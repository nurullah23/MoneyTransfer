package com.nurullah.moneytransfer.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nurullah.moneytransfer.TransferMoneyRestStartup;
import com.nurullah.moneytransfer.datastore.DataStore;
import com.nurullah.moneytransfer.dto.ResultDTO;
import com.nurullah.moneytransfer.dto.TransactionDTO;
import com.nurullah.moneytransfer.model.TransferStatus;

@Path("transfer")
public class TransferService {

	private static ObjectMapper mapper = new ObjectMapper();

	private DataStore dataStore;
	
	public TransferService() {
		this.dataStore = TransferMoneyRestStartup.injector.getInstance(DataStore.class);
	}
	
	static {
		mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
	
	@GET
	@Path("/history")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getTransactionHistory(@Context Request request, @DefaultValue("50") @QueryParam("limit") int limit) throws JsonProcessingException {
		List<TransactionDTO> result = dataStore.getAllTransactions().stream().limit(limit).map(t -> new TransactionDTO(t)).collect(Collectors.toList());
		String responseJson = mapper.writeValueAsString(result);
		return Response.ok(responseJson).build();
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response transferMoney(@Context Request request, @QueryParam("sender") String senderAccountId,  @QueryParam("receiver")String receiverAccountId, @QueryParam("amount") int amount) throws JsonProcessingException {
		TransactionDTO transaction = dataStore.transferMoney(senderAccountId, receiverAccountId, amount);
		ResultDTO result;
		if (transaction.getStatus() == TransferStatus.COMPLETED) {
			result = ResultDTO.from("Transaction completed successfully");
		}
		else {
			result = ResultDTO.error(transaction.getReason());
		}
		String responseJson = mapper.writeValueAsString(result);
		return Response.ok(responseJson).build();
	}
}
