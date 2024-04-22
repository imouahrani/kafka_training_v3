package com.imouahrani.bankTransactionsQueries.controller;

import com.imouahrani.bankTransactionsQueries.model.BankBalance;
import com.imouahrani.bankTransactionsQueries.service.BankBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/bank-balance")
public class BankBalanceController {
    private final BankBalanceService bankBalanceService;

    @Autowired
    public BankBalanceController(BankBalanceService bankBalanceService) {
        this.bankBalanceService = bankBalanceService;
    }
    @GetMapping(value = "/{bankBalanceId}", produces = "application/json")
    public ResponseEntity<BankBalance> getBankBalanceById(@PathVariable("bankBalanceId") String bankBalanceId){
        return ResponseEntity.ok(bankBalanceService.getBankBalanceById(bankBalanceId));

    }
}
