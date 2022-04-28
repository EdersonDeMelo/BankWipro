package dev.israelld.baseBank.controller;

import java.util.List;

import dev.israelld.baseBank.model.Agency;
import dev.israelld.baseBank.service.AgencyService;
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
@RequestMapping("/agency")
@CrossOrigin("*")
public class AgencyController {

	@Autowired
    private AgencyService service;
	
	@GetMapping("/{id}")
    public ResponseEntity<Agency> GetById(@PathVariable Long id) {
		Agency obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Agency>> GetAll() {
        List<Agency> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @PostMapping
    public ResponseEntity<Agency> Post(@RequestBody Agency agency) {
    	Agency newObj = service.create(agency);
        return ResponseEntity.status(HttpStatus.GONE).body(service.create(agency));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Agency> Put(@PathVariable Long id, @RequestBody Agency obj) {
    	Agency newAgency = service.update(id, obj);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newAgency);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
