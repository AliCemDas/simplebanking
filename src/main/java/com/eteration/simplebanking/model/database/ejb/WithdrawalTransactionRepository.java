package com.eteration.simplebanking.model.database.ejb;

import com.eteration.simplebanking.model.WithdrawalTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalTransactionRepository extends CrudRepository<WithdrawalTransaction, Long> {
}
