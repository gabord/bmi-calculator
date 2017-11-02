package net.gaborc.service;

import net.gaborc.domain.BMIRule;
import net.gaborc.domain.BodyParameters;
import net.gaborc.domain.dto.BMIResult;

import java.util.List;

/**
 * Services relating to the calculation of BMI values
 */
public interface BMIService {
    /**
     * Returns a BMI result object
     * @param rules a set of rules against which to compare the resulting BMI
     * @param bodyParameters the person's body parameters
     * @return a BMI result object with the resulting BMI and its interpretation.
     */
    BMIResult calculateBMIResult(List<BMIRule> rules, BodyParameters bodyParameters);
}
