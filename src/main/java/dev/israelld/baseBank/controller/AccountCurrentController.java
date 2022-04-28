package dev.israelld.baseBank.controller;

import java.util.List;

import dev.israelld.baseBank.model.AccountCurrent;
import dev.israelld.baseBank.service.AccountCurrentService;
import dev.israelld.baseBank.service.AgencyService;
import dev.israelld.baseBank.service.ClientService;
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
@RequestMapping("/accountCurrent")
@CrossOrigin("*")
public class AccountCurrentController {

	@Autowired
    private AccountCurrentService service;
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private ClientService clientService;
	
	@GetMapping("/{id}")
    public ResponseEntity<AccountCurrent> GetById(@PathVariable Long id) {
        AccountCurrent obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    public ResponseEntity<List<AccountCurrent>> GetAll() {
        List<AccountCurrent> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @PostMapping
    public ResponseEntity<AccountCurrent> Post(@RequestBody AccountCurrent currentAccount) {
        currentAccount.setAgency(agencyService.findById(currentAccount.getAgency().getId()));
        currentAccount.setClient(clientService.findById(currentAccount.getClient().getId()));
        currentAccount.setTipo("C");
        return ResponseEntity.status(HttpStatus.GONE).body(service.create(currentAccount));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AccountCurrent> Put(@PathVariable Long id, @RequestBody AccountCurrent obj) {
        AccountCurrent newCurrentAccount = service.update(id, obj);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCurrentAccount);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
