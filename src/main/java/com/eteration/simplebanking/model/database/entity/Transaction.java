package com.eteration.simplebanking.model.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="\"transaction\"")
@Entity
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;
    @Column(name = "date")
    public Date date;
    @Column(name = "amount")
    public Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Account account;

    public Transaction(Double amount) {
        this.amount = amount;
    }

    public Transaction( Double amount,Date date ) {
        this.date = date;
        this.amount = amount;
    }
}
