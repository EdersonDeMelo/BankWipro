package dev.israelld.baseBank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client extends Person{

	public Client(Long id, String cpf, String name, String address) {
		super(id, cpf, name, address);
		// TODO Auto-generated constructor stub
	}

	

}
