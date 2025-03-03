package com.samsong.reward.dao.customer;

import com.samsong.reward.config.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DynamoDBDataStoreTest {

    private DynamoDBDataStore target;
    final private String CUSTOMER_ID = "customer1";


    @BeforeEach
    void init(){
        target = new DynamoDBDataStore();
    }

    @Test
    public void saveTest(){
        assertThrows(UnsupportedOperationException.class, () -> target.save(CUSTOMER_ID, State.ELIGIBLE));
    }

    @Test
    public void getCustomerCurrentStateTest(){
        assertThrows(UnsupportedOperationException.class, () -> target.getCustomerCurrentState(CUSTOMER_ID));
    }

    @Test
    public void alreadyProcessedTest(){
        assertThrows(UnsupportedOperationException.class, () -> target.alreadyProcessed(CUSTOMER_ID, State.ELIGIBLE));
    }
}
