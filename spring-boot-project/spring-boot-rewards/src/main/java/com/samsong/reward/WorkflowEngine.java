package com.samsong.reward;

import com.samsong.reward.config.State;
import com.samsong.reward.config.WorkflowConfig;

public class WorkflowEngine {
	public static State nextState(State state){
        if(WorkflowConfig.END_STATE.contains(state)) {
            return null;
        }
         return WorkflowConfig.WORKFLOW.get(WorkflowConfig.WORKFLOW.indexOf(state)+1);
    }
}
