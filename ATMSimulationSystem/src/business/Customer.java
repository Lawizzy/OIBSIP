package business;

public class Customer {
	
	private String firstName;
	private String lastName;
	private int idNumber;
	private int accountNumber;
	private int pinNumber;
	//private HashMap<Integer, AnotherAccount> otherAccounts;
	private AnotherAccount savingsAccount;
	private double balance;
	
	public Customer() {
		firstName = "";
		lastName = "";
		idNumber = 123456789;
		accountNumber = 00000000;
		pinNumber = 0000;
		//savingsAccount = null;
		balance = 0.0;
	}
	
	public Customer(String firstName, String lastName, int idNumber, int accountNumber, int pinNumber, double balance, AnotherAccount savingsAccount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.accountNumber = accountNumber;
		this.pinNumber = pinNumber;
		this.balance = balance;
		this.savingsAccount = savingsAccount;

	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

//	public HashMap<Integer, AnotherAccount> getOtherAccounts() {
//		return otherAccounts;
//	}
//
//	public void setOtherAccounts(HashMap<Integer, AnotherAccount> otherAccounts) {
//		this.otherAccounts = otherAccounts;
//	}

	public double getBalance() {
		return balance;
	}

	public AnotherAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(AnotherAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User Account:\nFull Name:" + firstName + " " + lastName + "\n"
				+ "ID Number:" + idNumber
				+ "\nAccount Number(current):" + accountNumber
				+ "\nBalance:" + balance + "\n";
					//	+ "accountTypes:" + savingsAccount + "\n";
	}
	
	

}
