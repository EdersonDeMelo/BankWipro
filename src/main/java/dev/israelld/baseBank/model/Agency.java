package dev.israelld.baseBank.model;

import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class Agency{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String agencyName;
	private String agencyNumber;
	private String cnpj;


	public Agency(Long id, String agencyName, String agencyNumber, String cnpj) {
		super();
		this.id = id;
		this.agencyName = agencyName;
		this.agencyNumber = agencyNumber;
		this.cnpj = cnpj;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getAgencyNumber() {
		return agencyNumber;
	}
	public void setAgencyNumber(String agencyNumber) {
		this.agencyNumber = agencyNumber;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cnpj, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agency other = (Agency) obj;
		return Objects.equals(cnpj, other.cnpj) && id == other.id;
	}

}