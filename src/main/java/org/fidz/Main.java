package org.fidz;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * description of class Main
 *
 * @author firman.lasaman
 * @version v1.0.0
 */
public class Main {
  
    private static final String ERROR_MESSAGE = "Something went wrong";
     /**
     * main function
     * 
     * @param args arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char answer;

        double payment;
        List<PassengerInformation> passengerInformationList = new ArrayList<>();
        Map<Integer, Double> stopWithfaresMaps = new HashMap<>();
        stopWithfaresMaps.put(1, 10.00);
        stopWithfaresMaps.put(2, 11.00);
        stopWithfaresMaps.put(3, 12.00);
        stopWithfaresMaps.put(4, 13.00);
        stopWithfaresMaps.put(5, 14.00);
        stopWithfaresMaps.put(6, 15.00);

        Map<String, Double> fares = new HashMap<>();
        fares.put("Quezon Avenue and GMA Kamuning", 10.00);
        fares.put("Santolan and Cubao", 11.00);
        fares.put("Ortigas and Shaw Boulevard", 12.00);
        fares.put("Boni Avenue and Guadalupe", 13.00);
        fares.put("Buendia and Ayala", 14.00);
        fares.put("Magallanes and Taft Avenue", 15.00);
        do {
            PassengerInformation passengers = new PassengerInformation();
            System.out.print("\n Enter your lastname: ");
            passengers.setLastname(sc.nextLine().trim());
            System.out.print("\n Enter your firstname: ");
            passengers.setFirstname(sc.nextLine().trim());
            do {
                System.out.print("\n Enter your destination: ");
                String des = sc.nextLine();
                if (!InvalidMRTStationException.isValidMRTStation(des, fares)) {
                    try {
                        throw new InvalidMRTStationException(InvalidMRTStationException.MESSAGE);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                passengers.setDestination(getStation(des, fares));
                break;
            } while (true);

            do {
                System.out.print("\n Enter Patyment: ");
                payment = sc.nextDouble();
                sc.nextLine();
                passengers.setFareAmount(payment);
                if (!InvalidFarePaymentException.isValidPayment(payment)) {
                    try {
                        throw new InvalidFarePaymentException(InvalidFarePaymentException.MESSAGE);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }

                if (payment < fares.get(passengers.getDestination())) {
                    System.out.println("Invalid payment. Must be " +
                            fares.get(passengers.getDestination()));
                }
            } while (fares.get(passengers.getDestination()) > payment);
            if (payment == fares.get(passengers.getDestination())) {
                System.out.println("Thank you for paying the exact amount.");
            } else {
                System.out.println(
                        "Thank you for paying. Your change is " + (payment -
                                fares.get(passengers.getDestination())));
            }
            System.out.println("Please remember you have to alight at STOP"
                    + getStopNumber(stopWithfaresMaps, fares.get(passengers.getDestination())));
            System.out.println("-------------------------------------------");
            System.out.println("LIST OF FARE PROFITS");
            passengers.setStopNumber(iterateStation(stopWithfaresMaps,
                    fares.get(passengers.getDestination())));
            System.out.println("MRT fare amount to " + passengers.getDestination() + " is "
                    + fares.get(passengers.getDestination()));
            System.out.print("\n Do you want to continue? [Y/N]: ");
            answer = sc.next().charAt(0);
            sc.nextLine();
            passengerInformationList.add(passengers);
        } while (answer == 'Y' || answer == 'y');
        sc.close();

        try {
            generateOutputFile(passengerInformationList);
        } catch (Exception e) {
            System.out.println("something went wrong : " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Program terminated. Thanks your for using the Lagning Nasisira MRT Application.");
        passengerInformationList.clear();
    }
    // generate javadoc

    // generate function that convert capital letter at the beginning of each word
    /**
     * convertToTitleCase
     *  
     * @param text String
     * @return String
     */
    public static String convertToTitleCase(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    /**
     * iterateStation view all stop
     * 
     * @param stopWithFares map of stop number and fare amount
     * @param fare fare amount
     * @return int stop number
     */
    public static int iterateStation(Map<Integer, Double> stopWithFares, double fare) {
        int stop = 0;
        for (Map.Entry<Integer, Double> entry : stopWithFares.entrySet()) {
            if (entry.getValue().equals(fare)) {
                System.out.println("TOTAL FARE FOR ALL STOP " + entry.getKey() + ": " + fare);
                stop = entry.getKey();
            } else {
                System.out.println("TOTAL FARE FOR ALL STOP " + entry.getKey() + ": " + 0.00);
            }
        }
        return stop;
    }

    /**
     * getStopNumber from the map that have the stop number and fare amount
     * 
     * @param stopWithFare map of stop number and fare amount
     * @param fareAmount fare amount
     * @return int stop number
     */
    public static int getStopNumber(Map<Integer, Double> stopWithFare, double fareAmount) {
        int stopnumber;
        for (Map.Entry<Integer, Double> entry : stopWithFare.entrySet()) {
            if (entry.getValue().equals(fareAmount)) {
                stopnumber = entry.getKey();
                return stopnumber;
            }
        }
        return 99;
    }

    /**
     * create output file 6 files per stop number
     * 
     * @param passengerInformationList list of passenger information
     */
    public static void generateOutputFile(List<PassengerInformation> passengerInformationList) {

        try (
                FileWriter myWritter1 = new FileWriter("Quezon_Avenue_and_GMA_Kamuning.txt");
                FileWriter myWritter2 = new FileWriter("Cubao_and_Santolan.txt");
                FileWriter myWritter3 = new FileWriter("Ortigas_and_Shaw_Boulevard.txt");
                FileWriter myWritter4 = new FileWriter("Boni_Avenue_and_Guadalupe");
                FileWriter myWritter5 = new FileWriter("Buendia_and_Ayala.txt");
                FileWriter myWritter6 = new FileWriter("Magallanes_and_Taft_Avenue.txt");) {

            // ! Stop 1
            passengerInformationList.stream()
                    .filter(passenger -> passenger.getDestination().equals("Quezon Avenue and GMA Kamuning"))
                    .forEach(passenger -> {
                        try {
                            myWritter1.write(passenger.toTexFileFormat());
                        } catch (Exception e) {
                            System.out.println(ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    });

            // ! Stop 2
            passengerInformationList.stream()
                    .filter(passenger -> passenger.getDestination().equals("Cubao and Santolan"))
                    .forEach(passenger -> {
                        try {
                            myWritter2.write(passenger.toTexFileFormat());
                            System.out.println(passenger.toTexFileFormat());
                        } catch (Exception e) {
                            System.out.println(ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    });
            // ! Stop 3
            passengerInformationList.stream()
                    .filter(passenger -> passenger.getDestination().equals("Ortigas and Shaw Boulevard"))
                    .forEach(passenger -> {
                        try {
                            myWritter3.write(passenger.toTexFileFormat());
                            System.out.println(passenger.toTexFileFormat());
                        } catch (Exception e) {
                            System.out.println(ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    });
            // ! Stop 4
            passengerInformationList.stream()
                    .filter(passenger -> passenger.getDestination().equals("Boni Avenue and Guadalupe"))
                    .forEach(passenger -> {
                        try {
                            myWritter4.write(passenger.toTexFileFormat());
                            System.out.println(passenger.toTexFileFormat());
                        } catch (Exception e) {
                            System.out.println(ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    });
            // ! Stop 5
            passengerInformationList.stream()
                    .filter(passenger -> passenger.getDestination().equals("Buendia and Ayala"))
                    .forEach(passenger -> {
                        try {
                            myWritter5.write(passenger.toTexFileFormat());
                            System.out.println(passenger.toTexFileFormat());
                        } catch (Exception e) {
                            System.out.println(ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    });
            // ! Stop 6
            passengerInformationList.stream()
                    .filter(passenger -> passenger.getDestination().equals("Magallanes and Taft Avenue"))
                    .forEach(passenger -> {
                        try {
                            myWritter6.write(passenger.toTexFileFormat());
                            System.out.println(passenger.toTexFileFormat());
                        } catch (Exception e) {
                            System.out.println(ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);

        }
    }
      // generate function to find word in provided string as passengerInformation
    /**
     * get name of station by provided one of station name in the map.
     * @param station   String
     * @param fares map of station and fare amount
     * @return String
     */ 
    public static String getStation(String station, Map<String, Double> fares) {
        if (InvalidMRTStationException.isValidMRTStation(station, fares)) {
            for (String key : fares.keySet()) {
                if (key.contains(station) || key.contains(convertToTitleCase(station))) {
                    return key;
                }
            }
        }
        return null;
    }
}