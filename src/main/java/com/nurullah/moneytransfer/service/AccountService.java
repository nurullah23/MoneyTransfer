package com.nurullah.moneytransfer.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nurullah.moneytransfer.TransferMoneyRestStartup;
import com.nurullah.moneytransfer.datastore.DataStore;
import com.nurullah.moneytransfer.dto.AccountDTO;
import com.nurullah.moneytransfer.dto.ResultDTO;
import com.nurullah.moneytransfer.model.Account;

@Path("account")
public class AccountService {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private DataStore dataStore;
	
	public AccountService() {
		this.dataStore = TransferMoneyRestStartup.injector.getInstance(DataStore.class);
	}

	@GET
	@Path("/all")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getAllAccounts(@Context Request request) throws JsonProcessingException {
		List<AccountDTO> result = dataStore.getAllAccounts().stream().map(a -> new AccountDTO(a)).collect(Collectors.toList());
		String resultJson = mapper.writeValueAsString(ResultDTO.from(result));
		return Response.ok(resultJson).build();
	}

	@GET
	@Path("/{accountNumber}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getAccountByAccountNumber(@Context Request request, @QueryParam("accountNumber") String accountNumber) throws JsonProcessingException {
		Account account = dataStore.getAccountByAccountNumber(accountNumber);
		ResultDTO result = account != null ?
				ResultDTO.from(new AccountDTO(account)) : 
				ResultDTO.error("Invalid account number");
		String resultJson = mapper.writeValueAsString(result);
		return Response.ok(resultJson).build();
	}

	@POST
	@Path("/create")
	@Consumes("application/json")
	@Produces("application/json")
	public Response createNewAccount(@Context Request request, @QueryParam("accountNumber") String accountNumber,
			@QueryParam("owner") String owner, @DefaultValue("0") @QueryParam("amount") double balance) throws JsonProcessingException {
		ResultDTO result = dataStore.createNewAccount(accountNumber, owner, balance);
		String responseJson = mapper.writeValueAsString(result);
		return Response.ok(responseJson).build();
	}
}
