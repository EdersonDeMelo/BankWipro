package dev.israelld.baseBank.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import dev.israelld.baseBank.model.Agency;
import dev.israelld.baseBank.model.Manager;
import dev.israelld.baseBank.service.AgencyService;
import dev.israelld.baseBank.service.ManagerService;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/manager")
@CrossOrigin("*")
public class ManagerController {

	@Autowired
    private ManagerService service;
    @Autowired
    private AgencyService agencyService;
	
	@GetMapping("/{id}")
    public ResponseEntity<Manager> GetById(@PathVariable Long id) {
		Manager obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Manager>> GetAll() {
        List<Manager> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Manager> PostByAgency(@RequestBody Manager manager) {
        manager.setAgency(agencyService.findById(manager.getAgency().getId()));
//        manager.setAgency(agencyService.findByAgencyName(manager.getAgency().getAgencyName()));
        return ResponseEntity.status(HttpStatus.GONE).body(service.create(manager));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manager> Put(@PathVariable Long id, @RequestBody Manager obj) {
    	Manager newManager = service.update(id, obj);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newManager);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
