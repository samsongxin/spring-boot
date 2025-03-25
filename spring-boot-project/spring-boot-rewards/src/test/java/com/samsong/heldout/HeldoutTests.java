package com.samsong.heldout;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeldoutTests {

	@DisplayName("[+10] are all code written in Java?")
	@Test
	public void isWrittenInJava(){
		assertTrue(FileFinder.findProjectFilesWithExtension("java").size() > 10);
	}

	@DisplayName("[+10] is Maven used as the build tool?")
	@Test
	public void isAMavenProject(){
		assertTrue(FileFinder.doesFileExistInProject("pom.xml"));
	}

	@DisplayName("[+10] are unit tests implemented in Junit5?")
	@Test
	public void usesJunit5(){
		assertTrue(FileFinder.readFileContent("pom.xml").toLowerCase().contains("org.junit.jupiter"));
	}


	@DisplayName("[+3] read me file is created?")
	@Test
	public void isReadMeFileCreated(){
		assertTrue(FileFinder.doesFileExistInProject("readme.md"));
	}

	@Test
	@DisplayName("[+5] are all customer states defined in the enum class State? com.samsong.reward.config.State")
	public void isStateEnumCreated(){
		JavaFileParser parser = new JavaFileParser(FileFinder.findFile("State.java").getAbsolutePath());
		parser.parse();
		assertTrue("com.samsong.reward.config".equalsIgnoreCase(parser.getPackageName()));
	}

	@Test
	@DisplayName("[+5] are all customer states defined in the enum class State? com.samsong.reward.config.State")
	public void isEventTypeEnumCreated(){
		JavaFileParser parser = new JavaFileParser(FileFinder.findFile("EventType.java").getAbsolutePath());
		parser.parse();
		assertTrue("com.samsong.reward.config".equalsIgnoreCase(parser.getPackageName()));
	}

	@Test
	@DisplayName("[+10] is the WorkflowEngine implemented by avoiding hard-coding logic with massive if-else statements?")
	public void isWorkflowEngineCreatedWithoutMassiveIfElse(){
		String content = FileFinder.readFileContent(FileFinder.findFile("WorkflowEngine.java").getAbsolutePath());
		String[] tokens = content.split("else");
		assertTrue(tokens.length < 3);
	}

//	@Test
//	@DisplayName("[+5] Is the reward workflow engine class unit tested?")
//	public void isWorkflowEngineTested(){
//		assertTrue(FileFinder.doesFileExistInProject("WorkflowEngineTest.java") || FileFinder.doesFileExistInProject("TestWorkflowEngine.java"));
//	}

	@Test
	@DisplayName("[+10] is interface EventProcessor created?")
	public void isEventProcessorCreated(){
		assertTrue(FileFinder.doesFileExistInProject("EventProcessor.java"));
	}


	/*
	 * [+10] do all event processors implement interface EventProcessor?
	 * [+5] are all event processors placed in the same package?
	 * [+5] are unit test classes implemented for all processors?
	 * [+10] does the unit tests cover event processing idempotence?  com.samsong.reward.processor.LandingPageVisitProcessorTest#processTest
	 */


	@Test
	@DisplayName("[+10] is customer data DataStore interface created?")
	public void isDataStoreCreated(){
		assertTrue(FileFinder.doesFileExistInProject("DataStore.java"));
	}

	 // [+10] do all data store classes implement DataStore interface?
	//  [+5] are all data store classes placed in the same package?

	@Test
	@DisplayName("[+10] is In-memory data store implemented?")
	public void isInMemoryDataStoreCreated(){
		assertTrue(FileFinder.doesFileExistInProject("InMemoryDataStore.java"));
	}

//	@Test
//	@DisplayName("[+3] is the In-memory data store unit tested?")
//	public void isInMemoryDataStoreTested(){
//		assertTrue(FileFinder.doesFileExistInProject("InMemoryDataStoreTest.java") || FileFinder.doesFileExistInProject("TestInMemoryDataStore.java"));
//	}

	@Test
	@DisplayName("[+10] does in-memory data store implement DataStore interface?")
	public void doesInMemoryDataStoreImplementDataStore(){
		JavaFileParser parser = new JavaFileParser(FileFinder.findFile("customer\\InMemoryDataStore.java").getAbsolutePath());
		parser.parse();
		assertTrue(parser.getClasses().get(0).toString().contains("implements DataStore"));
	}


	// [+3] is DynamoDB stub class created and implemented DataStore interface?  com.samsong.reward.dao.customer.DynamoDBDataStore
}
