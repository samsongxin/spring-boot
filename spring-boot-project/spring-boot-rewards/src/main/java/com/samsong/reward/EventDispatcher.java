package com.samsong.reward;

import com.samsong.reward.config.EventType;
import com.samsong.reward.processor.EventProcessorFactory;

public class EventDispatcher {
    void dispatch(String customerId, EventType eventType){
        EventProcessorFactory.getProcessor(eventType).process(customerId);
    }
}
