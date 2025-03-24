package com.samsong.reward.config;

import java.util.List;
import java.util.Set;

public class WorkflowConfig {
    final public static Set<State> END_STATE = Set.of(State.ELIGIBILITY_EXPIRED, State.CUSTOMER_NOTIFIED);

    /**
     * user states should be updated in the order defined by this list
     */
    final public static List<State> WORKFLOW = List.of(
            State.ELIGIBLE,
            State.ORDER_PLACED,
			State.BUDGET_RESERVED,
            State.ORDER_SHIPPED,
            State.REWARD_GRANTED,
            State.CUSTOMER_NOTIFIED);
}
