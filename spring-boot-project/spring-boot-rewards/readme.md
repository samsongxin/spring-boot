Implement a customer reward workflow system

A configurable customer reward workflow system that tracks customer reward eligibility and processes rewards based on customer actions. The system will integrate with an external advertising service that serves advertisements on landing pages. Customer actions trigger events that advance customers through defined workflow states.

# Work flow
- Given a new customer in state of null
- Customer state becomes ELIGIBLE for rewards after visiting advertisements on a landing page and a LANDING_PAGE_VISIT event is received.
- Customer state becomes ORDER_PLACED when a valid purchase is made and the ORDER_PLACED event is received
- Customer state becomes BUDGET_RESERVED when a budget is reserved successfully by the system on receiving ORDER_PLACED event.
- Customer state becomes ORDER_SHIPPED when the order is shipped and the ORDER_SHIPPED event is received
- Customer state becomes REWARD_GRANTED after the reward is granted to the customer by the system after customer state become ORDER_SHIPPED.
- Customer state becomes CUSTOMER_NOTIFIED after the system sends a notification to the customer after the reward is granted.
- Customer ELIGIBLE state might become ELIGIBILITY_EXPIRED after a specific period (e.g after the advertisement ends) without a purchase.


# Requirements:
- Put all source code into spring-boot\spring-boot-project\spring-boot-rewards\src\
- Implement in Java 17
- Use Maven as the build tool
- Implement unit tests with Junit 5
- With a decent unit test coverage, focus on business logic rather than classes such as enums, constants, etc.
- Each event processing should be idempotent (only process once)
- Create a workflow engine class com.samsong.reward.WorkflowEngine which leverages collection APIs like List, Set, or Map instead of hard-coding logic with massive if-else statements.
- Create an enum class com.samsong.reward.config.State to define all customer state.
- Create an enum class com.samsong.reward.config.EventType to define all events.
- Create a interface com.samsong.reward.EventProcessor and have all event processors implement it.
- Create a customer data interface com.samsong.reward.dao.customer.DataStore, implement an in-memory data store InMemoryDataStore for testing purposes, and create stub classes for DynamoDB, AWS RDP database, and AWS S3.
- Create a com.samsong.reward.RewardService as the entry point of entire service with these two methods, the first method is to retrieve the current state of given customer, the second method is to process an event.
  public State getCustomerState(String customerId);
  public void removeCustomer(String customerId);
  void processEvent(String customerId, EventType eventType);

# To compile rewards module:
- mvn clean install