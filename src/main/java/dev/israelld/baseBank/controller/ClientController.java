package dev.israelld.baseBank.controller;

import java.util.List;

import dev.israelld.baseBank.model.Client;
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
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientController {

	@Autowired
    private ClientService service;
	
	@GetMapping("/{id}")
    public ResponseEntity<Client> GetById(@PathVariable Long id) {
		Client obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    public ResponseEntity<List<Client>> GetAll() {
        List<Client> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @PostMapping
    public ResponseEntity<Client> Post(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.GONE).body(service.create(client));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> Put(@PathVariable Long id, @RequestBody Client obj) {
    	Client newClient = service.update(id, obj);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
