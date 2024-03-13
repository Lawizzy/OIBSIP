package business;

public class AnotherAccount {
	
	private int mainAccountNumber;
	private int savingsAccountNumber;
	private String accountName;
	private double accountAmount;
	
	public AnotherAccount() {
		
		mainAccountNumber = 0;
		savingsAccountNumber = 0;
		accountName = "None";
		accountAmount = 0.0;
		
	}

	public AnotherAccount(int mainAccountNumber, int savingsAccountNumber, String accountName, double accountAmount) {
	
		this.mainAccountNumber = mainAccountNumber;
		this.savingsAccountNumber = savingsAccountNumber;
		this.accountName = accountName;
		this.accountAmount = accountAmount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(double accountAmount) {
		this.accountAmount = accountAmount;
	}

	public int getSavingsAccountNumber() {
		return savingsAccountNumber;
	}

	public void setSavingsAccountNumber(int savingsAccountNumber) {
		this.savingsAccountNumber = savingsAccountNumber;
	}

	@Override
	public String toString() {
		return "\n*******************************************************\n"
				+ "AnotherAccount\nmainAccountNumber: " + mainAccountNumber + "\nsavingsAccountNumber: "
				+ savingsAccountNumber + "\nAccountName: " + accountName + "\nAccountAmount: " + accountAmount + "\n";
	}

//	@Override
//	public String toString() {
//		return "AnotherAccount:\nAccount Name: " + accountName + "\nAccount Amount: "
//				+ accountAmount + "\n";
//	}
	
	
	
	

}
