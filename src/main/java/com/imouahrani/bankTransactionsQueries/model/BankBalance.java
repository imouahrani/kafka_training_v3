package com.imouahrani.bankTransactionsQueries.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankBalance {

    private Long id;
    private BigDecimal amount = BigDecimal.ZERO;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private Date lastUpdate;
    private BankTransaction latestTransaction;

    public BankBalance process(BankTransaction bankTransaction) {
        this.id = bankTransaction.getBalanceId();
        this.latestTransaction = bankTransaction;
        if(this.amount.add(bankTransaction.getAmount()).compareTo(BigDecimal.ZERO) >= 0) {
            this.latestTransaction.setState(BankTransactionState.APPROVED);
            this.amount = this.amount.add(bankTransaction.getAmount());
        } else {
            this.latestTransaction.setState(BankTransactionState.REJECTED);
        }
        this.lastUpdate = bankTransaction.getTime();
        return this;
    }
}

