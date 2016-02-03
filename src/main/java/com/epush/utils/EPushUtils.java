package com.epush.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guanxinquan on 16/2/3.
 */
public class EPushUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static final Logger logger = LoggerFactory.getLogger(EPushUtils.class);

    public static final String toJson(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("object to json error ",e);
            return null;
        }
    }


}
