package com.imouahrani.bankTransactionsQueries.config;

import com.imouahrani.bankTransactionsQueries.topology.BankBalanceTopology;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

public class StreamConfiguration {
    @Value("${kafka.streams.state.dir:/tmp/kafka-streams/bank}")
    private static String kafkaStreamsHostInfo;
    @Value("${kafka.streams.host.info:localhost:8080}")
    private static String kafkaStreamsStateDir;

    @Bean
    public static Properties getConfiguration() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "bank-balance");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(StreamsConfig.APPLICATION_SERVER_CONFIG, "0");
        properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, kafkaStreamsHostInfo);
        properties.put(StreamsConfig.STATE_DIR_CONFIG, kafkaStreamsStateDir);


        return properties;
    }
    @Bean
    public KafkaStreams kafkaStreams(@Qualifier("kafkaStreamsProps") Properties props){
        // var configuration = StreamConfiguration.getConfiguration();
        var topology = BankBalanceTopology.buildTopology();
        var kafkaStreams = new KafkaStreams(topology, props);

        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
        return kafkaStreams;

    }
}