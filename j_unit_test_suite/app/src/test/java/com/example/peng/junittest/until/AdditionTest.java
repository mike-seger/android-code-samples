package com.example.peng.junittest.until;

import com.example.peng.junittest.Calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;


public class AdditionTest {
    @Test
    public void additionTest() throws Exception {
        //Fail this on purpose
        int expected = 5;
        int actual = Calculator.addition(2, 2);
        assertEquals(expected, actual);
    }
}