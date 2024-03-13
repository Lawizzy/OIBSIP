package presentation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import business.AnotherAccount;
import business.BankSystem;
import business.Customer;

public class ATMSimulatorApp {

	public static int attempts = 3;

	public static void main(String[] args) {
		
		BankSystem bank = new BankSystem();
		AnotherAccount savings = new AnotherAccount();
		
		ArrayList<Customer>customers = new ArrayList<>();

		customers.add(new Customer("Hermione", "Granger", 123456789, 12345678, 1234, 80000.00, savings));
		customers.add(new Customer("Harry", "Potter", 987654321, 87654321, 4321, 100000.00, savings));
		customers.add(new Customer("Ronald", "Weasley", 111111111, 11111111, 1111, 9000.00, savings));
		
		bank = new BankSystem("Mountain View ATM", "Block 5 city", customers);

		Scanner sc = new Scanner(System.in);

		System.out.println("\n********Luxor Bank ATM*********\n");

		int id = validateIntInput("Enter you ID: ", sc);

		boolean isValid = bank.validIdNumber(id);

		//System.out.println("Id is "+isValid);

		int pin = validateIntInput("Enter your PIN: ", sc);

		boolean isCorrect = bank.validatePin(pin);

		//System.out.println("Pin is "+isCorrect);


		while(isCorrect && isValid) {
			
			int accountNumber = bank.accountHolderAccountNumbere(id, pin);

			try {
			
			System.out.println("\n********Luxor Bank ATM*********\n");
			
			System.out.print("Good day, ");
			bank.accountHolderFirstName(id, pin);
			System.out.println();

			bank.accountHolderProfile(id, pin);

			System.out.println("Main menu here\n");

			System.out.println("Press 1: Cash WITHDRAWAL");
			System.out.println("Press 2: Cash DEPOSIT");
			System.out.println("Press 3: Cash TRANSFER");
			System.out.println("Press 4: Transaction HISTORY");
			System.out.println("Press 5: EXIT");
			System.out.println("\n********************************\n");

			int choice = sc.nextInt();

			switch(choice) {

			case 1:
				System.out.println("How much are you withdraw");

					double withdraw = sc.nextDouble();

					
					bank.withdraw(withdraw, accountNumber);

				break;

			case 2:
				System.out.println("How much are you deposit");

					double deposit = sc.nextDouble();


					bank.deposit(deposit, accountNumber);
				
				break;

			case 3:
				System.out.println("Transfer menu here\n");

				if(bank.hasSavingsAccount(accountNumber, pin) == true) {

					System.out.println("Press B: Transfer to Savings");
					System.out.println("Press C: Transfer to Current");
					System.out.println("Press D: EXIT to MAIN");

				}else {

					System.out.println("Press A: Create Savings Account");
					System.out.println("Press B: Transfer to Savings");
					System.out.println("Press C: Transfer to Current");
					System.out.println("Press D: EXIT to MAIN");

				}


				char choice2 = Character.toUpperCase(sc.next().charAt(0));

				switch (choice2) {
				case 'A':
					bank.savingsAccount(accountNumber, pin);
					break;

				case 'B':
					System.out.println("Enter Amount");

					if(sc.hasNextInt()) {
						double amount = sc.nextDouble();

						bank.transferToSavings(amount, accountNumber, pin);

					}else {

						System.out.println("Please enter amount in numbers!!");
					}
					
					break;

				case 'C':
					System.out.println("Enter Amount");

						double amount2 = sc.nextDouble();

						bank.transferToMain(amount2, accountNumber, pin);

					break;

				case 'D':
					break;

				default:
					System.err.println("Invalid choice!!!\n"
							+ "Please re-enter option");
					break;
				}
				break;

			case 4:
				bank.transactionHistory(accountNumber);
				break;

			case 5:
				System.out.println("Thank you for using our Bank :)");
				System.exit(0);
				break;

			default:
				System.err.println("Invalid choice!!!\n"
						+ "Please re-enter option");

			}
			}catch(InputMismatchException e) {
				
				System.err.println("Invalid Input\nAccepting numbers only\n");
				sc.next();
				
			}

			//sc.close();

		}
					if(attempts != 0) {
		
						while(attempts != 0) {
		
		
							System.err.println("Wrong ID or PIN!!!!");
							System.out.println("Your have "+attempts+" attempts remaining");
		
							attempts--;
		
							main(args);
		
		
						}
					}else {

		System.out.println("Good bye...\nPlease remember your cridencials next time");
		}

	}
	
	private static int validateIntInput(String prompt, Scanner sc) {
		
		int input = 0;
		boolean isValid = false;
		
		do {
		try {
			System.out.println(prompt);
			input = sc.nextInt();
			isValid = true;
		}catch(InputMismatchException e) {
			
			System.err.println("Invalid Input\nAccepting numbers only\n");
			sc.next();
		}
		}while(!isValid);
		
		return input;
	}

}
