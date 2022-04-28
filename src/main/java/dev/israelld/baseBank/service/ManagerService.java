package dev.israelld.baseBank.service;

import java.util.List;
import java.util.Optional;

import dev.israelld.baseBank.model.Manager;
import dev.israelld.baseBank.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
	
	
	@Autowired
	private ManagerRepository repository;
	
	public Manager findById(Long id) {
        Optional<Manager> obj = repository.findById(id);
        return obj.orElse(null);
    }
	
	public List<Manager> findAll() {
        return repository.findAll();
    }
	
	public Manager update(Long id, Manager obj) {
		Manager newObj = findById(id);
		newObj.setId(id);
        newObj.setName(obj.getName());
        newObj.setCpf(obj.getCpf());
        newObj.setAddress(obj.getAddress());       
        return repository.save(newObj);
    }
	
	public Manager create(Manager obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
	
}
