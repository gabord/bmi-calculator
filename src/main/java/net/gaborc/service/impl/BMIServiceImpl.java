package net.gaborc.service.impl;

import net.gaborc.constants.BMIConstants;
import net.gaborc.domain.BMIRule;
import net.gaborc.domain.BodyParameters;
import net.gaborc.domain.dto.BMIResult;
import net.gaborc.exception.BodyParameterOutOfBoundsException;
import net.gaborc.service.BMIService;

import java.text.MessageFormat;
import java.util.List;

public class BMIServiceImpl implements BMIService {

    /**
     *
     * @throws IllegalArgumentException either if the given body parameters are out of pre-defined ranges or no matching BMI rule was found for the result
     */
    public BMIResult calculateBMIResult(List<BMIRule> rules, BodyParameters bodyParameters) {
        try {
            checkBodyParameters(bodyParameters);
            BMIResult bmiResult = new BMIResult();
            double result = getBMINumber(bodyParameters);
            BMIRule matchingRule = findMatchingRule(rules, result);

            if (matchingRule == null) {
                throw new IllegalArgumentException("No matching rule was found for the BMI value: " + result);
            }
            bmiResult.setResult(result);
            bmiResult.setDescription(matchingRule.getDescription());
            return bmiResult;
        }
        catch (BodyParameterOutOfBoundsException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    private BMIRule findMatchingRule(List<BMIRule> rules, double result) {
        BMIRule matchingRule = null;

        for (BMIRule bmiRule: rules) {
            if (bmiRule.isBetweenRange(result)) {
                matchingRule = bmiRule;
            }
        }
        return matchingRule;
    }

    public double getBMINumber(BodyParameters bodyParameters) {
        if (bodyParameters.getHeightInCentimeters() <= 0) {
            throw new IllegalArgumentException("Height must be non-negative!");
        }
        if (bodyParameters.getWeightInKilograms() <= 0) {
            throw new IllegalArgumentException("Weight must be non-negative!");
        }
        double heightInMeters = bodyParameters.getHeightInCentimeters() / 100;
        return bodyParameters.getWeightInKilograms() / Math.pow(heightInMeters,2);
    }

    private void checkBodyParameters(BodyParameters bodyParameters) throws BodyParameterOutOfBoundsException {
        double height = bodyParameters.getHeightInCentimeters();
        double weight = bodyParameters.getWeightInKilograms();

        if (height < BMIConstants.MIN_HEIGHT_IN_CENTIMETERS || height > BMIConstants.MAX_HEIGHT_IN_CENTIMETERS) {
            throw new BodyParameterOutOfBoundsException(MessageFormat.format("Height is out of bounds! Was {0} - should be between {1} and {2}", height, BMIConstants.MIN_HEIGHT_IN_CENTIMETERS, BMIConstants.MAX_HEIGHT_IN_CENTIMETERS));
        }
        if (weight < BMIConstants.MIN_WEIGHT_IN_KILOGRAMS || weight > BMIConstants.MAX_WEIGHT_IN_KILOGRAMS) {
            throw new BodyParameterOutOfBoundsException(MessageFormat.format("Weight is out of bounds! Was {0} - should be between {1} and {2}", height, BMIConstants.MIN_WEIGHT_IN_KILOGRAMS, BMIConstants.MAX_WEIGHT_IN_KILOGRAMS));
        }
    }


}
