package com.nurullah.moneytransfer.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nurullah.moneytransfer.model.Transaction;
import com.nurullah.moneytransfer.model.TransferStatus;

@JsonInclude(Include.NON_NULL)
public class TransactionDTO implements Serializable {
	private static final long serialVersionUID = -6076611086980952178L;
	
	private UUID transactionId;
	private String senderAccountId;
	private String receiverAccountId;
	private double amount;
	private final String currencyCode;
	private LocalDateTime transactionDate;
	private TransferStatus status;
	private String reason;
	
	public TransactionDTO(UUID transactionId, String senderAccountId,
			String receiverAccountId, double amount, String currencyCode,
			LocalDateTime transactionDate, TransferStatus status) {
		this.transactionId = transactionId;
		this.senderAccountId = senderAccountId;
		this.receiverAccountId = receiverAccountId;
		this.amount = amount;
		this.currencyCode = currencyCode;
		this.transactionDate = transactionDate;
		this.status = status;
	}
	
	public TransactionDTO(Transaction transaction) {
		this.transactionId = transaction.getTransactionId();
		this.senderAccountId = transaction.getSenderAccountId();
		this.receiverAccountId = transaction.getReceiverAccountId();
		this.amount = transaction.getAmount();
		this.currencyCode = transaction.getCurrencyCode();
		this.transactionDate = transaction.getTransactionDate();
		this.status = transaction.getStatus();
		this.reason = transaction.getReason();
	}

	public UUID getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}

	public String getSenderAccountId() {
		return senderAccountId;
	}

	public void setSenderAccountId(String senderAccountId) {
		this.senderAccountId = senderAccountId;
	}

	public String getReceiverAccountId() {
		return receiverAccountId;
	}

	public void setReceiverAccountId(String receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransferStatus getStatus() {
		return status;
	}

	public void setStatus(TransferStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCurrencyCode() {
		return currencyCode;
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
		result = prime
				* result
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
		TransactionDTO other = (TransactionDTO) obj;
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
