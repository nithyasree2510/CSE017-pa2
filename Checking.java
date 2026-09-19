/**
 * Concrete class Checking that inherits Bank Account
 */
public class Checking extends BankAccount{
    /**
     * Constructor with three parameters
     * @param number initial value for the account number
     * @param owner initial value for the name of the owner
     * @param balance initial value of the balance
     */
    public Checking(long number, String owner, double balance) throws BadFormatException{
        super(number, owner, balance);
    }
    /**
     * Constructor with two parameters
     * @param owner initial value for the name of the owner
     * @param balance initial value of the balance
     */
    public Checking(String owner, double balance){
        super(owner, balance);
    }
    /**
    * Accessor for the Checking account attributes
    * @return formatted string with type label and object attributes
    */
    public String toString(){
       return String.format("%-16s", "Checking") + super.toString();
    }
    /**
     * Retturns the data of the checkings account in CSV format
     */
    public String fileString(){
        return "Checking," + super.fileString();
    }
}