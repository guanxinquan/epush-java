package com.epush.store;

import java.util.Date;

/**
 * Created by guanxinquan on 16/2/3.
 */
public class MessageStoreModel {

    private String id;

    private String user;

    private byte[] msg;

    private Date createAt;

    private String channel;

    public MessageStoreModel(String user,String channel, byte[] msg, Date createAt) {
        this.user = user;
        this.msg = msg;
        this.createAt = createAt;
        this.channel = channel;
    }


    public MessageStoreModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public byte[] getMsg() {
        return msg;
    }

    public void setMsg(byte[] msg) {
        this.msg = msg;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
