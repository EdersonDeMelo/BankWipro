package dev.israelld.baseBank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "C")
public class AccountCurrent extends Account{

	
	
    public AccountCurrent(Long id, String number, double balance, boolean status) {
		super(id, number, balance, status);
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return "AccountCurrent{} " + super.toString();
    }
}