import java.util.InputMismatchException;
/**
 * This exception is throw when an account number don't have exactly 10 digits
 */
public class BadFormatException extends InputMismatchException{
    public BadFormatException(){
        super();
    }
    public BadFormatException(String message){
        super(message);
    }
}