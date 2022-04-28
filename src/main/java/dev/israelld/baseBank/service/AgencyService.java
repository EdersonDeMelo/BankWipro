package dev.israelld.baseBank.service;

import java.util.List;
import java.util.Optional;

import dev.israelld.baseBank.model.Agency;
import dev.israelld.baseBank.repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {
	
	@Autowired
	private AgencyRepository repository;
	
	public Agency findById(Long id) {
        Optional<Agency> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public Agency findByAgencyName(String agencyName) {
        Agency obj = repository.findByAgencyName(agencyName);
        return obj;
    }
	
	public List<Agency> findAll() {
        return repository.findAll();
    }
	
	public Agency update(Long id, Agency obj) {
		Agency newObj = findById(id);
		newObj.setId(id);
        newObj.setAgencyName(obj.getAgencyName());
        newObj.setAgencyNumber(obj.getAgencyNumber());
        newObj.setCnpj(obj.getCnpj());
        return repository.save(newObj);
    }
	
	public Agency create(Agency obj) {
        return repository.save(obj);
    }
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
	
}
