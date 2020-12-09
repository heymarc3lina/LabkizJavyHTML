/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

/**
 * This is class with descripting a weather station.
 *
 * @version 1.2
 * @author Agata Pietrzycka
 */
public class WheatherStation {

    /**
     * It's a unique number of station
     */
    private int id;
    /**
     * It's a place where this station is located
     */
    private String town;
    /**
     * It's an object with all parameters which this station measure
     */
    private Measurement measurement;

    /**
     * Class constructor
     *
     * @param town it's a place where this station is located. Town is given to
     * constructor when this object is created
     * @param measurement measurement which this station has. It is given when
     * station is created
     */
    public WheatherStation(String town, Measurement measurement) {
        this.town = town;
        this.measurement = measurement;
    }

    /**
     * This is getter given station id
     *
     * @return int which is station id
     */
    public int getId() {
        return id;
    }

    /**
     * This is getter given measurement
     *
     * @return Measurement which is all parameters which this station measure
     */
    public Measurement getMeasurement() {
        return measurement;
    }

    /**
     * This is setter which sets id
     *
     * @param id is an unique number which this station has
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This is setter which sets measurement
     *
     * @param measurement is an object which has all parameters measure for this
     * station
     */
    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    /**
     * This is setter wchich sets new ammount of town
     *
     * @param newTown is a new name of town
     */
    public void setTown(String newTown) {
        this.town = newTown;
    }

    /**
     * This is a getter returning a name of town where this station is located
     *
     * @return String which is name of town where station is located
     */
    public String getTown() {
        return town;
    }

}
