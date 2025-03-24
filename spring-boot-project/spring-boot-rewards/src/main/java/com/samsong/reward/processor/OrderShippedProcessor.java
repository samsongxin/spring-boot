package com.samsong.reward.processor;

import com.samsong.reward.config.State;

public class OrderShippedProcessor implements EventProcessor{
    @Override
    public void process(String customerId) {
        EventProcessorHelper.process(customerId,this);
    }

	@Override
	public State getStartState() {
		return State.ORDER_SHIPPED;
	}

    @Override
    public State getEndState() {
        return State.CUSTOMER_NOTIFIED;
    }


}