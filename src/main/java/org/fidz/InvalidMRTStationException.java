package org.fidz;

import java.util.Map;

/**
 * a custom class Exception  for InvalidMRTStationException
 *
 * @author firman.lasaman
 * @version v1.0.0
 */
public class InvalidMRTStationException extends Exception {
    public static final String MESSAGE = "Invalid MRT station!.";

    /**
     * throws error message.
     * @param message error message
     */
    public InvalidMRTStationException(String message) {
        super(message);
    }

    /**
     * to check station is valid or not.
     * 
     * @param station MRT station
     * @param fares fare map
     * @return boolean return station is valid or not
     */
    public static boolean isValidMRTStation(String station, Map<String, Double> fares) {
        for (String key : fares.keySet()) {
            if (key.contains(station) || key.contains(convertToTitleCase(station))) {
                return true;
            }
        }
        return false;
    }

  

    /**
     * utilize method for convert to title case.
     * 
     * @param text text
     * @return string return converted s
     */
    public static String convertToTitleCase(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

}