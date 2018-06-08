package com.nurullah.moneytransfer.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction implements Serializable {
	private static final long serialVersionUID = -7164625804181641987L;
	
	private final UUID transactionId = UUID.randomUUID();
	private final String senderAccountId;
	private final String receiverAccountId;
	private final double amount;
	private final String currencyCode = "PLN";
	private final LocalDateTime transactionDate;
	private TransferStatus status = TransferStatus.PENDING;
	private String reason;

	public Transaction(String senderAccountId, String receiverAccountId, double amount) {
		this.senderAccountId = senderAccountId;
		this.receiverAccountId = receiverAccountId;
		this.amount = amount;
		this.transactionDate = LocalDateTime.now();
	}

	public UUID getTransactionId() {
		return transactionId;
	}

	public String getSenderAccountId() {
		return senderAccountId;
	}

	public String getReceiverAccountId() {
		return receiverAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public TransferStatus getStatus() {
		return status;
	}

	public void setStatus(TransferStatus status) {
		this.status = status;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result
				+ ((receiverAccountId == null) ? 0 : receiverAccountId
						.hashCode());
		result = prime * result
				+ ((senderAccountId == null) ? 0 : senderAccountId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result
				+ ((transactionId == null) ? 0 : transactionId.hashCode());
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
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double
				.doubleToLongBits(other.amount))
			return false;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (receiverAccountId == null) {
			if (other.receiverAccountId != null)
				return false;
		} else if (!receiverAccountId.equals(other.receiverAccountId))
			return false;
		if (senderAccountId == null) {
			if (other.senderAccountId != null)
				return false;
		} else if (!senderAccountId.equals(other.senderAccountId))
			return false;
		if (status != other.status)
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}
}
