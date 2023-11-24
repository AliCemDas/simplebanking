package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.model.dto.AccountDto;
import com.eteration.simplebanking.model.utils.InsufficientBalanceException;
import com.eteration.simplebanking.model.database.entity.Account;
import com.eteration.simplebanking.model.dto.ResponseDto;
import com.eteration.simplebanking.model.utils.ServiceException;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /**
     * Method to respond to an HTTP POST request for creating an account.
     *
     * @param accountDto DTO (Data Transfer Object) containing information for creating the account.
     * @return ResponseEntity containing information about the created account.
     * @throws ServiceException Thrown if an error occurs during the account creation.
     */
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody AccountDto accountDto) throws ServiceException {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.OK);
    }


    /**
     * Method to respond to an HTTP GET request for retrieving account information by account number.
     *
     * @param accountNumber Account number for which information needs to be retrieved.
     * @return ResponseEntity containing account information for the specified account number.
     * @throws ServiceException Thrown if an error occurs while retrieving account information.
     */
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) throws ServiceException {
        return new ResponseEntity<>(accountService.findAccount(accountNumber), HttpStatus.OK);
    }


    /**
     * Method to respond to an HTTP POST request for adding credit to an account by account number.
     *
     * @param accountNumber      Account number to which credit will be added.
     * @param withdrawalTransaction WithdrawalTransaction object containing the credit amount.
     * @return ResponseEntity containing information about the credit addition process.
     * @throws InsufficientBalanceException Thrown in case of insufficient balance.
     * @throws ServiceException             Thrown if an error occurs during the credit addition.
     */
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<ResponseDto> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException, ServiceException {
        return new ResponseEntity<>(accountService.debit(accountNumber, withdrawalTransaction.getAmount()), HttpStatus.OK);
    }

    /**
     * Method to respond to an HTTP POST request for withdrawing money from an account by account number.
     *
     * @param accountNumber         Account number from which money will be withdrawn.
     * @param depositTransaction DepositTransaction object containing the withdrawal amount.
     * @return ResponseEntity containing information about the withdrawal process.
     * @throws ServiceException Thrown if an error occurs during the withdrawal process.
     */
    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<ResponseDto> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction depositTransaction) throws ServiceException {
        return new ResponseEntity<>(accountService.credit(accountNumber, depositTransaction.getAmount()), HttpStatus.OK);
    }
}