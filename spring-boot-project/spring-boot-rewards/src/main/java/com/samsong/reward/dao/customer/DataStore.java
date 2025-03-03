package com.samsong.reward.dao.customer;

import com.samsong.reward.config.State;

public interface DataStore {
    void save(String customerId, State state);
    State getCustomerCurrentState(String customerId);
    boolean alreadyProcessed(String customerId, State state);
    void purgeCustomerData(String customerId);
}
