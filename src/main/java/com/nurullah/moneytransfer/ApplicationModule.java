package com.nurullah.moneytransfer;

import com.google.inject.AbstractModule;
import com.nurullah.moneytransfer.datastore.DataStore;
import com.nurullah.moneytransfer.datastore.InMemoryDataStore;

public class ApplicationModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DataStore.class).to(InMemoryDataStore.class);
	}
}
