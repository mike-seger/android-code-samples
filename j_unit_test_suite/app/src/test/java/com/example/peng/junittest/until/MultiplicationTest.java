package com.example.peng.junittest.until;

import com.example.peng.junittest.Calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.Assert.*;

public class MultiplicationTest {
    @Test
    public void multiplicationTest() throws Exception {
        int expected = 6;
        int actual = Calculator.multiplication(2, 3);
        assertEquals(expected, actual);
    }
}
