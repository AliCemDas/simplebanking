package com.eteration.simplebanking.model.database.ejb;

import com.eteration.simplebanking.model.DepositTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositTransactionRepository extends CrudRepository<DepositTransaction, Long> {
}
