package com.samsong.reward;

import com.samsong.reward.config.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WorkflowEngineTest {

    @Test
    @DisplayName("Test next state of a non-end state")
    public void nextStateOfEligible() {
        State nextState = WorkflowEngine.nextState(State.ELIGIBLE);
        assertEquals(State.ORDER_PLACED, nextState);
    }

    @Test
    public void nextStateIsNull() {
        State nextState = WorkflowEngine.nextState(State.ELIGIBILITY_EXPIRED);
        assertNull(nextState);
    }
}
