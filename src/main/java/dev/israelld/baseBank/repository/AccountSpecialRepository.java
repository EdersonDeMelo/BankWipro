package dev.israelld.baseBank.repository;

import dev.israelld.baseBank.model.AccountSpecial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSpecialRepository extends JpaRepository<AccountSpecial, Long> {

}
