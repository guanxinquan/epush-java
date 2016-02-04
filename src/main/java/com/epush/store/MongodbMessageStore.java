package com.epush.store;

import com.epush.utils.EPushUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by guanxinquan on 16/2/3.
 */
public class MongodbMessageStore implements MessageStore{

    private MongoTemplate template;

    private static final String COLLECTION_NAME = "epushStore";

    private static final Logger logger = LoggerFactory.getLogger(MongodbMessageStore.class);


    public void saveMessage(MessageStoreModel m) {
        logger.warn("save message to mongo {}", EPushUtils.toJson(m));
        template.save(m,COLLECTION_NAME);
    }

    public List<MessageStoreModel> syncMessage(String user, String channel, String syncTag) {
        logger.warn("sync message {} {} {}",user,channel,syncTag);
        ObjectId objectId = null;
        if(syncTag.equals("1")) {
            objectId = new ObjectId(new Date(1000l));

        }else{
            objectId = new ObjectId(syncTag);
        }
        return template.find(new Query(where("user").is(user).and("channel").is(channel).and("id").gt(objectId)).with(new Sort(Sort.Direction.ASC,"id")).limit(50),MessageStoreModel.class,COLLECTION_NAME);
    }

    public MongoTemplate getTemplate() {
        return template;
    }

    public void setTemplate(MongoTemplate template) {
        this.template = template;
    }
}
