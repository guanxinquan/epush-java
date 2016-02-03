package com.epush.store;

import java.util.List;

/**
 * Created by guanxinquan on 16/2/3.
 */
public interface MessageStore {

    /**
     * 用户上行消息,用于存储消息
     */
    public void saveMessage(MessageStoreModel m);

    /**
     * 同步消息
     * @param user 需要同步的用户
     * @param channel 消息对应的channel
     * @param syncTag 当前消息同步的tag信息
     */
    public List<MessageStoreModel> syncMessage(String user,String channel,String syncTag);



}
