package dev.israelld.baseBank.controller;

import java.util.List;

import dev.israelld.baseBank.model.CreditCard;
import dev.israelld.baseBank.service.AccountCurrentService;
import dev.israelld.baseBank.service.AccountSpecialService;
import dev.israelld.baseBank.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creditCard")
@CrossOrigin("*")
public class CreditCardController {

	@Autowired
    private CreditCardService service;
    @Autowired
    private AccountCurrentService accountCurrentService;
    @Autowired
    private AccountSpecialService accountSpecialservice;

	@GetMapping("/{id}")
    public ResponseEntity<CreditCard> GetById(@PathVariable Long id) {
		CreditCard obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<CreditCard>> GetAll() {
        List<CreditCard> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CreditCard> Post(@RequestBody CreditCard creditCard) {
        creditCard.setAccount(accountCurrentService.findById(creditCard.getAccount().getId()));
        return ResponseEntity.status(HttpStatus.GONE).body(service.create(creditCard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> Put(@PathVariable Long id, @RequestBody CreditCard obj) {
    	CreditCard newCreditCard = service.update(id, obj);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCreditCard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
