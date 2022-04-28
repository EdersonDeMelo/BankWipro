package dev.israelld.baseBank.service;

import java.util.List;
import java.util.Optional;

import dev.israelld.baseBank.model.AccountCurrent;
import dev.israelld.baseBank.repository.AccountCurrentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCurrentService {
	
	@Autowired
	private AccountCurrentRepository repository;
	
	public AccountCurrent findById(Long id) {
        Optional<AccountCurrent> obj = repository.findById(id);
        return obj.orElse(null);
    }
	
	public List<AccountCurrent> findAll() {
        return repository.findAll();
    }
	
	public AccountCurrent update(Long id, AccountCurrent obj) {
        AccountCurrent newObj = findById(id);
		newObj.setId(id);
        newObj.setTipo(obj.getTipo());
        newObj.setBalance(obj.getBalance());
        newObj.setNumber(obj.getNumber());
        newObj.setAgency(obj.getAgency());
        newObj.setClient(obj.getClient());
        newObj.setNumber(obj.getNumber());
        return repository.save(newObj);
    }
	
	public AccountCurrent create(AccountCurrent obj) {
        return repository.save(obj);
    }
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
