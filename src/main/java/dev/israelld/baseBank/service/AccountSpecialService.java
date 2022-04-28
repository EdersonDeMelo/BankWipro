package dev.israelld.baseBank.service;

import java.util.List;
import java.util.Optional;

import dev.israelld.baseBank.model.AccountSpecial;
import dev.israelld.baseBank.repository.AccountSpecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountSpecialService {

	@Autowired
	private AccountSpecialRepository repository;
	
	public AccountSpecial findById(Long id) {
        Optional<AccountSpecial> obj = repository.findById(id);
        return obj.orElse(null);
    }
	
	public List<AccountSpecial> findAll() {
        return repository.findAll();
    }
	
	public AccountSpecial update(Long id, AccountSpecial obj) {
        AccountSpecial newObj = findById(id);
		newObj.setId(id);
		newObj.setLimitValue(obj.getLimitValue());
        newObj.setTipo(obj.getTipo());
        newObj.setBalance(obj.getBalance());
        newObj.setNumber(obj.getNumber());
        newObj.setAgency(obj.getAgency());
        newObj.setClient(obj.getClient());
        newObj.setNumber(obj.getNumber());      
        return repository.save(newObj);
    }
	
	public AccountSpecial create(AccountSpecial obj) {
        return repository.save(obj);
    }
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
