package com.rkhd.eclient.utils.serde;

import com.rkhd.eclient.entity.CatServiceProvider;
import com.rkhd.eclient.utils.serializer.JSONDesrializer;
import com.rkhd.eclient.utils.serializer.JSONSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @info StreamsSerdes
 * @author JYB
 * @date
 */
public class StreamsSerdes {

    public static Serde<CatServiceProvider> CatServiceProderSerde(){
        return new CatServiceProderSerde();
    }

    public static final class CatServiceProderSerde extends WrapperSerde<CatServiceProvider> {
        public CatServiceProderSerde() {
            super(new JSONSerializer<>(), new JSONDesrializer<>(CatServiceProvider.class));
        }
    }


    /**
     *
     * @param <T>
     */
    private static class WrapperSerde<T> implements Serde<T> {

        private JSONSerializer<T> serializer;
        private JSONDesrializer<T> deserializer;

        WrapperSerde(JSONSerializer<T> serializer, JSONDesrializer<T> deserializer) {
            this.serializer = serializer;
            this.deserializer = deserializer;
        }

        @Override
        public void configure(Map<String, ?> map, boolean b) {

        }

        @Override
        public void close() {

        }

        @Override
        public Serializer<T> serializer() {
            return serializer;
        }

        @Override
        public Deserializer<T> deserializer() {
            return deserializer;
        }
    }
}
