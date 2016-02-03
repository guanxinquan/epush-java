package com.epush.broker.listener;

import com.epush.broker.event.*;
import com.epush.broker.sender.ESender;
import com.epush.register.PushRegister;
import com.epush.register.RegisterModel;
import com.epush.store.MessageStore;
import com.epush.store.MessageStoreModel;
import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by guanxinquan on 16/2/1.
 */
public class RabbitListener implements MessageListener{


    private static final Logger logger = LoggerFactory.getLogger(RabbitListener.class);

    private ESender eSender;

    private PushRegister register;

    private MessageStore store;

    public void onMessage(Message message) {
        try {
            String routingKey = message.getMessageProperties().getReceivedRoutingKey();
            OtpErlangTuple msg = (OtpErlangTuple) OtpErlangTuple.decode(new OtpInputStream(message.getBody()));
            Object event = null;

            if ("epush-login-queue".equals(routingKey)) {
                LoginEvent loginEvent = new LoginEvent(msg);
                register.register(new RegisterModel(//注册当前登录的用户
                        loginEvent.getClientId(),
                        loginEvent.getUserName(),
                        loginEvent.getPid().node(),
                        EventUtil.getPidBinary(loginEvent.getPid())
                        ));

                Map<String,String> channels = loginEvent.getChannel();
                for(Map.Entry<String,String> ch : channels.entrySet()) {//同步所有用户需要同步的channel数据
                    syncMessage(loginEvent.getUserName(),ch.getKey(),ch.getValue(),loginEvent.getPid());
                }
                event = loginEvent;
            } else if ("epush-logout-queue".equals(routingKey)) {
                LogoutEvent logoutEvent = new LogoutEvent(msg);
                register.unregister(logoutEvent.getClientId(),logoutEvent.getPid().node());//注销用户
                event = logoutEvent;
            } else if ("epush-pub-queue".equals(routingKey)) {
                PubEvent pubEvent = new PubEvent(msg);
                store.saveMessage(new MessageStoreModel(pubEvent.getUserName(),pubEvent.getChannelName(),pubEvent.getMessage(),new Date()));
                event = pubEvent;
            } else if ("epush-sync-queue".equals(routingKey)) {
                SyncEvent syncEvent = new SyncEvent(msg);
                syncMessage(syncEvent.getUsername(),syncEvent.getChannel(),syncEvent.getSyncTag(),syncEvent.getPid());
                event = syncEvent;
            } else {
                logger.error("unknown queue message, route key is {}", routingKey);
            }
            logger.warn("receive message : {}", event);
        }catch (Exception e){
            logger.error("message parser error {}",e);
        }

    }

    public ESender geteSender() {
        return eSender;
    }

    public void seteSender(ESender eSender) {
        this.eSender = eSender;
    }

    public PushRegister getRegister() {
        return register;
    }

    public void setRegister(PushRegister register) {
        this.register = register;
    }

    public MessageStore getStore() {
        return store;
    }

    public void setStore(MessageStore store) {
        this.store = store;
    }

    private void syncMessage(String userName,String channel,String tag,OtpErlangPid pid){
        List<MessageStoreModel> messages =  store.syncMessage(userName,channel,tag);
        if(!messages.isEmpty()) {
            try {
                eSender.sendMessage(channel,tag, messages.get(0).getId(),toPacket(messages),pid);
            } catch (IOException e) {
                logger.error("esend message error ",e);
            }
        }
    }

    private byte[] toPacket(List<MessageStoreModel> messages) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        for(MessageStoreModel msm: messages){
            if(msm.getMsg().length > 0) {
                stream.write(IntToByte(msm.getMsg().length));
                stream.write(msm.getMsg());
            }
        }

        return stream.toByteArray();
    }

    private byte[] IntToByte(int i){
        return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(i).array();
    }
}
