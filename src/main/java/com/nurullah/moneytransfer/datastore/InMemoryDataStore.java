package com.nurullah.moneytransfer.datastore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Singleton;

import com.nurullah.moneytransfer.dto.ResultDTO;
import com.nurullah.moneytransfer.dto.TransactionDTO;
import com.nurullah.moneytransfer.model.Account;
import com.nurullah.moneytransfer.model.Transaction;
import com.nurullah.moneytransfer.model.TransferStatus;

@Singleton
public final class InMemoryDataStore implements DataStore {
	
	private final List<Account> accounts = new CopyOnWriteArrayList<>();
	private final List<Transaction> transactions = new CopyOnWriteArrayList<>();

	private List<Account> getAccounts() {
		return new ArrayList<>(accounts);
	}

	private List<Transaction> getTransactions() {
		return new ArrayList<>(transactions);
	}

	@Override
	public List<Account> getAllAccounts() {
		return getAccounts();
	}

	@Override
	public Account getAccountById(String accountId) {
		return getAccounts().stream().filter(a -> a.getAccountId().equals(UUID.fromString(accountId))).findFirst().orElse(null);
	}

	@Override
	public Account getAccountByAccountNumber(String accountNumber) {
		return getAccounts().stream().filter(a -> a.getAccountNumber().equals(accountNumber)).findFirst().orElse(null);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return getTransactions();
	};
	
	@Override
	public TransactionDTO transferMoney(String senderAccountNumber, String receiverAccountNumber, double amount) {
		Transaction transaction = new Transaction(senderAccountNumber, receiverAccountNumber, amount);
		if (amount <= 0) {
			transaction.setStatus(TransferStatus.CANCELLED);
			transaction.setReason("Invalid amount");
		}
		else if (senderAccountNumber.equals(receiverAccountNumber)) {
			transaction.setStatus(TransferStatus.CANCELLED);
			transaction.setReason("Same sender and receiver");
		}
		else {
			synchronized (accounts) {
				Account senderAccount = getAccountByAccountNumber(senderAccountNumber);
				Account receiverAccount = getAccountByAccountNumber(receiverAccountNumber);
				if (senderAccount == null) {
					transaction.setStatus(TransferStatus.CANCELLED);
					transaction.setReason("Invalid sender account");
				}
				else if (receiverAccount == null) {
					transaction.setStatus(TransferStatus.CANCELLED);
					transaction.setReason("Invalid receiver account");
				}
				else if (senderAccount.getBalance() < amount) {
					transaction.setStatus(TransferStatus.CANCELLED);
					transaction.setReason("Insufficient funds");
				}
				else {
					transaction.setStatus(TransferStatus.COMPLETED);
					LocalDateTime now = LocalDateTime.now();
					senderAccount.setBalance(senderAccount.getBalance() - amount);
					senderAccount.setLastModificationDate(now);
					receiverAccount.setBalance(receiverAccount.getBalance() + amount);
					receiverAccount.setLastModificationDate(now);
				}
			}
		}
		
		transactions.add(transaction);
		return new TransactionDTO(transaction);
	}

	@Override
	public ResultDTO createNewAccount(String accountNumber, String owner, double balance) {
		if (balance < 0) {
			return ResultDTO.error("Invalid balance");
		}
		else if (owner == null || owner.isEmpty()) {
			return ResultDTO.error("Empty owner");
		}
		else if (accountNumber == null || accountNumber.isEmpty()) {
			return ResultDTO.error("Empty account number");
		}
		else {
			synchronized (accounts) {
				if (accounts.stream().anyMatch(a -> a.getAccountNumber().equals(accountNumber))) {
					return ResultDTO.error("Account already exists");
				}
				else {
					accounts.add(new Account(accountNumber, owner, balance));
				}
			}
			return ResultDTO.from("Account successfully created");
		}
	};
}
