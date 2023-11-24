package com.eteration.simplebanking.model.database.entity;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.model.utils.InsufficientBalanceException;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.eteration.simplebanking.model.utils.Constants.ERROR_MESSAGE_BALANCE_NOT_ENOUGH;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "\"account\"")
@Entity
@Builder
@AllArgsConstructor
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

    public Account(String owner, String accountNumber, Double balance) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void post(Transaction transaction) {
        if (transaction instanceof WithdrawalTransaction || transaction instanceof BillPaymentTransaction) {
            setBalance(-transaction.getAmount());
            if (CollectionUtils.isEmpty(this.transactions)) {
                this.transactions = new ArrayList<>();
            }
            this.transactions.add(transaction);
        } else {
            setBalance(+transaction.getAmount());
            if (CollectionUtils.isEmpty(this.transactions)) {
                this.transactions = new ArrayList<>();
            }
            this.transactions.add(transaction);
        }
    }

    public void deposit(Double amount) {
        if (this.balance == null) {
            this.balance = amount;
        }
        this.balance = this.balance + amount;
    }

    public void withdraw(Double amount) throws InsufficientBalanceException {
        if (this.balance == null || this.balance < amount) {
            throw new InsufficientBalanceException(ERROR_MESSAGE_BALANCE_NOT_ENOUGH);
        }
        this.balance = this.balance - amount;
    }
}
