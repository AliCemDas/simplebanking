package com.eteration.simplebanking.model.database.entity;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "\"account\"")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;
    @Column(name = "owner")
    public String owner;
    @Column(name = "accountNumber")
    public String accountNumber;
    @Column(name = "createDate")
    public Date createDate;
    @Column(name = "balance")
    public Double balance;


    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    public List<Transaction> transactions;


    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void post(Transaction transaction) {
        if (transaction instanceof WithdrawalTransaction || transaction instanceof BillPaymentTransaction) {
            setBalance(-transaction.getAmount());
        } else setBalance(+transaction.getAmount());
    }
}
