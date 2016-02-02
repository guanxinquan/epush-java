package com.epush.register;

import java.util.Date;

/**
 * Created by guanxinquan on 16/2/2.
 */
public class RegisterModel {

    private String clientId;
    private String userName;
    private String host;
    private byte[] pid;
    private Date updateTime;

    public RegisterModel() {
    }

    public RegisterModel(String clientId, String userName, String host, byte[] pid) {
        this.clientId = clientId;
        this.userName = userName;
        this.host = host;
        this.pid = pid;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public byte[] getPid() {
        return pid;
    }

    public void setPid(byte[] pid) {
        this.pid = pid;
    }
}
