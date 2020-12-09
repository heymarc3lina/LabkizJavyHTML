/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import com.mycompany.model.Exceptions.StationDoesNotExistException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * This is class with list of all weather stations
 *
 * @version 4.0
 * @author Agata Pietrzycka
 */
public class StationDataBase {

    /**
     * List is a field with list of all weather station which we observe on this
     * program
     */
    private ArrayList<WheatherStation> list;

    /**
     * Class constructor
     */
    public StationDataBase() {
        list = new ArrayList<>();
    }

    /**
     * Method responsible for add weather station to list
     *
     * @param station it's a station which we want to add to list
     */
    public void addStation(WheatherStation station) {
        if (station != null) {
            list.add(station);
            station.setId(list.size());
        }
    }

    /**
     * Method responsible fot returning list of weather station
     *
     * @return ArrayList type which contains list with our wheather stations
     */
    public ArrayList<WheatherStation> getList() {
        return list;
    }

    /**
     * Method responsible for finding a station
     *
     * @param town it's a town where searching station is located
     * @return boolean it is dependent on find station
     */
    public boolean findStation(String town) {
        if (town == null || town.isEmpty()) {
            return false;
        }
        return list.stream().anyMatch(station -> (station.getTown().toUpperCase().equals(town.toUpperCase())));
    }

    /**
     * Method responsible for returning station which has id equals stationId
     *
     * @param town it's town of choosen station
     * @return WheatherStation is null or is a station. It is dependending on
     * existence of the chosen station
     *
     * @throws StationDoesNotExistException it is throws when list has no
     * station with recived id
     */
    public WheatherStation getStationByTown(String town) throws StationDoesNotExistException {

        if (town != null || town.isEmpty() == false) {
            for (WheatherStation station : list) {
                if (station.getTown().equals(town)) {
                    return station;
                }
            }
        }
        throw new StationDoesNotExistException(town);
    }

    /**
     * Method responsible for load list to our base from file
     *
     * @param fileName it's a name of file where is our base
     * @throws IOException when this method has problem with file
     */
    public void loadFromFile(String fileName) throws IOException {
        
        
        //list = new ArrayList<>();
        
        
        
        try (BufferedReader inputFile = new BufferedReader(new FileReader(fileName))) {
            String line = inputFile.readLine();
            ArrayList<Double> parameters = new ArrayList<>();
            while (line != null) {
                StringTokenizer preparedString = new StringTokenizer(line, " ");
                int id = Integer.parseInt(preparedString.nextToken());
                String town = preparedString.nextToken();
                for (int i = 0; i < 7; i++) {
                    parameters.add(Double.parseDouble(preparedString.nextToken()));
                }
                Measurement measure = new Measurement(parameters);
                WheatherStation station = new WheatherStation(town.replace("_", " "), measure);
                station.setId(id);
                addStation(station);
                parameters.clear();
                line = inputFile.readLine();
            }
        }

    }

    /**
     * Method responbile for saving data from list to file
     *
     * @param fileName it's a file where base is saved
     * @throws IOException when method has problem with file
     */
    public void saveBaseToFile(String fileName) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            list.forEach(station -> {
                printWriter.print(station.getId() + " " + station.getTown().replace(" ", "_") + " " + station.getMeasurement().getTemperature() + " " + station.getMeasurement().getHumidity()
                        + " " + station.getMeasurement().getPressure() + " " + station.getMeasurement().getVisibility() + " " + station.getMeasurement().getTheAmountOfPrecipitation() + " "
                        + station.getMeasurement().getSunlight() + " " + station.getMeasurement().getSmog() + "\n");
            });
            printWriter.close();

        }

    }

}
