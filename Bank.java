import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Bank has a array with BankAccount objects
 * Adds, finds, removes acounts by bank number 
 * Sorts accounts by balance
 */
public class Bank{
    /**
     * Data members
     */
    private BankAccount[] accounts;
    private BankAccount[] closed; 
    private int count;
    private int closedCount; 
    /**
     * Default constructor creates an array of size 50
     */
    public Bank(){
        accounts = new BankAccount[50];
        closed = new BankAccount[50];
        count = 0;
        closedCount = 0;
    }
    /**
     * Constructor that builds the bank from a data file
     * @param filename the file to read accounts from
     */
    public Bank(String fileName){
        accounts = new BankAccount[50];
        closed = new BankAccount[50];
        count = 0;
        closedCount = 0;
        try{
            read(fileName);
        } catch (FileNotFoundException e){
            System.out.println("File not found: " + fileName);
        }
    }
    private void read(String filename) throws FileNotFoundException{
        Scanner fileScanner = new Scanner(new File(filename));
        while(fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            if(line.trim().isEmpty()){
                continue;
            }
            String[] tokens = line.split(",");
            boolean isClosed = tokens[tokens.length - 1].trim().equalsIgnoreCase("closed");
            String type = tokens[0].trim();

            try{
                long number;
                try{
                    number = Long.parseLong(tokens[1].trim());
                } catch(NumberFormatException e){
                    throw new BadFormatException("Invalid format for the account number \""
                        + tokens[1].trim() + "\", must be a long integer");
                }

                String owner = tokens[2].trim();

                double balance;
                try{
                    balance = Double.parseDouble(tokens[3].trim());
                } catch(NumberFormatException e){
                    throw new BadFormatException("Invalid format for the balance \""
                        + tokens[3].trim() + "\", must be a double");
                }

                BankAccount ba;
                if(type.equals("Checking")){
                    ba = new Checking(number, owner, balance);
                } else if(type.equals("Savings")){
                    double rate = Double.parseDouble(tokens[4].trim());
                    ba = new Savings(number, owner, balance, rate);
                } else if(type.equals("Investment")){
                    ba = new Investment(number, owner, balance, tokens[4].trim());
                } else {
                    throw new BadFormatException("Invalid type of account: " + type
                        + ", should be [Checking|Savings|Investment]");
                }

                if(isClosed){
                    addClosed(ba);
                } else {
                    add(ba);
                }
            } catch(BadFormatException e){
                System.out.println("Error at line: " + line);
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        fileScanner.close();
    }
    /**
     * Accessor for the number of accounts currently in the bank
     * @return the number of accounts currently stored
     */
    public int size(){
        return count;
    }
    /**
     * Accessor for the number of closed accounts 
     * @return the number of accounts currently stored in the closed array
     */
    public int closedSize(){
        return closedCount;
    }
    /**
     * Adds a new account to the bank at the next available index
     * and increments count
     * @param ba the BankAccount to add
     */
    public void add(BankAccount ba){
        accounts[count] = ba;
        count++;
    } 
    /**
     * Adds the given account to the closed back account array
     * @param ba the BankAccount closed
     */
    private void addClosed(BankAccount ba){
       closed[closedCount] = ba;
        closedCount++;
    } 
    /**
     * Searches for an account by its account number
     * @param number the account number to search for
     * @return the matching BankAccount if found or else null
     */
    public BankAccount find(long number){
        for(int i = 0; i < count; i++){
            if(accounts[i].getNumber() == number){
                return accounts[i];
            }
        }
        return null;
    }
    /**
     * Searches for an account by its account number and removes it
     * if found and shifts all subsequent accounts down one index
     * then decrements count
     * @param number the account number to remove
     * @return the removed BankAccount if found, null otherwise
     */
    public BankAccount remove(long number){
        for (int i=0; i<count; i++){
            if(accounts[i].getNumber() == number){
                BankAccount removed = accounts[i];
                for(int j = i; j < count - 1; j++){
                    accounts[j] = accounts[j+1];
                }
                count--;
                return removed;
            }
        }
        return null;
    }
    /**
     * Sorts the accounts array in ascending order by balance,
     */
    public void sort(){
        Arrays.sort(accounts, 0, count);
    }
    /**
    * Saves all open and closed accounts to the given file in CSV format,
    * using fileString() to format each account. Closed accounts have an
    * additional "closed" token appended. Does not print any summary output;
    * the caller is responsible for reporting how many accounts were saved.
    * @param filename the file to write the accounts to
    */
    public void save(String filename){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            for(int i = 0; i < count; i++){
                writer.println(accounts[i].fileString());
            }
            for(int i = 0; i < closedCount; i++){
                writer.println(closed[i].fileString() + ",closed");
            }
            writer.close();
        } catch(IOException e){
            System.out.println("Error writing to file: " + filename);
        }
    }
     /**
     * Accessor for the Bank's list of accounts
     * @return a formatted string listing all accounts currently
     *         in the bank, one per line, preceded by a header row
     */
    public String toString(){
        String str = String.format("%-16s%-16s\t%-30s\t%-12s%-10s\n",
                                "Type", "Number", "Owner", "Balance", "Interest/Type");
        for (int i = 0; i < count; i++){
             str += accounts[i].toString() + "\n";
        }
        return str;
    }
    /**
     * Applies the monthly interest to every Savings account
     */
    public void updateSavings(){
        int n=0;
        for(int i=0; i < count; i++){
            if(accounts[i] instanceof Savings){
                ((Savings)accounts[i]).applyMonthlyInterest();
                n++;
            }
        }
        System.out.println(n + " Savings accounts updated");
    }
    /**
     * Applies profit or loss to every Investment account for the given risk
     * @param risk the risk factor passed to getProfitOrLoss
     */
    public void updateInvestment(double risk){
        int n = 0;
        for(int i = 0; i < count; i++){
            if(accounts[i] instanceof Investment){
                ((Investment)accounts[i]).getProfitOrLoss(risk);
                n++;
            }
        }
        System.out.println(n + " Investment accounts were updated");
    }  
    /**
     * Prints only the Checking accounts and how many were found
     */
    public void viewChecking(){
        int n = 0;
        for(int i = 0; i < count; i++){
            if(accounts[i] instanceof Checking){
                System.out.println(accounts[i]);
                n++;
            }
        }
        System.out.println("There are " + n + " Checking accounts");
    }

    /**
     * Prints only the Savings accounts and how many were found
     */
    public void viewSavings(){
        int n = 0;
        for(int i = 0; i < count; i++){
            if(accounts[i] instanceof Savings){
                System.out.println(accounts[i]);
                n++;
            }
        }
        System.out.println("There are " + n + " Savings accounts");
    }

    /**
     * Prints only the Investment accounts and how many were found
     */
    public void viewInvestment(){
        int n = 0;
        for(int i = 0; i < count; i++){
            if(accounts[i] instanceof Investment){
                System.out.println(accounts[i]);
                n++;
            }
        }
        System.out.println("There are " + n + " Investment accounts");
    }

    /**
     * Prints the accounts eligible for closure
     */
    public void viewCloseable(){
        int n = 0;
        for(int i = 0; i < count; i++){
            if(accounts[i].isCloseable()){
                System.out.println(accounts[i]);
                n++;
            }
        }
        if(n == 0){
            System.out.println("There are no closeable accounts");
        } else {
            System.out.println(n + " accounts are closeable");
        }
    }

    /**
     * Prints the accounts that have already been closed
     */
    public void viewClosed(){
        for(int i = 0; i < closedCount; i++){
            System.out.println(closed[i]);
        }
        System.out.println(closedCount + " accounts are closed");
    }
    /**
     * Moves all closeable accounts from accounts to closed
     */
    public void closeAccounts(){
        int n = 0;
        for(int i = 0; i < count; ){
            if(accounts[i].isCloseable()){
                BankAccount ba = accounts[i];
                for(int j = i; j < count - 1; j++){
                    accounts[j] = accounts[j+1];
                }
                count--;
                addClosed(ba);
                n++;
            } else {
                i++;
            }
        }
        if(n == 0){
            System.out.println("No account closed: there are no closeable accounts");
        } else {
            System.out.println(n + " accounts were closed");
        }
    }
}