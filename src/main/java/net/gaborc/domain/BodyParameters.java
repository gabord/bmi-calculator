package net.gaborc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Value class for holding a person's basic body parameters
 */
@Data
public class BodyParameters {
    /**
     * The constructor for the body parameters.
     * @param weightInKilograms the persoqn's weight in kilograms
     * @param heightInCentimeters the person's height in kilograms
     */
    public BodyParameters(double weightInKilograms, double heightInCentimeters) {
        this.weightInKilograms = weightInKilograms;
        this.heightInCentimeters = heightInCentimeters;
    }

    private double weightInKilograms;
    private double heightInCentimeters;
}
