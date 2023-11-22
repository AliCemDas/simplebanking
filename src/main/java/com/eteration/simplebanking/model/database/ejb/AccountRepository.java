package com.eteration.simplebanking.model.database.ejb;

import com.eteration.simplebanking.model.database.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long> {

    Account findByAccountNumber(String accountNumber);
}
