package dev.israelld.baseBank.repository;

import dev.israelld.baseBank.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository  extends JpaRepository<CreditCard, Long> {

}
