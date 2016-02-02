package com.epush.register;

import java.util.List;

/**
 * Created by guanxinquan on 16/2/2.
 */
public interface PushRegister {

    public void register(RegisterModel register);

    public List<RegisterModel> loadRegister(String userName);

    public void unregister(String clientId,String host);

    public void unregisterAllByUser(String userName);

    public void unregisterAllByHost(String host);

}
