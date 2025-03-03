package com.samsong.reward.cache;

import com.samsong.reward.config.State;

import java.util.List;

public interface CustomerStateCache {
    List<State> getCustomerStates(String customerId);
    void addCustomerState(String customerId, State state);
    int cacheSize();
}
