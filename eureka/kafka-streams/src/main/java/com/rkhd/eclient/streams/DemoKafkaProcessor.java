package com.rkhd.eclient.streams;

import com.alibaba.fastjson.JSON;
import com.rkhd.eclient.entity.CatServiceProvider;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Aggregator;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

//@EnableBinding(KafkaStreamsProcessor.class)
//@Service
public class DemoKafkaProcessor {

	public static final String INPUT_TOPIC = "input";
	public static final String OUTPUT_TOPIC = "output";
	public static final int WINDOW_SIZE_MS = 30000;
//	@StreamListener(INPUT_TOPIC)
//	@SendTo(OUTPUT_TOPIC)
	public KStream<Bytes, String> process(KStream<Bytes, String> input) {

//		return input
//				.flatMapValues(value ->
//					Arrays.asList(value.toLowerCase().split("\\W+"))
//				)
//				.map((key, value) ->
//					new KeyValue<>(value, value)
//				)
//				.groupByKey(Serialized.with(Serdes.String(), Serdes.String()))
//				.windowedBy(TimeWindows.of(WINDOW_SIZE_MS))
//				.count(Materialized.as("WordCounts-1"))
//				.toStream()
//				.map((key, value) -> new KeyValue<>(null, new WordCount(key.key(), value, new Date(key.window().start()), new Date(key.window().end()))));

		KStream<Bytes, String> stream =null;
		try{
			stream = input
					.map((key, value) ->
							{
								CatServiceProvider serviceProvider = null;
								try {
									serviceProvider =JSON.parseObject(value,CatServiceProvider.class);
								}catch (Exception e ){
									System.out.println("serviceProvider to json error."+ e.getMessage());
								}
								System.out.println("serviceProvider = [" + serviceProvider + "]");
								if(serviceProvider == null) return  new KeyValue<>(value,value);
								return new KeyValue<>(serviceProvider.getDomain(),value);
							}
					)
					.groupByKey(Serialized.with(Serdes.String(), Serdes.String()))
					.windowedBy(TimeWindows.of(WINDOW_SIZE_MS))
					.aggregate(()->null, new Aggregator<String, String, Object>() {
						@Override
						public Object apply(String key, String value, Object aggregate) {
							try {
								if(aggregate != null){
									CatServiceProvider cc = JSON.parseObject(((Bytes)aggregate).get(),CatServiceProvider.class);
									cc.setCount(cc.getCount()+1);
									value = JSON.toJSONString(cc);
								}
							}catch (Exception e){
								System.out.println("aggregate Object to json error.");
							}

							return Bytes.wrap(value.getBytes());
						}
					})
					//	.count(Materialized.as("WordCounts-1"))
					.toStream()
					.map((key, value) ->{
								//value.setTimestampInMillis(key.window().end());
								return  new KeyValue<>(null,value.toString() );
							}
					);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//return stream;
		}
		return stream;


//		return input
//				.map((key, value) ->
//						new KeyValue<>(value,value)
//				)
//				.groupByKey(Serialized.with(Serdes.String(), Serdes.String()))
//				.windowedBy(TimeWindows.of(WINDOW_SIZE_MS))
//				.aggregate(new Initializer<CatServiceProvider>() {
//					@Override
//					public CatServiceProvider apply() {
//						return null;
//					}
//				}, new Aggregator<String, String, CatServiceProvider>() {
//					@Override
//					public CatServiceProvider apply(String key, String value, CatServiceProvider catServiceProvider) {
//						return JSON.parseObject(value, CatServiceProvider.class);
//					}
//				})
////				.count(Materialized.as("WordCounts-1"))
//				.toStream()
//				.map((key, value) ->{
//							value.setTimestampInMillis(key.window().end());
//							return  new KeyValue<>(null, JSON.toJSONString(value) );
//						}
//				);

	}
}