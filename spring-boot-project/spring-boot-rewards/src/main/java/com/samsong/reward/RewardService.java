package com.samsong.reward;

import com.samsong.reward.config.EventType;
import com.samsong.reward.config.State;
import com.samsong.reward.dao.customer.DataStoreFactory;
import com.samsong.reward.processor.EventProcessorFactory;

public class RewardService {
	public State getCustomerState(String customerId){
		return DataStoreFactory.getDataStore().getCustomerCurrentState(customerId);
	}

	public void removeCustomer(String customerId){
		DataStoreFactory.getDataStore().purgeCustomerData(customerId);
	}

	void processEvent(String customerId, EventType eventType){
		EventProcessorFactory.getProcessor(eventType).process(customerId);
	}
}
