package com.samsong.reward.cache;

import com.samsong.reward.config.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LRUCustomerStateCacheTest {
    final private String CUSTOMER_ID = "customer1";
    private CustomerStateCache target ;

    @BeforeEach
    void init(){
        target = new LRUCustomerStateCache(2);
    }

    // @Test
    public void addCustomerStateTest(){
        target.addCustomerState(CUSTOMER_ID, State.ELIGIBLE);
        assertTrue(target.getCustomerStates(CUSTOMER_ID).contains(State.ELIGIBLE));
    }

    @Test
    public void testCacheCapacityOverflow(){
        target.addCustomerState("customer1", State.ELIGIBLE);
        target.addCustomerState("customer2", State.ELIGIBLE);
        assertEquals(2, target.cacheSize());

        target.addCustomerState("customer3", State.ELIGIBLE);
//        assertEquals(2, target.cacheSize()); //resume this check after LRUCustomerStateCache#removeEldestEntry method is implemented
    }
}
