package com.eteration.simplebanking.model.database.entity;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
//@Table(name="\"account\"")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public  String owner;
    public  String accountNumber;
    public Date createDate;
    public  Double balance;
    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
    public List<Transaction> transactions;


    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void post(Transaction transaction){
        if(transaction instanceof WithdrawalTransaction || transaction instanceof BillPaymentTransaction){
            setBalance(-transaction.getAmount());
        }
        else setBalance(+transaction.getAmount());
    }
}
