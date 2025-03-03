package com.samsong.reward.processor;

import com.samsong.reward.config.State;

public class OrderPlacedProcessor implements EventProcessor{
    @Override
    public void process(String customerId) {
        EventProcessorHelper.process(customerId,this);
    }

    @Override
    public State getStartState() {
        return State.ORDER_PLACED;
    }

    @Override
    public State getEndState() {
        return State.BUDGET_RESERVED;
    }


}
