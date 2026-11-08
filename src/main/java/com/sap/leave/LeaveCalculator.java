
package com.sap.leave;

public class LeaveCalculator {

    // Calculate remaining employee leave balance
    public int calculateRemainingLeave(int totalLeave, int usedLeave) {
        if (totalLeave < 0 || usedLeave < 0) {
            throw new IllegalArgumentException("Leave cannot be negative");
        }

        if (usedLeave > totalLeave) {
            throw new IllegalArgumentException(
                "Used leave cannot exceed total leave"
            );
        }

        return totalLeave - usedLeave;
    }

    // Calculate leave encashment amount
    public double calculateLeaveEncashment(
            int remainingLeave, double dailyRate) {

        if (remainingLeave < 0 || dailyRate < 0) {
            throw new IllegalArgumentException(
                "Leave and daily rate cannot be negative"
            );
        }

        return remainingLeave * dailyRate;
    }
}
// BITS ID: 2024SL70047
