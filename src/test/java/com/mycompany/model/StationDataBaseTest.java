/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import com.mycompany.model.Exceptions.StationDoesNotExistException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @version 2.0
 * @author Agata Pietrzycka
 */
public class StationDataBaseTest {

    /**
     * It contains data about stations
     */
    private StationDataBase base;


    /**
     * Here before each test is new base create
     */
    @BeforeEach
    public void setUp() {
        base = new StationDataBase();

    }


    /**
     * Test of addStation method, of class StationDataBase. This test will check
     * if new station is added.
     *
     * @param station it's an object with station which type is WheatherStation.
     * @param expectedSize it's a value by which list size should be increased
     */
    @ParameterizedTest
    @MethodSource("dataToAddStation")
    public void testAddStation(WheatherStation station, int expectedSize) {

        int size = base.getList().size();
        base.addStation(station);
        Assertions.assertEquals(size + expectedSize, base.getList().size(), "Null wasn't add");

    }

    /**
     * It is a help method. It provides data to test testAddStation.
     *
     * @return arguments with data to test.
     */
    static Stream<Arguments> dataToAddStation() {
        return Stream.of(arguments(new WheatherStation("Bytom", new Measurement(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)))), 1),
                arguments(null, 0));

    }

    /**
     * Test of findStation method, of class StationDataBase. This test will
     * check if station having recived town exists on a list.
     *
     * @param town ammount with town to test
     * @param expectedValue value which is expected
     */
    @ParameterizedTest
    @MethodSource("dataToFindStation")
    public void testFindStation(String town, boolean expectedValue) {
        base.addStation(new WheatherStation("Bytom", new Measurement(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)))));
        Assertions.assertEquals(expectedValue, base.findStation(town), "This town is not in list");
    }

    /**
     * It is a help method. It provides data to test testFindStation.
     *
     * @return arguments with data to test.
     */
    static Stream<Arguments> dataToFindStation() {
        return Stream.of(arguments("Bytom", true),
                arguments("Gdansk", false),
                arguments(null, false),
                arguments("", false));

    }

    /**
     * Test of updateDataToStation method, of class StationDataBase. This test
     * will check if any station on list has recived town
     *
     * @param town it is recived station's town.
     */
    @ParameterizedTest
    @ValueSource(strings = {"Karkow", "Pruszcz", "Moskwa"})
    public void testGetStationByIdForIncorrectTown(String town) {
        base.addStation(new WheatherStation("Bytom", new Measurement(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)))));
        StationDoesNotExistException exception = Assertions.assertThrows(StationDoesNotExistException.class, () -> base.getStationByTown(town));
        Assertions.assertEquals("No station is located in " + town + ".", exception.getMessage());
    }

    /**
     * Test of updateDataToStation method, of class StationDataBase.This test
     * will check if any station on list has recived town
     *
     * @param town it is recived station's town.
     *
     */
    @ParameterizedTest
    @ValueSource(strings = {"Bytom"})
    public void testGetStationByIdForCorrectTown(String town) {
        WheatherStation station = new WheatherStation("Bytom", new Measurement(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0))));
        base.addStation(station);

        Assertions.assertEquals(station, base.getStationByTown(town));
    }

    /**
     * Test of loadFromFile method, of class StationDataBase.
     *
     * @param fileName it is a wrong file
     */
    @ParameterizedTest
    @ValueSource(strings = {"baza.txt", "bazusia.txt", "baz.txt"})
    public void testLoadFromFile(String fileName) {

        FileNotFoundException exception = Assertions.assertThrows(FileNotFoundException.class, () -> base.loadFromFile(fileName));
        Assertions.assertEquals(FileNotFoundException.class, exception.getClass());
    }

    /**
     * Test of loadFromFile method, of class StationDataBase.
     *
     * @param fileName it is a file which doesn't exist
     */
    @ParameterizedTest
    @ValueSource(strings = {"ba.txt", "bz.txt", "bkz.txt"})
    public void saveBaseToFile(String fileName) {
        base.addStation(new WheatherStation("Bytom", new Measurement(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)))));

        try {
            base.saveBaseToFile(fileName);
        } catch (IOException ex) {
            Logger.getLogger(StationDataBaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String path = new File(fileName).getAbsolutePath();
        Assertions.assertTrue(new File(path).isFile());
    }

}
