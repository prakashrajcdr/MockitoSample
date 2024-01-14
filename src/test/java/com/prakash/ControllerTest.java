package com.prakash;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.prakash.customerservice")
class ControllerTest {

	@Test
	public void test() {
		assertTrue(true);
	}

}
