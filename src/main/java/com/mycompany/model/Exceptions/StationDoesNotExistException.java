/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model.Exceptions;

/**
 * This is my exception throwing when no station on my list has recived town.
 * This exception extends from NullPointerException
 *
 * @version 2.0
 * @author Agata Pietrzycka
 */
public class StationDoesNotExistException extends NullPointerException {
/**
 * Massage which is showing to user when this exception is throwing
 */
    private static final String MESSAGE = "No station is located in %s.";
/**
 * Class constructor calls the constructor from NullPointerException class 
 * @param town this is a town which user gives to program but no statnios are located in this town
 */
    public StationDoesNotExistException(String town) {
        super(String.format(MESSAGE, town));
    }

}
