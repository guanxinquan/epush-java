package com.epush.register;

import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * Created by guanxinquan on 16/2/2.
 */
public class MongoRegister implements PushRegister{

    private MongoTemplate template;

    private static final String COLLECTION_NAME = "epush";

    public void register(RegisterModel register) {
        template.save(register,COLLECTION_NAME);
    }

    public List<RegisterModel> loadRegister(String userName) {
        return null;
    }

    public void unregister(String clientId, String host) {

    }

    public void unregisterAllByUser(String userName) {

    }

    public void unregisterAllByHost(String host) {

    }

    public MongoTemplate getTemplate() {
        return template;
    }

    public void setTemplate(MongoTemplate template) {
        this.template = template;
    }
}
