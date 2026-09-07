public class Savings extends BankAccount{
    private double yearlyInterest;

    public Savings(){
        super();
        yearlyInterest = 0.0;
    }
    // constructors

    public double getYearlyInterest(){ return yearlyInterest;}
    public void setYearlyInterest(double yi){ yearlyInterest = yi;}

    // applyMonthlyInterest()

    public String toString(){
        return String.format("%-10s\t%s\t%-5.2f", "Savings", super.toString(), yearlyInterest);
    }
    // fileString()
}