package com.samsong.reward.dao.customer;

public class DataStoreFactory {
    private final static InMemoryDataStore inMemoryDataStore = new InMemoryDataStore();
    public static DataStore getDataStore(){
        return inMemoryDataStore;
    }
}
