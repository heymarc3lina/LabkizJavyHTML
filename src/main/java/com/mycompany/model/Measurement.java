/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.ArrayList;

/**
 * This is class with all parameters which every weather station has.
 *
 * @author Agata Pietrzycka
 * @version 1.3
 *
 */
public class Measurement {

    /**
     * Temperature which is noted by station
     *
     */
    private double temperature;
    /**
     * Humidity which is noted by station
     */
    private double humidity;
    /**
     * Pressure which is noted by station
     */
    private double pressure;
    /**
     * Visibility which is noted by station
     */
    private double visibility;
    /**
     * TheAmountOfPrecipitation which is noted by station
     */
    private double theAmountOfPrecipitation;
    /**
     * Sunlight which is noted by station
     *
     */
    private double sunlight;
    /**
     * Smog ammount which is noted by station
     */
    private double smog;

    /**
     * It's class constructor.
     *
     * @param list table with all parameters which station has
     *
     */
    public Measurement(ArrayList<Double> list) {
        this.temperature = list.get(0);
        this.humidity = list.get(1);
        this.pressure = list.get(2);
        this.visibility = list.get(3);
        this.theAmountOfPrecipitation = list.get(4);
        this.sunlight = list.get(5);
        this.smog = list.get(6);
    }

    /**
     * It's a setter for temperature.
     *
     * @param newTemperature it's a new ammount of temperature
     */
    public void setTemperature(double newTemperature) {
        this.temperature = newTemperature;
    }

    /**
     * It's a setter for humidity
     *
     * @param newHumidity it's a new ammount of himanity
     */
    public void setHumidity(double newHumidity) {
        this.humidity = newHumidity;
    }

    /**
     * It's a setter for pressure
     *
     * @param newPressure it's a new ammount of pressure
     */
    public void setPressure(double newPressure) {
        this.pressure = newPressure;
    }

    /**
     * It's a setter for visibility
     *
     * @param newVisibility it's a new ammount of visibility
     */
    public void setVisibility(double newVisibility) {
        this.visibility = newVisibility;
    }

    /**
     * It's a setter for amount of precipitation
     *
     * @param newtheAmountOfPrecipitation - it's a new ammount of amount of
     * precipitation
     */
    public void setAmountOfPrecipitation(double newtheAmountOfPrecipitation) {
        this.theAmountOfPrecipitation = newtheAmountOfPrecipitation;
    }

    /**
     * It's a setter for smog
     *
     * @param smog it's a new ammount of smog
     */
    public void setSmog(double smog) {
        this.smog = smog;
    }

    /**
     * It's a setter for sunlight
     *
     * @param timeOfSunshine it's a new ammount of sunlight
     */
    public void setSunlight(double timeOfSunshine) {
        this.sunlight = timeOfSunshine;
    }

    /**
     * It's a getter for temperature.
     *
     * @return double which is temperature ammount
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * It's a getter for humidity.
     *
     * @return double which is humidity ammount
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * It's a getter for pressure.
     *
     * @return double which is pressure ammount
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * It's a getter for visibility.
     *
     * @return double which is visibility ammount
     */
    public double getVisibility() {
        return visibility;
    }

    /**
     * It's a getter for amount of precipitation.
     *
     * @return double which is amount of precipitation
     */
    public double getTheAmountOfPrecipitation() {
        return theAmountOfPrecipitation;
    }

    /**
     * It's a getter for sulnight
     *
     * @return double which is sunlight ammount
     */
    public double getSunlight() {
        return sunlight;
    }

    /**
     * It's a getter for smog
     *
     * @return double which is smog ammount
     */
    public double getSmog() {
        return smog;
    }

}
