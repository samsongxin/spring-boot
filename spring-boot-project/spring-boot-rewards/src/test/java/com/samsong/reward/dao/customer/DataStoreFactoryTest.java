package com.samsong.reward.dao.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataStoreFactoryTest {

    @Test
    public void getDataStoreTest(){
        assertInstanceOf(InMemoryDataStore.class, DataStoreFactory.getDataStore());
    }
}
