package com.samsong.reward.dao.customer;

import com.samsong.reward.config.State;

public class AWSRDPDataStore implements DataStore{
    @Override
    public void save(String customerId, State state) {
        //TODO implement
        throw new UnsupportedOperationException("This method needs to be implemented");
    }

    @Override
    public State getCustomerCurrentState(String customerId) {
        //TODO implement
        throw new UnsupportedOperationException("This method needs to be implemented");
    }

    @Override
    public boolean alreadyProcessed(String customerId, State state) {
        throw new UnsupportedOperationException("This method needs to be implemented");
    }

    @Override
    public void purgeCustomerData(String customerId) {
        throw new UnsupportedOperationException("This method needs to be implemented");
    }
}
