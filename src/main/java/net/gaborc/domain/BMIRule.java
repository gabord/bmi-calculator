package net.gaborc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Value class for holding BMI ranges and associated description (e.g. persons with a BMI between 0 and 17,9 should be considered underweight)
 */
@Data
public class BMIRule {
    private double min;
    private double max;
    private String description;

    /**
     * The main constructor for BMIRule.
     * @param min the minimum of the range for the rule (inclusive)
     * @param max the maximum of the range for the rule (non-inclusive)
     * @param description the description for the rule (e.g. "Overweight)
     */
    public BMIRule(double min, double max, String description) {
        this.description = description;
        this.min = min;
        this.max = max;
    }

    /**
     * Checks if a given BMI number is inside the range of this rule.
     * @param bmi
     * @return a boolean value
     */
    public boolean isBetweenRange(double bmi) {
        return bmi >= min && bmi <= max;
    }
}
