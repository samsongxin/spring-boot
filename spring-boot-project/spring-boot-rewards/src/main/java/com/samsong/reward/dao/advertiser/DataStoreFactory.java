package com.samsong.reward.dao.advertiser;

public class DataStoreFactory {
    private final static InMemoryDataStore inMemoryDataStore = new InMemoryDataStore();
    public static DataStore getDataStore(){
        return inMemoryDataStore;
    }
}
