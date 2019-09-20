package com.rkhd.eclient.utils.serializer;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Serializer;

import java.lang.reflect.Type;
import java.util.Map;

public class JSONSerializer<T> implements Serializer<T> {


    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, T t) {
        return JSON.parseObject(s, (Type) t);
    }

    @Override
    public void close() {

    }
}
