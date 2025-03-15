package com.samsong.reward.processor;

import com.samsong.reward.config.EventType;

public class EventProcessorFactory {
    public static EventProcessor getProcessor(EventType eventType){
        EventProcessor eventProcessor;
        switch (eventType){
            case LANDING_PAGE_VISIT: eventProcessor = new LandingPageVisitProcessor(); break;
            case ORDER_PLACED: eventProcessor = new OrderPlacedProcessor(); break;
            case ORDER_SHIPPED: eventProcessor = new OrderShippedProcessor(); break;
            default: throw new IllegalArgumentException("Unknown eventType="+ eventType);
        }
        return eventProcessor;
    }
}
