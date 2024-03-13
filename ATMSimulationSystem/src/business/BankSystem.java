package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankSystem {

	private String bankName;
	private String bankLocation;
	private ArrayList <Customer> accountHolders;
	private Map <Integer, List<String> >bankTransactions;

	public BankSystem() {
		bankName = "";
		bankLocation = "";
		accountHolders = null;
		bankTransactions = new HashMap<Integer, List<String>>(); 
		//accountHolderSavings = null;

	}

	public BankSystem(String bankName, String bankLocation, ArrayList<Customer> accountHolders) {

		this.bankName = bankName;
		this.bankLocation = bankLocation;
		this.accountHolders = accountHolders;
		bankTransactions = new HashMap<Integer, List<String>>(); 
	}

	public BankSystem(String bankName, String bankLocation, Customer accountHolder,
			Map<Integer, List<String>> bankTransactions,
			HashMap<Integer, AnotherAccount> savings, List<String> transaction) {
		super();
		this.bankName = bankName;
		this.bankLocation = bankLocation;
		this.accountHolders = new ArrayList<>();
		this.bankTransactions = bankTransactions;
		this.savings = savings;
		this.transaction = transaction;
	}
	
	HashMap<Integer, AnotherAccount> savings = new HashMap<>();
	
	List<String> transaction = new ArrayList<>();

	public String getBankName() {
		return bankName;
	}

	public String getBankLocation() {
		return bankLocation;
	}

	public void withdraw(double amount, int accountNumber) {

		for(Customer accountHolder : accountHolders){

			if(accountNumber == accountHolder.getAccountNumber()) {

				if (amount > accountHolder.getBalance()) {

					System.out.println("Cannot withdraw above "+ accountHolder.getBalance());

				}

				else{
					double balance = accountHolder.getBalance() - amount;

					accountHolder.setBalance(balance);

					String withdrawTransaction = "Withdrew: "+ amount;
					transaction.add(withdrawTransaction);

					System.out.println(accountNumber);

					bankTransactions.put(accountNumber, transaction);

				}}
		}
	}

	public void deposit(double depositAmount, int accountNumber) {

		for(Customer accountHolder : accountHolders){

			if(accountNumber == accountHolder.getAccountNumber()) {

				double balance = accountHolder.getBalance() + depositAmount;

				accountHolder.setBalance(balance);

				String depositTransaction = "Deposited: "+ depositAmount;
				transaction.add(depositTransaction);
				bankTransactions.put(accountNumber, transaction);

			}
		}
	}

	public void transactionHistory(int accountNumber) {

		List<String> value = bankTransactions.get(accountNumber);

		if(value != null) {

			System.out.println("Account: "+accountNumber+" "+ value);


		}else {

			System.out.println("No transaction yet");
		}

	}

	public void savingsAccount(int accountNumber, int pin) {



		for(Customer accountHolder : accountHolders){


			if(this.hasSavingsAccount(accountNumber, pin) == true && accountNumber == accountHolder.getAccountNumber()) {

				System.out.println("A savings account already exists");
				System.out.println("Can withdraw and deposit in it...");

			}else if(accountNumber == accountHolder.getAccountNumber()) {

				int mainAcc = accountHolder.getAccountNumber();

				double savingsOpening = accountHolder.getBalance() - 100;
				accountHolder.setBalance(savingsOpening);

				double startBalance = 100;
				accountHolder.getSavingsAccount().setAccountAmount(startBalance);

				int savingsAcc = accountHolder.getAccountNumber() + 1;

				AnotherAccount savings = new AnotherAccount(mainAcc, savingsAcc, "Savings", accountHolder.getSavingsAccount().getAccountAmount());

				accountHolder.setSavingsAccount(savings);

				System.out.println("Just created: "+accountHolder.getSavingsAccount());}
		}

	}

	public void transferToSavings(double amount, int accountNumber, int pin) {

		for(Customer accountHolder : accountHolders){

			if(this.hasSavingsAccount(accountNumber, pin) == false && accountNumber == accountHolder.getAccountNumber()) {

				System.out.println("No saviong account yet...");

				System.out.println("Please create an account first");
			}else {

				int matchAcc = accountHolder.getSavingsAccount().getSavingsAccountNumber() - 1;

				if(this.hasSavingsAccount(accountNumber, pin) == true && accountNumber == matchAcc) {


					if(amount < accountHolder.getBalance()) {

						double balance = accountHolder.getBalance() - amount;
						accountHolder.setBalance(balance);

						double savings = accountHolder.getSavingsAccount().getAccountAmount() + amount;
						accountHolder.getSavingsAccount().setAccountAmount(savings);

						System.out.println(accountHolder.getSavingsAccount());

						String toSavings = "Transfered "+amount+" to Savings";
						transaction.add(toSavings);
						bankTransactions.put(accountHolder.getAccountNumber(), transaction);
					}else {

						System.out.println("Your limit is "+accountHolder.getBalance());

					}}
			}
		}
	}

	public void transferToMain(double amount, int accountNumber, int pin) {

		for(Customer accountHolder : accountHolders){

			if(this.hasSavingsAccount(accountNumber, pin) == false) {

				System.out.println("No saviong account yet...");

				System.out.println("Please create an account first");
			}else {

				int matchAcc = accountHolder.getSavingsAccount().getSavingsAccountNumber() - 1;

				if(this.hasSavingsAccount(accountNumber, pin) == true && accountNumber == matchAcc) {

					if(amount < accountHolder.getSavingsAccount().getAccountAmount()) {

						double balance = accountHolder.getSavingsAccount().getAccountAmount() - amount;
						accountHolder.getSavingsAccount().setAccountAmount(balance);

						double main = accountHolder.getBalance() + amount;
						accountHolder.setBalance(main);

						System.out.println(accountHolder.getSavingsAccount());

						String fromSavings = "Transfered "+amount+" from Savings";
						transaction.add(fromSavings);
						bankTransactions.put(accountHolder.getAccountNumber(), transaction);

					}else {

						System.out.println("Your limit is "+accountHolder.getSavingsAccount().getAccountAmount());

					}}
			}
		}
	}

	public boolean hasSavingsAccount(int accountNumber, int pin) {

		boolean hasAccount = true;

		for(Customer accountHolder : accountHolders){

			if(accountNumber == accountHolder.getAccountNumber() && 
					pin == accountHolder.getPinNumber()) {

				if(accountHolder.getSavingsAccount().getAccountAmount() == 0) {


					hasAccount = false;

				}
			}
		}

		return hasAccount;


	}

	public boolean validIdNumber(int id) {

		boolean isValid = false;

		for(Customer accountHolder : accountHolders){

			if(id == accountHolder.getIdNumber()) {

				isValid = true;
			}
		}

		return isValid;
	}

	public boolean validatePin(int inputPin) {

		boolean isValid = false;

		for(Customer accountHolder : accountHolders){


			if(inputPin == accountHolder.getPinNumber()) {

				isValid = true;
			}
		}

		return isValid;
	}

	public void accountHolderProfile(int id, int pin) {
		for(Customer accountHolder : accountHolders) {

			if(id == accountHolder.getIdNumber() && pin == accountHolder.getPinNumber()) {
				String profile = "User Account:\nFull Name:" + accountHolder.getFirstName() + " " + accountHolder.getLastName() + "\n"
						+ "ID Number:" + accountHolder.getIdNumber()
						+ "\nAccount Number(current):" + accountHolder.getAccountNumber()
						+"\nFrom: "+ this.bankName +" located at: "+this.bankLocation
						+ "\nBalance:" + accountHolder.getBalance() + "\n";

				System.out.println(profile);
			}
		}
	}

	public void accountHolderFirstName(int id, int pin) {
		for(Customer accountHolder : accountHolders) {

			if(id == accountHolder.getIdNumber() && pin == accountHolder.getPinNumber()) {
				String firstName = "" + accountHolder.getFirstName() + " " ;

				System.out.println(firstName);
			}
		}
	}

	public int accountHolderAccountNumbere(int id, int pin) {

		int accountNumber = 0;

		for(Customer accountHolder : accountHolders) {

			if(id == accountHolder.getIdNumber() && pin == accountHolder.getPinNumber()) {
				int accountNumber2 = accountHolder.getAccountNumber();

				accountNumber = accountNumber2;
			}
		}
		return accountNumber;
	}

}
