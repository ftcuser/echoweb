package com.citizant.seleniumtest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ PassionTeaTest.class, PassionTeaRecordedTest.class })

public class JunitTestSuite {
}