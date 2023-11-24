package com.eteration.simplebanking.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private String accountNumber;
    private Double balance;
    private String owner;

}
