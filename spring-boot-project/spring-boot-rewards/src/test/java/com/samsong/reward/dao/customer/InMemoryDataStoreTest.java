package com.samsong.reward.dao.customer;

import com.samsong.reward.config.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryDataStoreTest {

    private InMemoryDataStore target;
    final private String CUSTOMER_ID = "customer1";


	@BeforeEach
    void init(){
        target = new InMemoryDataStore();
    }

    @Test
    public void saveTest(){
        target.save(CUSTOMER_ID, State.ELIGIBLE);
        State currentState = target.getCustomerCurrentState(CUSTOMER_ID);
        assertEquals(State.ELIGIBLE, currentState);
    }

    @Test
    public void getCustomerCurrentStateTest(){
        target.save(CUSTOMER_ID, State.ELIGIBILITY_EXPIRED);
        State currentState = target.getCustomerCurrentState(CUSTOMER_ID);
        assertEquals(State.ELIGIBILITY_EXPIRED, currentState);
    }

    @Test
    public void alreadyProcessedTest(){
        target.save(CUSTOMER_ID, State.ELIGIBLE);
        boolean alreadyProcessed = target.alreadyProcessed(CUSTOMER_ID, State.ELIGIBLE);
        assertTrue(alreadyProcessed);
    }
}
