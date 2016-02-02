package com.epush.broker.event;

import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangTuple;

import java.io.UnsupportedEncodingException;

/**
 * Created by guanxinquan on 16/2/1.
 */
public class SyncEvent {

    private String username;

    private String syncTag;

    private String channel;

    private OtpErlangPid pid;

    public SyncEvent(OtpErlangTuple syncEvent) throws UnsupportedEncodingException {
        username = EventUtil.getString(syncEvent,1);
        channel = EventUtil.getString(syncEvent,2);
        syncTag = EventUtil.getString(syncEvent,3);
        pid = EventUtil.getPid(syncEvent,4);
    }

    public SyncEvent() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSyncTag() {
        return syncTag;
    }

    public void setSyncTag(String syncTag) {
        this.syncTag = syncTag;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public OtpErlangPid getPid() {
        return pid;
    }

    public void setPid(OtpErlangPid pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("sync event").append("\n");
        builder.append("username:").append(username).append("\n");
        builder.append("channel:").append(channel).append("\n");
        builder.append("syncTag:").append(syncTag).append("\n");
        builder.append("pid:").append(pid);
        return builder.toString();
    }
}
