/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @version 1.0
 * @author Agata Pietrzycka
 */
public class ValidDataModelTest {

    /**
     * It contains a validator from ValidDataModel class
     */
    ValidDataModel valid;

    /**
     * Here before each test is new valid create
     */
    @BeforeEach
    public void setUp() {
        valid = new ValidDataModel();
    }

    /**
     * Test of sunlightValidator method, of class ValidDataModel.
     *
     * @param sunlight it's ammount of sunlight
     * @param expectedValue it's value which is expected
     */
    @ParameterizedTest
    @MethodSource("dataToSunlightValidator")
    public void testSunlightValidator(String sunlight, boolean expectedValue) {

        assertEquals(expectedValue, valid.sunlightValidator(sunlight), "Value is not correct");

    }

    /**
     * It is a help method. It provide data to test testSunlightValidator.
     *
     * @return arguments with data to test.
     */
    static Stream<Arguments> dataToSunlightValidator() {
        return Stream.of(arguments("1", true),
                arguments("", false),
                arguments("-100", false),
                arguments("100", true),
                arguments("10100", false),
                arguments("-10100", false),
                arguments("10.0.1", false),
                arguments("1000", false),
                arguments("a", false),
                arguments("999", true),
                arguments("999.99", true),
                arguments(null, false));

    }

    /**
     * Test of theAmmoutofPrecipitationValidator method, of class
     * ValidDataModel.
     *
     * @param rainfall it's ammount of rainfall
     * @param expectedValue it's value which is expected
     */
    @ParameterizedTest
    @MethodSource("dataToTheAmmoutofPrecipitationValidator")
    public void testTheAmmoutofPrecipitationValidator(String rainfall, boolean expectedValue) {
        assertEquals(expectedValue, valid.theAmmoutofPrecipitationValidator(rainfall), "Value is not correct");
    }

    /**
     * It is a help method. It provide data to test
     * testTheAmmoutofPrecipitationValidator.
     *
     * @return arguments with data to test.
     */
    static Stream<Arguments> dataToTheAmmoutofPrecipitationValidator() {
        return Stream.of(arguments("1", true),
                arguments("", false),
                arguments("-100", false),
                arguments("100", true),
                arguments("10100.", true),
                arguments("-10100", false),
                arguments("10.0.1", false),
                arguments(null, false),
                arguments("99999.99", true),
                arguments("a", false),
                arguments("100000.00", false),
                arguments("1000000", false));

    }

    /**
     * Test of visabilityValidator method, of class ValidDataModel.
     *
     * @param visability it's ammount of visability
     * @param expectedValue it's value which is expected
     */
    @ParameterizedTest
    @MethodSource("dataToVisabilityValidator")
    public void testVisabilityValidator(String visability, boolean expectedValue) {
        assertEquals(expectedValue, valid.visabilityValidator(visability), "Value is not correct");
    }

    /**
     * It is a help method. It provide data to test testVisabilityValidator.
     *
     * @return arguments with data to test.
     */
    static Stream<Arguments> dataToVisabilityValidator() {
        return Stream.of(arguments("1", true),
                arguments("", false),
                arguments("50", true),
                arguments("-100", false),
                arguments("100", true),
                arguments("10100.", true),
                arguments("-10100", false),
                arguments("10.0.1", false),
                arguments("a", false),
                arguments(null, false),
                arguments("999999.99", true),
                arguments("999999", true),
                arguments("1000000.00", false),
                arguments("1000000", false));

    }

    /**
     * Test of pressureValidator method, of class ValidDataModel.
     *
     * @param pressure it's ammount of pressure
     * @param expectedValue it's value which is expected
     */
    @ParameterizedTest
    @MethodSource("dataToPressureValidator")
    public void testPressureValidator(String pressure, boolean expectedValue) {
        assertEquals(expectedValue, valid.pressureValidator(pressure), "Value is not correct");
    }

    /**
     * It is a help method. It provide data to test testPressureValidator.
     *
     * @return arguments with data to test.
     */
    static Stream<Arguments> dataToPressureValidator() {
        return Stream.of(arguments("1", true),
                arguments("", false),
                arguments("-100", false),
                arguments("100", true),
                arguments("1010.", true),
                arguments("-10100", false),
                arguments("10.0.1", false),
                arguments("a", false),
                arguments(null, false),
                arguments("1999.99", true),
                arguments("1999", true),
                arguments("2000.00", false),
                arguments("2000", false));

    }

    /**
     * Test of townValidator method, of class ValidDataModel.
     *
     * @param town it's a town ammount
     * @param expectedValue it's value which is expected
     */
    @ParameterizedTest
    @MethodSource("dataToTownValidator")
    public void testTownValidator(String town, boolean expectedValue) {
        assertEquals(expectedValue, valid.townValidator(town), "Value is not correct");
    }

    /**
     * It is a help method. It provide data to test testTownValidator.
     *
     * @return arguments with data to test.
     */
    static Stream<Arguments> dataToTownValidator() {
        return Stream.of(arguments("1", false),
                arguments("", false),
                arguments("Miasto trzydziesci jeden znakow", true),
                arguments("Miasto trzydziesci znakow test", true),
                arguments("Bytom1", false),
                arguments("Bielsko-Biala", true),
                arguments("Pruszcz Gdanski", true),
                arguments("    Gdansk", false),
                arguments("Gdynia-", false),
                arguments("----", false),
                arguments("    ", false),
                arguments("Gdynia-Gdynia Gdynia", true),
                arguments(null, false));

    }

    /**
     * Test of temperatureValidator method, of class ValidDataModel.
     *
     * @param temp it's ammount of temperature
     * @param expectedValue it's value which is expected
     */
    @ParameterizedTest
    @MethodSource("dataToTemperatureValidator")
    public void testTemperatureValidator(String temp, boolean expectedValue) {
        assertEquals(expectedValue, valid.temperatureValidator(temp), "Value is not correct");
    }

    /**
     * It is a help method. It provide data to test testTemperatureValidator.
     *
     * @return arguments with data to test.
     */
    static Stream<Arguments> dataToTemperatureValidator() {
        return Stream.of(arguments("1", true),
                arguments("", false),
                arguments("a", false),
                arguments("-1", true),
                arguments("100", true),
                arguments("101", false),
                arguments("-100", true),
                arguments("-101", false),
                arguments("99.99", true),
                arguments(null, false));

    }

    /**
     * Test of smogOrHumidityValidator method, of class ValidDataModel.
     *
     * @param parameter it's ammount of smog or humidity
     * @param expectedValue it's value which is expected
     */
    @ParameterizedTest
    @MethodSource("dataToSmogOrHumidityValidator")
    public void testSmogOrHumidityValidator(String parameter, boolean expectedValue) {
        assertEquals(expectedValue, valid.smogOrHumidityValidator(parameter), "Value is not correct");
    }

    /**
     * It is a help method. It provide data to test testSmogOrHumidityValidator.
     *
     * @return arguments with data to test.
     */
    static Stream<Arguments> dataToSmogOrHumidityValidator() {
        return Stream.of(arguments("1", true),
                arguments("", false),
                arguments("a", false),
                arguments("-1", false),
                arguments("100", true),
                arguments("100.1", false),
                arguments("101", false),
                arguments("99", true),
                arguments("99.99", true),
                arguments(null, false));

    }
}
