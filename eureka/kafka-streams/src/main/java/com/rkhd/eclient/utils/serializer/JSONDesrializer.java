package com.rkhd.eclient.utils.serializer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Deserializer;

import java.lang.reflect.Type;
import java.util.Map;

public class JSONDesrializer<T> implements Deserializer<T> {

    private  Class<T> deserializedClass;

    private Type reflectionType;

    public JSONDesrializer() {
    }

    public JSONDesrializer(Type reflectionType) {
        this.reflectionType = reflectionType;
    }

    public JSONDesrializer(Class<T> deserializedClass) {
        this.deserializedClass = deserializedClass;
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {
        if(deserializedClass == null) {
            deserializedClass = (Class<T>) map.get("serializedClass");
        }
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        if(bytes == null){
            return  null;
        }
        Type deserializeFrom = deserializedClass != null ? deserializedClass : reflectionType;
        return JSON.parseObject(new String(bytes),deserializeFrom);
    }



    @Override
    public void close() {

    }
}
