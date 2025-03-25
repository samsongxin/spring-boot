package com.samsong.reward;

import com.samsong.reward.config.EventType;
import com.samsong.reward.config.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RewardServiceTest {
	private RewardService target;
	private static final String CUSTOMER_ID = "CUSTOMER_ID_RewardServiceTest";

	@BeforeEach
	void init(){
		target = new RewardService();
	}

	@Test
	@DisplayName("[+10] is initial customer state set to null?")
	public void nextStateOfNewCustomer() {
		target.removeCustomer(CUSTOMER_ID);
		State currentState = target.getCustomerState(CUSTOMER_ID);
		assertNull(currentState);
	}

	@Test
	@DisplayName("[+10] does customer state become ELIGIBLE after landing page visit?")
	public void nextStateOfLPVisit() {
		target.removeCustomer(CUSTOMER_ID);
		target.processEvent(CUSTOMER_ID, EventType.LANDING_PAGE_VISIT);
		State nextState = target.getCustomerState(CUSTOMER_ID);
		assertEquals(State.ELIGIBLE, nextState);
	}

	@Test
	@DisplayName("Test the next state of ORDER_PLACED")
	public void nextStateOfOrderPlaced() {
		target.removeCustomer(CUSTOMER_ID);
		target.processEvent(CUSTOMER_ID, EventType.LANDING_PAGE_VISIT);
		target.processEvent(CUSTOMER_ID, EventType.ORDER_PLACED);
		State nextState = target.getCustomerState(CUSTOMER_ID);
		assertEquals(State.BUDGET_RESERVED, nextState);
	}

	@Test
	@DisplayName("Test the next state of ORDER_SHIPPED")
	public void nextStateOfOrderShipped()  {
		target.removeCustomer(CUSTOMER_ID);
		target.processEvent(CUSTOMER_ID, EventType.LANDING_PAGE_VISIT);
		target.processEvent(CUSTOMER_ID, EventType.ORDER_PLACED);
		target.processEvent(CUSTOMER_ID, EventType.ORDER_SHIPPED);
		State nextState = target.getCustomerState(CUSTOMER_ID);
		assertEquals(State.CUSTOMER_NOTIFIED, nextState);
	}
}
