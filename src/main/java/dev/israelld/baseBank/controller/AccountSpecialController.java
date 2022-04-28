package dev.israelld.baseBank.controller;

import java.util.List;

import dev.israelld.baseBank.model.AccountSpecial;
import dev.israelld.baseBank.service.AccountSpecialService;
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
@RequestMapping("/accountSpecial")
@CrossOrigin("*")
public class AccountSpecialController {

	@Autowired
    private AccountSpecialService service;
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private ClientService clientService;
	
	@GetMapping("/{id}")
    public ResponseEntity<AccountSpecial> GetById(@PathVariable Long id) {
        AccountSpecial obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    public ResponseEntity<List<AccountSpecial>> GetAll() {
        List<AccountSpecial> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @PostMapping
    public ResponseEntity<AccountSpecial> Post(@RequestBody AccountSpecial specialAccount) {
        specialAccount.setAgency(agencyService.findById(specialAccount.getAgency().getId()));
        specialAccount.setClient(clientService.findById(specialAccount.getClient().getId()));
        specialAccount.setTipo("S");
        return ResponseEntity.status(HttpStatus.GONE).body(service.create(specialAccount));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AccountSpecial> Put(@PathVariable Long id, @RequestBody AccountSpecial obj) {
        AccountSpecial newSpecialAccount = service.update(id, obj);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newSpecialAccount);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
