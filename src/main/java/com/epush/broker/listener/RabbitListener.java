package com.epush.broker.listener;

import com.epush.broker.event.LoginEvent;
import com.epush.broker.event.LogoutEvent;
import com.epush.broker.event.PubEvent;
import com.epush.broker.event.SyncEvent;
import com.epush.broker.sender.ESender;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by guanxinquan on 16/2/1.
 */
public class RabbitListener implements MessageListener{


    private static final Logger logger = LoggerFactory.getLogger(RabbitListener.class);

    private ESender eSender;

    public void onMessage(Message message) {
        try {
            String routingKey = message.getMessageProperties().getReceivedRoutingKey();
            OtpErlangTuple msg = (OtpErlangTuple) OtpErlangTuple.decode(new OtpInputStream(message.getBody()));
            Object event = null;


            if ("epush-login-queue".equals(routingKey)) {
                LoginEvent loginEvent = new LoginEvent(msg);
                eSender.sendSync("test",loginEvent.getPid());
                event = loginEvent;
            } else if ("epush-logout-queue".equals(routingKey)) {
                LogoutEvent logoutEvent = new LogoutEvent(msg);
                event = logoutEvent;
            } else if ("epush-pub-queue".equals(routingKey)) {
                PubEvent pubEvent = new PubEvent(msg);
                event = pubEvent;
            } else if ("epush-sync-queue".equals(routingKey)) {
                SyncEvent syncEvent = new SyncEvent(msg);
                event = syncEvent;
            } else {
                logger.error("unknown queue message, route key is {}", routingKey);
            }
            logger.info("receive message : {}",event);
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
}
