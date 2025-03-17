package com.samsong.reward.processor;

import com.samsong.reward.Constant;
import com.samsong.reward.config.State;
import com.samsong.reward.dao.customer.DataStoreFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventProcessorHelperTest {

	@Test
	public void saveTestWithEmptyCanaryCustomerID() {
		assertDoesNotThrow(() -> {EventProcessorHelper.save(Constant.CANARY_CUSTOMER_ID, State.ELIGIBLE);});
	}
}
