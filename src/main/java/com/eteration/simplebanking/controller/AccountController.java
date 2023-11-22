package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.utils.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.model.database.entity.Account;
import com.eteration.simplebanking.model.dto.ResponseDto;
import com.eteration.simplebanking.model.utils.ServiceException;
import com.eteration.simplebanking.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Method to return information about the account whose account number is given.
    @GetMapping("/{accountnumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountnumber) throws ServiceException {
        return new ResponseEntity<>(accountService.getAccount(accountnumber), HttpStatus.OK);
    }

    //Method that allows money to be withdrawn from the account whose account number is given.
    @PostMapping("/credit/{accountnumber}")
    public ResponseEntity<ResponseDto> credit(@PathVariable String accountnumber,@RequestBody @Valid WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException, ServiceException {
        return new ResponseEntity<>(accountService.credit(accountnumber, withdrawalTransaction.amount), HttpStatus.OK);
    }

    //Method that allows money to be deposited into the account whose account number is given.
    @PostMapping("/debit/{accountnumber}")
    public ResponseEntity<ResponseDto> debit(@PathVariable String accountnumber,@RequestBody @Valid DepositTransaction depositTransaction) throws ServiceException {
        return new ResponseEntity<>(accountService.debit(accountnumber, depositTransaction.amount), HttpStatus.OK);
    }
}