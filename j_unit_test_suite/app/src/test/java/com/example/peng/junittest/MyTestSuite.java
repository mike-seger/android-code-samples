package com.example.peng.junittest;


import com.example.peng.junittest.until.AdditionTest;
import com.example.peng.junittest.until.MultiplicationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdditionTest.class,
        MultiplicationTest.class
})
public class MyTestSuite {
}
