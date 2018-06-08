package com.nurullah.moneytransfer;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

public class TransferMoneyRestStartup {
	private final static int PORT = 7979;
	private final static String HOST = "http://localhost/";
	
	public static Injector injector = Guice.createInjector(new ApplicationModule());
	
	public static void main(String[] args) {
		URI baseUri = UriBuilder.fromUri(HOST).port(PORT).build();
		ResourceConfig config = new PackagesResourceConfig("com.nurullah.moneytransfer.service");
		try {
			HttpServer server = HttpServerFactory.create(baseUri, config);
			server.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
