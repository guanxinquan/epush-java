package com.epush.register;

import com.epush.utils.EPushUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Criteria.where;



import java.util.List;

/**
 * Created by guanxinquan on 16/2/2.
 */
public class MongoRegister implements PushRegister{

    private MongoTemplate template;

    private static final String COLLECTION_NAME = "epush";

    private static final Logger logger = LoggerFactory.getLogger(MongoRegister.class);

    public void register(RegisterModel register) {

        logger.warn("register {}", EPushUtils.toJson(register));
        template.save(register,COLLECTION_NAME);
    }

    public List<RegisterModel> loadRegister(String userName) {
        return template.find(new Query(where("userName").is(userName)),RegisterModel.class,COLLECTION_NAME);
    }

    public void unregister(String clientId, String host) {
        logger.warn("register remove {} {}",clientId,host);
        template.remove(new Query(where("clientId").is(clientId).and("host").is(host)),COLLECTION_NAME);
    }

    public void unregisterAllByUser(String userName) {
        logger.warn("register remove by user {}",userName);
        template.remove(new Query(where("userName").is(userName)),COLLECTION_NAME);
    }

    public void unregisterAllByHost(String host) {
        logger.warn("register remove by host {}",host);
        template.remove(new Query(where("host").is(host)),COLLECTION_NAME);
    }

    public MongoTemplate getTemplate() {
        return template;
    }

    public void setTemplate(MongoTemplate template) {
        this.template = template;
    }
}
