package com.imouahrani.bankTransactionsQueries.service;

import com.imouahrani.bankTransactionsQueries.model.BankBalance;
import com.imouahrani.bankTransactionsQueries.model.BankTransaction;
import com.imouahrani.bankTransactionsQueries.model.BankTransactionState;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class BankBalanceService {
    public BankBalance getBankBalanceById(String bankBalanceId){
        return BankBalance.builder()
                .id(1L)
                .amount(new BigDecimal(3000))
                .lastUpdate(new Date())
                .latestTransaction(BankTransaction.builder()
                        .balanceId(1L)
                        .state(BankTransactionState.APPROVED)
                        .time(new Date())
                        .amount(new BigDecimal(10))
                        .build())
                .build();
    }
}
