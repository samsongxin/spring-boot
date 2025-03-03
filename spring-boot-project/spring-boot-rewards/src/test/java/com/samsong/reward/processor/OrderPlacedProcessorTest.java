package com.samsong.reward.processor;

import com.samsong.reward.config.State;
import com.samsong.reward.dao.customer.DataStoreFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderPlacedProcessorTest {
    private OrderPlacedProcessor target;
    final private String CUSTOMER_ID = "customer3";

    @BeforeEach
    void init(){
        target = new OrderPlacedProcessor();
    }

    @Test
    public void processTest() {
        target.process(CUSTOMER_ID);
        State state = DataStoreFactory.getDataStore().getCustomerCurrentState(CUSTOMER_ID);
        assertEquals(State.BUDGET_RESERVED, state);

        assertThrows(IllegalArgumentException.class, () -> target.process(CUSTOMER_ID));
    }
}
