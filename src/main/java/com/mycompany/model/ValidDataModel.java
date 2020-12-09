/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.regex.Pattern;

/**
 * This class valid data from controller. If data are correct this class gives
 * it to model. If ther aren;t correct they are return to controller
 *
 * @version 1.0
 * @author Agata Pietrzycka
 */
public class ValidDataModel {

    /**
     * Variable to set pattern for sunlight ammount.
     */
    private Pattern sunlightPattern;
    /**
     * Variable to set pattern for the ammout of precipitation
     */
    private Pattern theAmmoutofPrecipitationPattern;
    /**
     * Variable to set pattern for visability ammount
     */
    private Pattern visabilityPattern;
    /**
     * Variable to set pattern for pressure ammount
     */
    private Pattern pressurePattern;
    /**
     * Variable to set pattern for town ammount
     */
    private Pattern townPattern;
    /**
     * Variable to set pattern for humidity and smog ammount
     */
    private Pattern humidityAndSmogPattern;
    /**
     * Variable to set pattern for temperature ammount
     */
    private Pattern temperaturePattern;

    /**
     * Class conctructor
     */
    public ValidDataModel() {
        humidityAndSmogPattern = Pattern.compile("([0-9]{1,2}[.][0-9]{0,2})|[0-9]{1,2}");
        townPattern = Pattern.compile("[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*");
        pressurePattern = Pattern.compile("([0-9]{1,4}[.][0-9]{0,2})|([0-9]{1,4})");
        visabilityPattern = Pattern.compile("([0-9]{1,6}[.][0-9]{0,2})|([0-9]{1,6})");
        theAmmoutofPrecipitationPattern = Pattern.compile("([0-9]{1,5}[.][0-9]{0,2})|([0-9]{1,5})");
        sunlightPattern = Pattern.compile("([0-9]{1,4}[.][0-9]{0,2})|([0-9]{1,4})");
        temperaturePattern = Pattern.compile("([-]{0,1}[0-9]{1,2}[.][0-9]{0,2})|([-]{0,1}[0-9]{1,2})");

    }

    /**
     * Method responsible for validating sunlight
     *
     * @param choice ammount of sunlight which user gives to program
     * @return boolean - true if choice is correct and false if not
     */
    public boolean sunlightValidator(String choice) {
        if (choice == null) {
            return false;
        }
        if (sunlightPattern.matcher(choice).matches()) {
            if (Double.parseDouble(choice) < 1000) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method responsible for validating the ammout of precipitation
     *
     * @param choice ammount of the ammount of precipitation which user gives to
     * program
     * @return boolean - true if choice is correct and false if not
     */
    public boolean theAmmoutofPrecipitationValidator(String choice) {
        if (choice == null) {
            return false;
        }
        return theAmmoutofPrecipitationPattern.matcher(choice).matches();
    }

    /**
     * Method responsible for validating the ammount of visability
     *
     * @param choice ammount of visibility which user gives to program
     * @return boolean - true if choice is correct and false when not
     */
    public boolean visabilityValidator(String choice) {
        if (choice == null) {
            return false;
        }
        return visabilityPattern.matcher(choice).matches();
    }

    /**
     * Method responsible for validating the ammout of pressure
     *
     * @param choice ammount of pressure which user gives to program
     * @return boolean - true if choice is correct and false when not
     */
    public boolean pressureValidator(String choice) {
        if (choice == null) {
            return false;
        }
        if (pressurePattern.matcher(choice).matches()) {
            if (Double.parseDouble(choice) < 2000) {

                return true;
            }
        }
        return false;

    }

    /**
     * Method responsible for validating the ammout of town
     *
     * @param choice ammount of town which user gives to program
     * @return boolean - true if choice is correct and false when not
     */
    public boolean townValidator(String choice) {
        if (choice == null) {
            return false;
        }
        return townPattern.matcher(choice).matches();
    }

    /**
     * Method responsible for validating the ammout of temperature
     *
     * @param choice ammount of temperature which user gives to program
     * @return boolean - true if choice is correct and false when not
     */
    public boolean temperatureValidator(String choice) {
        if (choice == null) {
            return false;
        }
        return temperaturePattern.matcher(choice).matches() || choice.equals("100") || choice.equals("-100");
    }

    /**
     * Method responsible for validating the ammout of humidity and smog
     *
     * @param choice ammount of smog or humidity which user gives to program
     * @return boolean - true if choice is correct and false when not
     */
    public boolean smogOrHumidityValidator(String choice) {

        if (choice == null) {
            return false;
        }
        return humidityAndSmogPattern.matcher(choice).matches() || choice.equals("100");
    }
}
