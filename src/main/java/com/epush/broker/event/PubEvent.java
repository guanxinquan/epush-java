package com.epush.broker.event;

import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangTuple;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by guanxinquan on 16/2/1.
 */
public class PubEvent {

    private String userName;

    private OtpErlangPid pid;

    private byte[] message;

    private String channelName;

    public PubEvent(OtpErlangTuple pubEvent) throws UnsupportedEncodingException {
        userName = EventUtil.getString(pubEvent,1);
        channelName = EventUtil.getString(pubEvent,2);
        message = EventUtil.getBytes(pubEvent,3);
        pid = EventUtil.getPid(pubEvent,4);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public OtpErlangPid getPid() {
        return pid;
    }

    public void setPid(OtpErlangPid pid) {
        this.pid = pid;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("pub event").append("\n");
        builder.append("username:").append(userName).append("\n");
        builder.append("channel:").append(channelName).append("\n");
        builder.append("message:").append(new String(message)).append("\n");
        builder.append("pid:").append(pid);
        return builder.toString();
    }
}
