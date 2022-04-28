package dev.israelld.baseBank.controller;

import dev.israelld.baseBank.controller.form.TransferForm;
import dev.israelld.baseBank.model.*;
import dev.israelld.baseBank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;


    @GetMapping("balance/{id}")
    public ResponseEntity<String> Balance(@PathVariable Long id) {
        Account account = accountService.findById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(String.format("Saldo atual: %.2f",account.getBalance()));
    }

    @PutMapping("transfer/{id}")
    public ResponseEntity<String> Transfer(@PathVariable Long id, @RequestBody TransferForm form) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionService.transfer(id, form));
    }

    @PutMapping("deposit/{id}")
    public ResponseEntity<String> Deposit(@PathVariable Long id, @RequestBody Account obj) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionService.deposit(id, obj));
    }

    @PutMapping("withdraw/{id}")
    public ResponseEntity<String> Withdraw(@PathVariable Long id, @RequestBody Account obj) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionService.withdraw(id, obj));

    }
}