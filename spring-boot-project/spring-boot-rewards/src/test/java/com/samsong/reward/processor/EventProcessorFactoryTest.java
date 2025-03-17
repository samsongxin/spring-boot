package com.samsong.reward.processor;

import com.samsong.reward.config.EventType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class EventProcessorFactoryTest {
    @Test
    public void getProcessorForLandingPageVisitEvent(){
        assertInstanceOf(LandingPageVisitProcessor.class, EventProcessorFactory.getProcessor(EventType.LANDING_PAGE_VISIT));
    }
}
