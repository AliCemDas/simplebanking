package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.database.entity.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepositTransaction extends Transaction {
    public DepositTransaction(double v) {
        super(v);
    }
}
