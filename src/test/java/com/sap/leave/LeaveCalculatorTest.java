
package com.sap.leave;

import org.junit.Test;
import static org.junit.Assert.*;

public class LeaveCalculatorTest {

    LeaveCalculator calculator = new LeaveCalculator();

    // Test 1: Remaining leave calculation
    @Test
    public void testRemainingLeave() {
        int result = calculator.calculateRemainingLeave(24, 8);
        assertEquals(16, result);
    }

    // Test 2: Leave encashment calculation
    @Test
    public void testLeaveEncashment() {
        double result =
            calculator.calculateLeaveEncashment(10, 1500.0);

        assertEquals(15000.0, result, 0.001);
    }

    // Test 3: Invalid leave input
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLeave() {
        calculator.calculateRemainingLeave(10, 15);
    }
}
