package com.rkhd.eclient.streams;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

/**
 * Created by JYB on 2019/9/22.
 */
public interface CATKafkaStreamsProcessor {
    String INPUT_INVOKER = "input_invoker";
    String OUTPUT_INVOKER = "output_invoker";
    String INPUT_PROVIDER = "input_provider";
    String OUTPUT_PROVIDER = "output_provider";

    /**
     * Input binding.
     * @return {@link Input} binding for {@link KStream} type.
     */
    @Input(INPUT_PROVIDER)
    KStream<?, ?> inputProvider();

    /**
     * Output binding.
     * @return {@link Output} binding for {@link KStream} type.
     */
    @Output(OUTPUT_PROVIDER)
    KStream<?, ?> outputProvider();


    /**
     * Input binding.
     * @return {@link Input} binding for {@link KStream} type.
     */
//    @Input(INPUT_INVOKER)
//    KStream<?, ?> inputInvoker();
//
//    /**
//     * Output binding.
//     * @return {@link Output} binding for {@link KStream} type.
//     */
//    @Output(OUTPUT_INVOKER)
//    KStream<?, ?> outputInvoker();
}
