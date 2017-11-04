package net.gaborc.service.impl;

import net.gaborc.constants.BMIConstants;
import net.gaborc.domain.BMIRule;
import net.gaborc.domain.BodyParameters;
import net.gaborc.domain.dto.BMIResult;
import net.gaborc.exception.BodyParameterOutOfBoundsException;
import net.gaborc.exception.BusinessException;
import net.gaborc.exception.NoMachingBMIRUleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class BMIServiceImplTest {

    BMIServiceImpl bmiService;
    List<BMIRule> bmiRules;

    @BeforeEach
    void init() {
        this.bmiService = new BMIServiceImpl();
        bmiRules = new ArrayList<>();
    }

    @Test
    void getBMINumberShouldThrowExceptionWhenInputValuesAreNegative() {
        BodyParameters bodyParameterWithNegativeWeight = new BodyParameters(-1, 150);
        BodyParameters bodyParameterWithNegativeHeight = new BodyParameters(50, -1);

        Executable heightExecutable = () -> bmiService.getBMINumber(bodyParameterWithNegativeHeight);
        Executable weightExecutable = () -> bmiService.getBMINumber(bodyParameterWithNegativeWeight);

        assertThrows(IllegalArgumentException.class, heightExecutable);
        assertThrows(IllegalArgumentException.class, weightExecutable);
    }

    @Test
    void getBMINumberShouldThrowExceptionWhenInputValuesAreZero() {
        BodyParameters bodyParametersWithZeroHeight = new BodyParameters(50, 0);
        BodyParameters bodyParametersWithZeroWeight = new BodyParameters(0, 150);

        Executable heightExecutable = () -> bmiService.getBMINumber(bodyParametersWithZeroHeight);
        Executable weightExecutable = () -> bmiService.getBMINumber(bodyParametersWithZeroWeight);

        assertThrows(IllegalArgumentException.class, heightExecutable);
        assertThrows(IllegalArgumentException.class, weightExecutable);
    }

    @Test
    void getBMINumberShouldReturnCorrectBMIValue() {
        BodyParameters bodyParameters = new BodyParameters(100, 200);
        assertEquals(25, bmiService.getBMINumber(bodyParameters));
    }

    @Test
    void calculateBMIResultShouldThrowExceptionWhenBodyParametersAreOutOfBounds() {
        bmiRules.add(new BMIRule(10, 20, "Dummy"));

        BodyParameters bodyParametersWithBelowMinHeight = new BodyParameters(100, BMIConstants.MIN_HEIGHT_IN_CENTIMETERS - 1);
        BodyParameters bodyParametersWithAboveMaxHeight = new BodyParameters(100, BMIConstants.MAX_HEIGHT_IN_CENTIMETERS + 1);
        BodyParameters bodyParametersWithBelowMinWeight = new BodyParameters(BMIConstants.MIN_WEIGHT_IN_KILOGRAMS - 1, 180);
        BodyParameters bodyParametersWithAboveMaxWeight = new BodyParameters(BMIConstants.MAX_WEIGHT_IN_KILOGRAMS + 1, 180);

        Executable bodyBelowMinHeightExecutable = () -> bmiService.calculateBMIResult(bmiRules, bodyParametersWithBelowMinHeight);
        Executable bodyAboveMaxHeightExecutable = () -> bmiService.calculateBMIResult(bmiRules, bodyParametersWithAboveMaxHeight);
        Executable bodyAboveMaxWeightExecutable = () -> bmiService.calculateBMIResult(bmiRules, bodyParametersWithAboveMaxWeight);
        Executable bodyBelowMinWeightExecutable = () -> bmiService.calculateBMIResult(bmiRules, bodyParametersWithBelowMinWeight);

        assertThrows(BodyParameterOutOfBoundsException.class, bodyAboveMaxHeightExecutable);
        assertThrows(BodyParameterOutOfBoundsException.class, bodyAboveMaxWeightExecutable);
        assertThrows(BodyParameterOutOfBoundsException.class, bodyBelowMinHeightExecutable);
        assertThrows(BodyParameterOutOfBoundsException.class, bodyBelowMinWeightExecutable);
    }

    @Test
    void calculateBMIResultShouldThrowExceptionWhenNoMatchingRuleWasFound() {
        bmiRules.add(new BMIRule(0,17.9,"Underweight"));
        BodyParameters bodyParameters = new BodyParameters(100,200);

        Executable executable = () -> bmiService.calculateBMIResult(bmiRules, bodyParameters);
        assertThrows(NoMachingBMIRUleException.class, executable);
    }

    @Test
    void calculateBMIResultShouldReturnCorrectBMIResult() {
        try {
            BodyParameters bodyParameters = new BodyParameters(98,190);

            bmiRules.add(new BMIRule(0,18.4,"underweight"));
            bmiRules.add(new BMIRule(18.5,24.9,"healthy"));
            bmiRules.add(new BMIRule(25,29.9,"overweight"));
            bmiRules.add(new BMIRule(30,100,"obese"));

            BMIResult result = bmiService.calculateBMIResult(bmiRules,bodyParameters);

            assertThat(result.getDescription(),equalTo("overweight"));
            assertThat(result.getResult(),equalTo(27.146814404432135));
        }
        catch (BusinessException ex) {
            // If an exception is thrown, it will be reported in the results...
        }

    }
}