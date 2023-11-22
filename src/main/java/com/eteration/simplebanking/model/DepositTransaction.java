package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.database.entity.Transaction;
import lombok.*;

@Getter
@Setter
@Builder
public class DepositTransaction extends Transaction {
    public DepositTransaction(double v) {
        super();
    }
}
