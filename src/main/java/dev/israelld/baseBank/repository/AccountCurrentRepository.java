package dev.israelld.baseBank.repository;

import dev.israelld.baseBank.model.AccountCurrent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCurrentRepository extends JpaRepository<AccountCurrent, Long> {

}
