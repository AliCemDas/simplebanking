package com.eteration.simplebanking.model.database.entity;

import com.eteration.simplebanking.model.database.entity.Account;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
//@Table(name="\"transaction\"")
@Entity
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Date date;
    public Double amount;
    @ManyToOne(fetch = FetchType.LAZY)
    public Account account;
}
