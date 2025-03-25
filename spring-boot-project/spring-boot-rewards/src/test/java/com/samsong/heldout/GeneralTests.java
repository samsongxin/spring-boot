package com.samsong.heldout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneralTests {

	@Test
	public void isWrittenInJava(){
		assertTrue(FileFinder.findProjectFilesWithExtension("java").size() > 10);
	}

	@Test
	public void isAMavenProject(){
		assertTrue(FileFinder.doesFileExistInProject("pom.xml"));
	}

	/**
	 * are unit tests implemented in Junit5?
	 */
	@Test
	public void usesJunit5(){
		assertTrue(FileFinder.readFileContent("pom.xml").toLowerCase().contains("org.junit.jupiter"));
	}

	@Test
	public void isReadMeFileCreated(){
		assertTrue(FileFinder.doesFileExistInProject("readme.md"));
	}

	/**
	 * are all customer states defined in the enum class State? com.samsong.reward.config.State
	 */
}
