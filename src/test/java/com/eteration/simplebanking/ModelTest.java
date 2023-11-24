package com.eteration.simplebanking;



import com.eteration.simplebanking.model.database.entity.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.database.entity.Transaction;
import com.eteration.simplebanking.model.utils.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
	
	@Test
	public void testCreateAccountAndSetBalance0() {
		Account account = new Account("Kerem Karaca", "17892",0.00);
		assertTrue(account.getOwner().equals("Kerem Karaca"));
		assertTrue(account.getAccountNumber().equals("17892"));
		assertTrue(account.getBalance() == 0);
	}

	@Test
	public void testDepositIntoBankAccount() {
		Account account = new Account("Demet Demircan", "9834");
		account.post(new DepositTransaction(100));
		assertTrue(account.getBalance() == 100);
	}

	@Test
	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
		Account account = new Account("Demet Demircan", "9834");
		account.post(new DepositTransaction(100));
		assertTrue(account.getBalance() == 100);
		account.post(new WithdrawalTransaction(50));
		assertTrue(account.getBalance() == -50);
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows( InsufficientBalanceException.class, () -> {
			Account account = new Account("Demet Demircan", "9834");
			account.deposit(100.00);
			account.withdraw(500.00);
		});

	}



	@Test
	public void testTransactions() throws InsufficientBalanceException {
		// Create account
		Account account = new Account("Canan Kaya", "1234");
		assertNull(account.getTransactions());

		// Deposit Transaction
		DepositTransaction depositTrx = new DepositTransaction(100);
		assertFalse(depositTrx.getDate() != null);
		account.post(depositTrx);
		assertTrue(account.getBalance() == 100);
		assertTrue(account.getTransactions().size() == 1);

		// Withdrawal Transaction
		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60 , new Date());
		assertTrue(withdrawalTrx.getDate() != null);
		account.post(withdrawalTrx);
		assertTrue(account.getBalance() == -60);
		assertTrue(account.getTransactions().size() == 2);
	}
}
