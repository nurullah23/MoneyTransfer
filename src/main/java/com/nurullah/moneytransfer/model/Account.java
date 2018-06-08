package com.nurullah.moneytransfer.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Account implements Serializable {
	private static final long serialVersionUID = -4341156918774816154L;
	
	private final UUID accountId = UUID.randomUUID();
	private final String accountNumber;
	private final String owner;
	private double balance;
	private final String currencyCode = "PLN";
	private final LocalDateTime creationDate;
	private LocalDateTime lastModificationDate;

	public Account(String accountNumber, String owner, double balance,
			LocalDateTime creationDate, LocalDateTime lastModificationDate) {
		this.accountNumber = accountNumber;
		this.owner = owner;
		this.balance = balance;
		this.creationDate = creationDate;
		this.lastModificationDate = lastModificationDate;
	}

	public Account(String accountNumber, String owner, double balance) {
		this.accountNumber = accountNumber;
		this.owner = owner;
		this.balance = balance;
		LocalDateTime now = LocalDateTime.now();
		this.creationDate = now;
		this.lastModificationDate = now;
	}

	public Account(String accountNumber, String owner) {
		this.accountNumber = accountNumber;
		this.owner = owner;
		this.balance = 0;
		LocalDateTime now = LocalDateTime.now();
		this.creationDate = now;
		this.lastModificationDate = now;
	}

	public String getOwner() {
		return owner;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDateTime getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(LocalDateTime lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public UUID getAccountId() {
		return accountId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result
				+ ((accountNumber == null) ? 0 : accountNumber.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime
				* result
				+ ((lastModificationDate == null) ? 0 : lastModificationDate
						.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (lastModificationDate == null) {
			if (other.lastModificationDate != null)
				return false;
		} else if (!lastModificationDate.equals(other.lastModificationDate))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
}
