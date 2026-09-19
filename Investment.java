/**
 * Concrete class Investment that inherits Bank Account
 */
public class Investment extends BankAccount{
    /**
     * Data member
     */
    private String type;
    /**
     * Constructor with four parameters
     * @param number initial value for the account number
     * @param owner initial value for the name of the owner
     * @param balance initial value of the balance
     * @param type value of the types "Property", "Growth" , "Shares"
     * @throws BadFormatException if type is not Growth, Property or Shares 
     * and/or if the account number is not 10 digits exactly 
     */
    public Investment(long number, String owner, double balance, String type) throws BadFormatException{
        super(number, owner, balance);
        setType(type);
    }
    /**
     * Constructor with three parameters
     * @param owner initial value for the name of the owner
     * @param balance initial value of the balance
     * @param type value of the types "Property", "Growth" , "Shares"
     * @throws BadFormatException if type is not Growth, Property or Shares
     * and/or if the account number is not 10 digits exactly 
     */
    public Investment(String owner, double balance, String type) throws BadFormatException{
        super(owner, balance);
        setType(type);
    }
    /**
     * Accessor for the type 
     * @return value of the tyoe
     */
    public String getType(){
        return type;
    }
    /**
     * Mutator for the investment Type
     * @throws BadFormatException if the type is not Growth, Property, or Shares
     */
    public void setType(String type) throws BadFormatException{
        if(!type.equals("Growth") && !type.equals("Property") && !type.equals("Shares")){
            throw new BadFormatException("Bad Investment type: \"" + type + "\", should be [Property|Growth|Shares]");
        }
        this.type = type;
    }
   /**
   * Applies a profit or loss to the account balance based on the given risk
   * factor. A risk of 0.5 or higher yields a profit of 5% of the balance;
   * a risk below 0.5 yields a loss of 2% of the balance. The balance is
   * updated accordingly.
   * @param risk the risk factor used to determine profit or loss
   * @return the amount added to the balance if positive (profit),
   * or the amount subtracted if negative (loss)
   */
    public double getProfitOrLoss(double risk){
        if (risk >= 0.5){
            double profit = (0.05 * balance);
            balance = balance + (0.05 * balance);
            return profit;
        } else {
            double loss = (0.02 * balance);
            balance = balance - (0.02 * balance);
            return -loss; 
        }
    }
    /**
    * Accessor for the Checking account attributes
    * @return formatted string with type label and object attributes
    */
    public String toString(){
       return String.format("%-16s", "Investment") + super.toString() + String.format("\t%-10s", type);
    }
    /**
     * Retturns the data of the investment account in CSV format
     */
    public String fileString(){
        return "Investment," + super.fileString() + "," + type;
    }
}