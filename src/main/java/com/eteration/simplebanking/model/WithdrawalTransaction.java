package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.database.entity.Transaction;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double v) {
        super(v);
    }


    public WithdrawalTransaction(double v , Date date) {
        super(v,date);
    }

}


