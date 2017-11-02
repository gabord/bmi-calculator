package net.gaborc.domain.dto;

import lombok.Data;

/**
 * Data transfer object for holding a result and interpretation of BMI calculations
 */
@Data
public class BMIResult {
    private String description;
    private double result;
}
