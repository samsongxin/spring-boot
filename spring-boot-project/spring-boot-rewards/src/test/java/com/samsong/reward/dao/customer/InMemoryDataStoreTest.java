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

	@Test
	public void purgeCustomerTest(){
		target.save(CUSTOMER_ID, State.ELIGIBILITY_EXPIRED);
		target.purgeCustomerData(CUSTOMER_ID);
		assertNull(target.getCustomerCurrentState(CUSTOMER_ID));
	}

	@Test
	public void purgeMultipleCustomerTest(){
		target.save(CUSTOMER_ID, State.ELIGIBILITY_EXPIRED);
		String CUSTOMER_ID2 = "customer2";
		target.save(CUSTOMER_ID2, State.CUSTOMER_NOTIFIED);
		target.purgeCustomerData(CUSTOMER_ID);
		target.purgeCustomerData(CUSTOMER_ID2);
		assertNull(target.getCustomerCurrentState(CUSTOMER_ID2));
	}
}
