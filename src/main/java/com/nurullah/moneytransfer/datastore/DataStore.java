package com.nurullah.moneytransfer.datastore;

import java.util.List;

import com.nurullah.moneytransfer.dto.ResultDTO;
import com.nurullah.moneytransfer.dto.TransactionDTO;
import com.nurullah.moneytransfer.model.Account;
import com.nurullah.moneytransfer.model.Transaction;

public interface DataStore {
	List<Account> getAllAccounts();
	Account getAccountById(String accountId);
	Account getAccountByAccountNumber(String accountNumber);
	List<Transaction> getAllTransactions();
	TransactionDTO transferMoney(String senderAccountNumber, String receiverAccountNumber, double amount);
	ResultDTO createNewAccount(String accountNumber, String owner, double balance);
}
