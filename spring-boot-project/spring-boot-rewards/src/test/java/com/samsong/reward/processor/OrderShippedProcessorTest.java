package com.samsong.reward.processor;

import com.samsong.reward.config.State;
import com.samsong.reward.dao.customer.DataStoreFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderShippedProcessorTest {
    private OrderShippedProcessor target;
    final private String CUSTOMER_ID = "customer2";

    @BeforeEach
    void init(){
        target = new OrderShippedProcessor();
    }

    @Test
    public void processTest() {
        target.process(CUSTOMER_ID);
        State state = DataStoreFactory.getDataStore().getCustomerCurrentState(CUSTOMER_ID);
        assertEquals(State.CUSTOMER_NOTIFIED, state);

        assertThrows(IllegalArgumentException.class, () -> target.process(CUSTOMER_ID));
    }
}
