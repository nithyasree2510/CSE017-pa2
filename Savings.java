/**
 * Concrete class Checking that inherits Bank Account
 */
public class Savings extends BankAccount{
    /**
     * Data member
     */
    private double YearlyInterestRate;
    /**
     * Constructor with four parameters
     * @param number initial value for the account number
     * @param owner initial value for the name of the owner
     * @param balance initial value of the balance
     * @param yInterestRate initial value of the YearlyInterestRate
     */
    public Savings(long number, String owner, double balance, double yInterestRate) throws BadFormatException{
        super(number, owner, balance);
        YearlyInterestRate = yInterestRate;
    }
    /**
     * Constructor with three parameters
     * @param owner initial value for the name of the owner
     * @param balance initial value of the balance
     * @param yInterestRate initial value of the YearlyInterestRate
     */
    public Savings(String owner, double balance, double yInterestRate){
        super(owner, balance);
        YearlyInterestRate = yInterestRate;
    }
    /**
     * Accessor for the yearly interest 
     * @return value of the yearly interest 
     */
    public double getYearlyInterest(){
        return YearlyInterestRate;
    }
    /**
     * Mutator for the yearly interest
     * @param id value of the yearlyInterest
     */
    public void setYearlyInterest(double y){
        YearlyInterestRate = y;
    }
    public double applyMonthlyInterest(){
        double monthlyInterest = ((YearlyInterestRate/12)/100) * balance;
        balance = balance + monthlyInterest;
        return monthlyInterest;
    }
    /**
    * Accessor for the Checking account attributes
    * @return formatted string with type label and object attributes
    */
    public String toString(){
       return String.format("%-16s", "Savings") + super.toString() + String.format("\t%-5.2f", YearlyInterestRate);
    }
    /**
     * Retturns the data of the savings account in CSV format
     */
    public String fileString(){
    return "Savings," + super.fileString() + "," + YearlyInterestRate;
}
}