package com.betamedia.atom.core.api.tp.entities.response;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CRMAccountTest {

	@Test
  	public void testShouldCheckDefinedEnums() {
		assertEquals("panda", CRMAccount.PlatformType.PANDA.getValue());
		assertEquals("scipio", CRMAccount.PlatformType.SCIPIO.getValue());
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testShouldCheckIncorrectNameForEnum() {
		CRMAccount.PlatformType.parse("incorrectName");
	}
}
