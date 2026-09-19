/**
 * This exception is thrown when the amount to withdraw is greater than
 * or equal to the current balance
 */
public class IllegalTransactionException extends Exception{
    public IllegalTransactionException(){
        super();
    }
    public IllegalTransactionException(String message){
        super(message);   
    }
}