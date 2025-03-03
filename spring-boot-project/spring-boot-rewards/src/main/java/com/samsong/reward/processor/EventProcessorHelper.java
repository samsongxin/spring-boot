package com.samsong.reward.processor;

import com.samsong.reward.WorkflowEngine;
import com.samsong.reward.config.State;
import com.samsong.reward.dao.customer.DataStoreFactory;

public class EventProcessorHelper {

    public static void process(String customerId, EventProcessor eventProcessor) {
        State currentState = eventProcessor.getStartState();
        save(customerId, currentState);
        while(currentState != eventProcessor.getEndState()){
            currentState = WorkflowEngine.nextState(currentState);
            if(currentState != null){
                save(customerId, currentState);
            }
        }
    }

    private static void save(String customerId, State currentState){
        if(DataStoreFactory.getDataStore().alreadyProcessed(customerId, currentState)){
            throw new IllegalArgumentException("Event already processed; customerId=" + customerId + "; state=" + currentState);
        }else{
            DataStoreFactory.getDataStore().save(customerId, currentState);
        }
    }
}
