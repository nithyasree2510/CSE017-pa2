public class Checking extends BankAccount{
    public Checking(){
        super();
    }
    // implement constructors
    
    public String toString(){
        return String.format("%-10s\t%s", "Checking", super.toString());
    }
    // implement fileString()
}