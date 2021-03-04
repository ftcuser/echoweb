package com.citizant.seleniumtest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(JunitTestSuite.class);

		for (Failure failure : result.getFailures()) {
			System.out.println("Error:" + failure.toString());
		}

		System.out.println("Successful:" + result.wasSuccessful());
	}
}
