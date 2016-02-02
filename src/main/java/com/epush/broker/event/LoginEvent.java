package com.epush.broker.event;

import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangTuple;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by guanxinquan on 16/2/1.
 */
public class LoginEvent {

    private String userName;

    private String password;

    private OtpErlangPid pid;

    private String clientId;

    private Map<String,String> channel;

    public LoginEvent(OtpErlangTuple login) throws UnsupportedEncodingException {
        userName = EventUtil.getString(login,1);
        password = EventUtil.getString(login,2);
        clientId = EventUtil.getString(login,3);
        channel = EventUtil.getMap(login,4);
        pid = EventUtil.getPid(login,5);
    }

    public LoginEvent(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Map<String, String> getChannel() {
        return channel;
    }

    public void setChannel(Map<String, String> channel) {
        this.channel = channel;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("login event").append("\n");
        builder.append("username:"+userName+"\n");
        builder.append("password:"+password+"\n");
        builder.append("clientId:"+clientId+"\n");
        builder.append("channels:"+"\n");
        for(Map.Entry<String,String> e : channel.entrySet()){
            builder.append("\t"+e.getKey()+"==>"+e.getValue());
        }
        builder.append("\npid:"+pid.toString());
        return builder.toString();
    }
}
