package com.samsong.reward.cache;

import com.samsong.reward.config.State;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A LRU cache to store customer states with limited capacity to avoid heap overflow.
 */
public class LFUCustomerStateCache implements CustomerStateCache {
    private int capacity;

    public LFUCustomerStateCache(int capacity){
        this.capacity = capacity;
    }

    @Override
    public List<State> getCustomerStates(String customerId) {
        //TODO implement
        throw new UnsupportedOperationException("This method needs to be implemented");
    }

    @Override
    public void addCustomerState(String customerId, State state) {
        //TODO implement
        throw new UnsupportedOperationException("This method needs to be implemented");
    }

    @Override
    public int cacheSize() {
        //TODO implement
        throw new UnsupportedOperationException("This method needs to be implemented");
    }
}
