package com.epush.broker.event;

import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangTuple;

import java.io.UnsupportedEncodingException;

/**
 * Created by guanxinquan on 16/2/1.
 */
public class LogoutEvent {

    private String userName;

    private OtpErlangPid pid;

    private String clientId;

    public LogoutEvent(OtpErlangTuple logout) throws UnsupportedEncodingException {
        userName = EventUtil.getString(logout,1);
        clientId = EventUtil.getString(logout,2);
        pid = EventUtil.getPid(logout,3);
    }

    public LogoutEvent() {
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("logout event").append("\n");
        builder.append("username:").append(userName).append("\n");
        builder.append("clientId:").append(clientId).append("\n");
        builder.append("pid:").append(pid);
        return builder.toString();
    }
}
