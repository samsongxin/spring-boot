package com.samsong.reward.dao.customer;

import com.samsong.reward.config.State;

import java.util.*;

/**
 * An In memory data store for testing
 */
public class InMemoryDataStore implements DataStore{
    private final Map<String, List<State>> inMemoryDB = new HashMap<>();
    @Override
    public void save(String customerId, State state) {
        inMemoryDB.putIfAbsent(customerId, new ArrayList<>());
        inMemoryDB.get(customerId).add(state);
    }

    @Override
    public State getCustomerCurrentState(String customerId) {
        if(!inMemoryDB.containsKey(customerId)){
            return null;
        }else{
            return inMemoryDB.get(customerId).get(inMemoryDB.get(customerId).size() - 1);
        }
    }

    @Override
    public boolean alreadyProcessed(String customerId, State state) {
        return inMemoryDB.containsKey(customerId) && inMemoryDB.get(customerId).contains(state);
    }

    @Override
    public void purgeCustomerData(String customerId) {
        inMemoryDB.remove(customerId);
    }
}
