package com.samsong.reward.cache;

import com.samsong.reward.config.State;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A LRU cache to store customer states with limited capacity to avoid heap overflow.
 */
public class LRUCustomerStateCache extends LinkedHashMap<String, List<State>> implements CustomerStateCache {
    private int capacity;

    public LRUCustomerStateCache(int capacity){
        this.capacity = capacity;
    }

    @Override
    public List<State> getCustomerStates(String customerId) {
        return this.getOrDefault(customerId, null);
    }

    @Override
    public void addCustomerState(String customerId, State state) {
        this.putIfAbsent(customerId, new ArrayList<>());
        this.get(customerId).add(state);
    }

    @Override
    public int cacheSize() {
        return size();
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, List<State>> eldest) {
        return size() > capacity;
    }
}
