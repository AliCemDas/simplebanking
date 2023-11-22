package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.database.entity.Transaction;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double v) {
        super(v);
    }
}


