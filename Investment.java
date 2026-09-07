public class Investment extends BankAccount{
    private String type;
    public Investment(){
        super();
        type = "none";
    }
    // constructors

    public String getType(){
        return type;
    }
    // setType

    public double getProfitOrLoss(double risk){
        double amount = 0;
        if(risk >= 0.5){ // profit
            amount = balance * 0.05;
        }
        else{ // loss
            amount = -balance * 0.02;
        }
        balance += amount;
        return amount;
    }
    public String toString(){
        return String.format("%-10s\t%s\t%-10s", "Investment", super.toString(), type);
    }
    // fileString()
}