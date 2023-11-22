package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.database.entity.Transaction;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double v) {
        super();
    }
}


