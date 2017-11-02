package net.gaborc.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BMIRuleTest {

    BMIRule bmiRule;

    @Test
    void isBetweenRangeShouldReturnTrueWhenInRange() {
        bmiRule = new BMIRule(10,20, "normal");
        assertTrue(bmiRule.isBetweenRange(15));
        assertTrue(bmiRule.isBetweenRange(10));
        assertTrue(bmiRule.isBetweenRange(20));
    }

    @Test
    void isBetweenRangeShouldReturnFalseWhenNotInRange() {
        bmiRule = new BMIRule(10,20, "normal");
        assertFalse(bmiRule.isBetweenRange(9));
        assertFalse(bmiRule.isBetweenRange(21));
    }
}