Implement a configurable customer reward workflow.

When customers take action, certain events will be generated, which will be processed and put the customer in an appropriate workflow state.

# Work flow
- customer becomes eligible for rewards after visiting the landing page
- customer eligibility might expire and customer become ineligible
- reserve budget once a customer makes a valid purchase and the order placed event is received
- Grant a reward to the customer when the order is shipped is received if the budget was reserved
- send the customer a notification once the reward is granted.

# Requirements:

- implement in Java
- use Maven as the build tool
- implement unit tests with Junit 5
- Create an interface EventProcessor and have all event processors implement it.
- create a customer data interface DataStore, implement an in-memory data store for testing purposes, and create stub classes for DynamoDB, AWS RDP database, and AWS S3.
- create a customer state cache interface CustomerStateCache, implement LRU cache, create stub implementation for LFU
- with a decent unit test coverage, focus on business logic rather than classes such as enums, constants, etc.
- the workflow should be driven by configurations rather than hard-coded (avoid massive if-else statements)
- each event processing should be idempotent (only process once)

# To compile rewards module:
- mvn clean install