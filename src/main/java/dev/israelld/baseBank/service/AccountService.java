package dev.israelld.baseBank.service;

import dev.israelld.baseBank.model.Account;
import dev.israelld.baseBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account findById(Long id) {
        Optional<Account> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

}
