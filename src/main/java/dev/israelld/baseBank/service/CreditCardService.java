package dev.israelld.baseBank.service;

import java.util.List;
import java.util.Optional;

import dev.israelld.baseBank.model.CreditCard;
import dev.israelld.baseBank.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreditCardService {

	@Autowired
	private CreditCardRepository repository;
	
	public CreditCard findById(Long id) {
        Optional<CreditCard> obj = repository.findById(id);
        return obj.orElse(null);
    }
	
	public List<CreditCard> findAll() {
        return repository.findAll();
    }
	
	public CreditCard update(Long id, CreditCard obj) {
		CreditCard newObj = findById(id);
		newObj.setId(id);
        newObj.setAccount(obj.getAccount());
        newObj.setCardNumber(obj.getCardNumber());
        newObj.setCvv(obj.getCvv());       
        return repository.save(newObj);
    }
	
	public CreditCard create(CreditCard obj) {
        return repository.save(obj);
    }
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
