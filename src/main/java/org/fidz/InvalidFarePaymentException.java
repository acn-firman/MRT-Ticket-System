package org.fidz;

/**
 * description of class Exception{ 
 *
 * @author firman.lasaman
 * @version v1.0.0
 */
public class InvalidFarePaymentException extends Exception{
    public static final String MESSAGE = "Invalid Fare Payment!.";
    /**
     * construtor for InvalidFarePaymentException
     * @param message error message
     */
    public InvalidFarePaymentException(String message){
        super(message);
    }
    
    /**
     * to check payment is valid or not.
     * @param payment cash amount
     * @return boolean return payment is greater than or equal to 0
     */
    public static boolean isValidPayment(double payment){
        return payment >= 0;
    }
}
