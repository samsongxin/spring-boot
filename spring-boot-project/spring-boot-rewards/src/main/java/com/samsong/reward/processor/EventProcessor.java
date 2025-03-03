package com.samsong.reward.processor;

import com.samsong.reward.config.State;

public interface EventProcessor {
    void process(String customerId);
    State getStartState();
    State getEndState();
}
