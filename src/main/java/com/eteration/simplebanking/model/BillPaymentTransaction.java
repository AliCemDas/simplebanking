package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.database.entity.Transaction;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BillPaymentTransaction extends Transaction {
    private String payee;
    private String gsmNumber;
}
