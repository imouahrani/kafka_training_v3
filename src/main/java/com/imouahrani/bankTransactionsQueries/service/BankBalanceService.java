package com.imouahrani.bankTransactionsQueries.service;

import com.imouahrani.bankTransactionsQueries.model.BankBalance;
import com.imouahrani.bankTransactionsQueries.model.BankTransaction;
import com.imouahrani.bankTransactionsQueries.model.BankTransactionState;
import com.imouahrani.bankTransactionsQueries.topology.BankBalanceTopology;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class BankBalanceService {

    private KafkaStreams kafkaStreams;

    @Autowired
    public BankBalanceService(KafkaStreams kafkaStreams) {
        this.kafkaStreams = kafkaStreams;
    }
    public BankBalance getBankBalanceById(Long bankBalanceId){
       /* return BankBalance.builder()
                .id(1L)
                .amount(new BigDecimal(3000))
                .lastUpdate(new Date())
                .latestTransaction(BankTransaction.builder()
                        .balanceId(1L)
                        .state(BankTransactionState.APPROVED)
                        .time(new Date())
                        .amount(new BigDecimal(10))
                        .build())
                .build();*/
        return getStore().get(bankBalanceId);

    }
    private ReadOnlyKeyValueStore<Long, BankBalance> getStore(){
        return kafkaStreams.store(StoreQueryParameters.fromNameAndType(
                BankBalanceTopology.BANK_BALANCES_STORE,
                QueryableStoreTypes.keyValueStore()
        ));


    }
}
