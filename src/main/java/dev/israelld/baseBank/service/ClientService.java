package dev.israelld.baseBank.service;

import java.util.List;
import java.util.Optional;

import dev.israelld.baseBank.model.Client;
import dev.israelld.baseBank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Client findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        return obj.orElse(null);
    }
	
	public List<Client> findAll() {
        return repository.findAll();
    }
	
	public Client update(Long id, Client obj) {
		Client newObj = findById(id);
		newObj.setId(id);
        newObj.setName(obj.getName());
        newObj.setCpf(obj.getCpf());
        newObj.setAddress(obj.getAddress());       
        return repository.save(newObj);
    }
	
	public Client create(Client obj) {
        return repository.save(obj);
    }
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
