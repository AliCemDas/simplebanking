package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.model.database.ejb.AccountRepository;
import com.eteration.simplebanking.model.database.ejb.DepositTransactionRepository;
import com.eteration.simplebanking.model.database.ejb.WithdrawalTransactionRepository;
import com.eteration.simplebanking.model.database.entity.Account;
import com.eteration.simplebanking.model.dto.AccountDto;
import com.eteration.simplebanking.model.dto.ResponseDto;
import com.eteration.simplebanking.model.utils.InsufficientBalanceException;
import com.eteration.simplebanking.model.utils.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static com.eteration.simplebanking.model.utils.Constants.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final WithdrawalTransactionRepository withdrawalTransactionRepository;
    private final DepositTransactionRepository depositTransactionRepository;

    public ResponseDto createAccount(AccountDto dto) throws ServiceException {

        Account account = accountRepository.save(
                Account.builder()
                        .accountNumber(dto.getAccountNumber())
                        .balance(dto.getBalance())
                        .createDate(new Date())
                        .owner(dto.getOwner())
                        .build());

        log.info("Account Created .Id : {}  Acoount Number : {} Balance : {} CreatedDate {} ",
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getCreateDate());

        return ResponseDto.builder().status(OK).approvalCode(account.getId().toString()).build();
    }

    public Account findAccount(String accountNumber) throws ServiceException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null)
            throw new ServiceException(ERROR_MESSAGE_ACCOUNT_NOT_FOUND);
        return account;
    }

    public ResponseDto debit(String accountNumber, double amount) throws InsufficientBalanceException, ServiceException {
        Account account = findAccount(accountNumber);
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
        } else
            throw new InsufficientBalanceException(ERROR_MESSAGE_BALANCE_NOT_ENOUGH);

        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(amount);
        withdrawalTransaction.setAmount(amount);
        withdrawalTransaction.setAccount(account);
        withdrawalTransaction.setDate(new Date());
        withdrawalTransactionRepository.save(withdrawalTransaction);

        return ResponseDto.builder().status(OK).approvalCode(UUID.randomUUID().toString()).build();
    }

    public ResponseDto credit(String accountNumber, double amount) throws ServiceException {
        Account account = findAccount(accountNumber);
        Double currentBalance = account.getBalance() + amount;
        account.setBalance(currentBalance);
        accountRepository.save(account);

        DepositTransaction depositTransaction = new DepositTransaction(amount);
        depositTransaction.setAccount(account);
        depositTransaction.setDate(new Date());
        depositTransactionRepository.save(depositTransaction);

        return ResponseDto.builder().status(OK).approvalCode(UUID.randomUUID().toString()).build();

    }

    public ResponseDto billPayment(String accountNumber, BillPaymentTransaction billPaymentTransaction) throws InsufficientBalanceException, ServiceException {
        try {
            debit(accountNumber, billPaymentTransaction.getAmount());
        } catch (Exception e) {
            throw e;
        }
        return ResponseDto.builder().status(OK).approvalCode(UUID.randomUUID().toString()).build();
    }
}
