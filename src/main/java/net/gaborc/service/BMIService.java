package net.gaborc.service;

import net.gaborc.domain.BMIRule;
import net.gaborc.domain.BodyParameters;
import net.gaborc.domain.dto.BMIResult;
import net.gaborc.exception.BusinessException;

import java.util.List;

/**
 * Services relating to the calculation of BMI values
 */
public interface BMIService {
    /**
     * Returns a BMI result object - first, a BMI result value is calculated, then it gets compared to the supplied BMIRules.
     * @param rules a set of rules against which to compare the resulting BMI.
     * @param bodyParameters the person's body parameters.
     * @return a BMI result object with the resulting BMI and its interpretation.
     * @throws BusinessException when calculation of BMI was not successful given the arguments.
     */
    BMIResult calculateBMIResult(List<BMIRule> rules, BodyParameters bodyParameters) throws BusinessException;

    /**
     * Calculates a raw BMI result value.
     * @param bodyParameters the person's body parameters.
     * @return the BMI result value.
     * @throws IllegalArgumentException if either the height or weight value is non-positive.
     */
    public double getBMINumber(BodyParameters bodyParameters);
}
