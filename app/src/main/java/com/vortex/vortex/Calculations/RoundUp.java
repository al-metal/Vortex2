package com.vortex.vortex.Calculations;

import java.math.BigDecimal;

public class RoundUp {
    public static BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
}
