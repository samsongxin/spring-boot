package com.samsong.reward.processor;

import com.samsong.reward.config.EventType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class EventProcessorFactoryTest {
    @Test
    public void getProcessorForLandingPageVisitEvent(){
        assertInstanceOf(LandingPageVisitProcessor.class, EventProcessorFactory.getProcessor(EventType.LANDING_PAGE_VISIT));
    }

    @Test
    public void getProcessorForOrderPlacedEvent(){
        assertInstanceOf(OrderPlacedProcessor.class, EventProcessorFactory.getProcessor(EventType.ORDER_PLACED));
    }

    @Test
    public void getProcessorForOrderShippedEvent(){
        assertInstanceOf(OrderShippedProcessor.class, EventProcessorFactory.getProcessor(EventType.ORDER_SHIPPED));
    }
}
