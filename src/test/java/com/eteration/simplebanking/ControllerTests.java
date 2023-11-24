package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.model.database.ejb.AccountRepository;
import com.eteration.simplebanking.model.database.ejb.DepositTransactionRepository;
import com.eteration.simplebanking.model.database.ejb.WithdrawalTransactionRepository;
import com.eteration.simplebanking.model.database.entity.Account;
import com.eteration.simplebanking.model.dto.ResponseDto;
import com.eteration.simplebanking.model.utils.InsufficientBalanceException;
import com.eteration.simplebanking.services.AccountService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {

    @Spy
    @InjectMocks
    private AccountController controller;

    @Mock
    private AccountService service;

    @Mock
    private  AccountRepository accountRepository;

    @Mock
    private  DepositTransactionRepository depositTransactionRepository;

    @Mock
    private WithdrawalTransactionRepository withdrawalTransactionRepository;

    @Test
    public void givenId_Credit_thenReturnJson()
            throws Exception {

        // Given
        Account account = new Account("Kerem Karaca", "17892");
        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(1000.0);

        // When
        doReturn(account).when(service).findAccount("17892");
        doReturn(withdrawalTransaction).when(withdrawalTransactionRepository).save(withdrawalTransaction);
        ResponseEntity<ResponseDto> result = controller.debit("17892", withdrawalTransaction);

        // Then
        assertEquals("OK", result.getStatusCode().getReasonPhrase());
    }
    
    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson()
            throws Exception {

        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<ResponseDto> result = controller.debit( "17892", new WithdrawalTransaction(1000.0));
        ResponseEntity<ResponseDto> result2 = controller.credit( "17892", new DepositTransaction(50.0));
        //verify(service, times(2)).findAccount("17892");
        assertEquals("OK", result.getStatusCode().getReasonPhrase());
        assertEquals("OK", result2.getStatusCode().getReasonPhrase());
    }



}

