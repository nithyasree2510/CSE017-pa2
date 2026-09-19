/**
 * Abstract class Bank account acts as a blueprint for types of 
 * bank account like checking, investment and savings
 */
public abstract class BankAccount implements Comparable<BankAccount>, Closeable{
    // Data members 
    private long number;
    private String owner;
    protected double balance;
    private static long nextNumber = 1111111111L;
    /**
     * Constructor with two parameters
     * @param owner initial value for the name of the owner
     * @param balance initial value of the balance
     */
    public BankAccount(String owner, double balance){
        this.number = nextNumber;
        this.owner = owner;
        this.balance = balance;
        nextNumber++;
    }
    /**
     * Constructor with three parameters
     * @param number initial value for the account number
     * @param owner initial value for the name of the owner
     * @param balance initial value of the balance
     */
    public BankAccount(long number, String owner, double balance) throws BadFormatException{
        String numStr = Long.toString(number);
        if(!numStr.matches("\\d{10}")){
            throw new BadFormatException("Invalid account number ( " + number + " ), must have 10 digits");
        }
        this.number = number;
        this.owner = owner;
        this.balance = balance;
    }
    /**
     * Accessor for the account number
     * @return value of the account number
     */
    public long getNumber(){
         return number;
    }
    /**
     * Accessor for the owner name
     * @return value of the owner name
     */
    public String getOwner(){
        return owner;
    }
    /**
     * Accessor for the balance
     * @return value of the balance
     */
    public double getBalance(){
        return balance;
    }
    /**
     * Accessor for the BankAcount attributes
     * @return formatted string with the object attributes
     */
    public String toString(){
        String str = String.format("%-10d\t%-30s\t$%-10.2f", 
                                   number, owner, balance);
        return str;
    }
    /**
     * Mutator for the accountnumber
     * @param id value of the acoount number
     */
    public void setNumber(long n){
        this.number = n;
    }
    /**
     * Mutator for the owner name
     * @param name value of the owner name
     */
    public void setOwner(String o){
        this.owner = o;
    }
    /**
     * Deposits the given amount into the account
     */
    public void deposit(double amount){
        balance = balance + amount;
    }
    /**
     * Withraw the given amount given that balance is enough 
     */
    public void withdraw(double amount) throws IllegalTransactionException{
        if (amount < balance){
            balance = balance - amount;
        }
        else{
            throw new IllegalTransactionException("Withdrawal failed. Not enough credit in the account.");
        }

    } 
    /**
     * Comapares this account's balance to given account's balance
     * @param ba the account number to comapre to
     * @return -1 if this balance is less than ba's balance
     * 0 if they are equal
     * 1 if this balance is greater than given account's balance
     */
    public int compareTo(BankAccount ba){
        return Double.compare(this.balance, ba.balance);
    }
    /**
     * Checks if the account can be closed 
     */
    public boolean isCloseable(){
        return balance <= 100;
    }
    /**
     * Format account's information in CSV format
     * @return CSV-formatted string of number, owner, balance
     */
    public String fileString(){
        return number + "," + owner + "," + balance;
    }
}