package org.fidz;

/**
 * description of class PassengerInformation 
 *
 * @author firman.lasaman
 * @version v1.0.0
 */
public class PassengerInformation {
    private String firstname;
    private String lastname;
    private String destination;
    private double fareAmount;
    private int stopNumber;

    /**
     * constructor for passenger Information class
     * 
     * @param firstname first name
     * @param lastname last name
     * @param destination destination
     * @param fareAmount fare amount
     * @param stopNumber stop number
     */
    public PassengerInformation(String firstname, String lastname, String destination, double fareAmount,
            int stopNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.destination = destination;
        this.fareAmount = fareAmount;
        this.stopNumber = stopNumber;
    }

    /**
     * constructor for passenger Information class
     */
    public PassengerInformation() {
    }

    /**
     * to getFirstname
     * @return String
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * to getLastname
     * @return String
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * to getDestination
     * @return String
     */
    public String getDestination() {
        return destination;
    }

    /**
     * to getFareAmount
     * @return double
     */
    public double getFareAmount() {
        return fareAmount;
    }

    /**
     * to getStopNumber
     * @return double 
     */
    public double getStopNumber() {
        return stopNumber;
    }

    /**
     * to setFirstname
     * 
     * @param firstname String
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * to setLastname
     * 
     * @param lastname String
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * to setDestination
     * 
     * @param destination String
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * to setFareAmount
     * 
     * @param fareAmount double
     */
    public void setFareAmount(double fareAmount) {
        this.fareAmount = fareAmount;
    }

    /**
     * to setStopNumber
     * 
     * @param stopNumber int
     */
    public void setStopNumber(int stopNumber) {
        this.stopNumber = stopNumber;
    }

    /**
     * override toString method
     * @return String
     */
    @Override
    public String toString() {
        return "PassengerInformation [destination=" + destination + ", fareAmount=" + fareAmount + ", firstname="
                + firstname + ", lastname=" + lastname + ", stopNumber=" + stopNumber + "]";
    }

    /**
     * formating output to tex file
     * @return String
     */
    public String toTexFileFormat() {
        return "Passenger Last Name: " + this.lastname + "\nPassenger First Name: " + this.firstname + "\nDestination: "
                + this.destination + "\nFare Amount: " + this.fareAmount + "\nStop Number: " + this.stopNumber + "\n\n";
    }

    /**
     * generate mock data
     * @return PassengerInformation
     */
    public static PassengerInformation generateMockData1() {
        return new PassengerInformation("John", "Doe", "Quezon Avenue and GMA Kamuning", 50.0, 3);
    }

    /**
     * generate mock data
     * @return PassengerInformation
     */
    public static PassengerInformation generateMockData2() {
        return new PassengerInformation("Firman", "Lasaman", "Boni Avenue and Guadalupe", 50.0, 2);
    }

    /**
     * generate mock data
     * @return PassengerInformation
     */
    public static PassengerInformation generateMockData3() {
        return new PassengerInformation("Film", "Mahone", "Cubao and Santolan", 50.0, 4);
    }

}
