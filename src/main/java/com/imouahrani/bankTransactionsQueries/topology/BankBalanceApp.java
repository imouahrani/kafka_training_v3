package com.imouahrani.bankTransactionsQueries.topology;

import com.imouahrani.bankTransactionsQueries.config.StreamConfiguration;
import com.imouahrani.bankTransactionsQueries.topology.BankBalanceTopology;
import org.apache.kafka.streams.KafkaStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankBalanceApp {

    public static void main(String[] args) {
        SpringApplication.run(BankBalanceApp.class, args);
      /*  var configuration = StreamConfiguration.getConfiguration();
        var topology = BankBalanceTopology.buildTopology();
        var kafkaStreams = new KafkaStreams(topology, configuration);

        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));*/
    }
}
