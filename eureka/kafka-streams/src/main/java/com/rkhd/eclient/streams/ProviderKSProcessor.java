package com.rkhd.eclient.streams;

import com.alibaba.fastjson.JSON;
import com.rkhd.eclient.entity.CatServiceBase;
import com.rkhd.eclient.entity.CatServiceMetrics;
import com.rkhd.eclient.entity.CatServiceProvider;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;

@EnableBinding(CATKafkaStreamsProcessor.class)
@Service
public class ProviderKSProcessor {
	private static Logger logger = LoggerFactory.getLogger(ProviderKSProcessor.class);

	public static final int WINDOW_SIZE_MS = 30000;
	@StreamListener(CATKafkaStreamsProcessor.INPUT_PROVIDER)
	@SendTo(CATKafkaStreamsProcessor.OUTPUT_PROVIDER)
	public KStream<Bytes, String> process(KStream<Bytes, String> input) {

		KStream<Bytes, String> stream =null;
		try{
//			Serde<String> stringSerdes = Serdes.String();
//			Serde<CatServiceProvider> catServiceProviderSerde = StreamsSerdes.CatServiceProderSerde();
			stream = input
					.map((key, value) ->
							{
								CatServiceBase catServiceBase = null;
								try {
									catServiceBase =JSON.parseObject(value, CatServiceBase.class);
								}catch (Exception e ){
									logger.error("serviceProvider to json error.{}",e.getMessage());
								}
								logger.debug("serviceProvider {}" , catServiceBase );
								if(catServiceBase == null) return  new KeyValue<>(value,value);
								return new KeyValue<>(catServiceBase.toString(),value);
							}
					)
					//.mapValues((k,v)->JSON.parseObject(v,CatServiceProvider.class))
					.groupByKey(Grouped.with(Serdes.String(),Serdes.String()))
					.windowedBy(TimeWindows.of(Duration.ofMillis(WINDOW_SIZE_MS)).grace(Duration.ZERO)) // https://blog.csdn.net/u012364631/article/details/94019707
					.aggregate(()->null, new Aggregator<String, String, Object>() {
						@Override
						public Object apply(String key, String value, Object aggregate) {
							if(StringUtils.isEmpty(value)){
								return null;
							}
							try {
								CatServiceProvider catServiceProvider = JSON.parseObject(value,CatServiceProvider.class);
								if(aggregate != null){
									CatServiceProvider cc = JSON.parseObject(((Bytes)aggregate).get(),CatServiceProvider.class);
									cc.setCount(cc.getCount() +1);
									String messageIds = cc.getMessageId();
									if(messageIds != null && messageIds.split(",").length <5){
										cc.setMessageId(cc.getMessageId()+","+catServiceProvider.getMessageId());
									}
									cc.setDurationInMillis(cc.getDurationInMillis()+ catServiceProvider.getDurationInMillis());
									value = JSON.toJSONString(cc);
								}else{
                                   value = JSON.toJSONString(catServiceProvider);
								}
							}catch (Exception e){
								logger.error("aggregate Object to json error.{}",e.getMessage());
							}
							return Bytes.wrap(value.getBytes());
						}
					})
					.suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
					//	.count(Materialized.as("WordCounts-1"))
					.toStream()
					.map((key, value) ->{
						if(value != null){
							try {
								CatServiceProvider csp = JSON.parseObject(((Bytes) value).get(), CatServiceProvider.class);
								if (csp != null) {
									CatServiceMetrics metrics = new CatServiceMetrics(csp);
									metrics.setTimestampInMillis(key.window().end());
									value = Bytes.wrap(JSON.toJSONString(metrics).getBytes());
								}
							}catch (Exception e){
								logger.error("value map error.{}",e.getMessage());
								return  null;
							}
						}
						return  new KeyValue<>(null,value.toString() );
					}
			);
		}catch (Exception e){
			logger.error("ks processor error .{}",e.getMessage());
		}finally {
			//return stream;
		}
		return stream;

	}


	//@SendTo(OUTPUT_TOPIC)
	public KStream<Bytes, String> sendTo(KStream<Windowed<String>, Object> windowedObjectKStream) {

		KStream<Bytes, String> stream =null;
		if(windowedObjectKStream != null){
		try{
					windowedObjectKStream.map((key, value) ->{
								if(value != null){
									try {
										CatServiceProvider csp = JSON.parseObject(((Bytes) value).get(), CatServiceProvider.class);
										if (csp != null) {
											CatServiceMetrics metrics = new CatServiceMetrics(csp);
											metrics.setTimestampInMillis(key.window().end());
											value = Bytes.wrap(JSON.toJSONString(metrics).getBytes());
										}
									}catch (Exception e){
										logger.error("value map error.{}",e.getMessage());
									}
								}
								return  new KeyValue<>(null,value.toString() );
							}
					);
		}catch (Exception e){
			logger.error("ks processor error .{}",e.getMessage());
		}finally {
			//return stream;
		}
		return stream;
		}else {
			logger.warn("windowedObjectKStream is null.");
		}
		return null;
	}
}