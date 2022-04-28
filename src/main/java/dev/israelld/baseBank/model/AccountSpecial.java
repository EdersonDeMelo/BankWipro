package dev.israelld.baseBank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "S")
public class AccountSpecial extends Account{
	
	
	public AccountSpecial(Long id, String number, double balance, boolean status) {
		super(id, number, balance, status);
		// TODO Auto-generated constructor stub
	}
	

	private double limitValue;

	public double getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(double limitValue) {
		this.limitValue = limitValue;
	}
	
}