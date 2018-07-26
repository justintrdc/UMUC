package project2;

public class Account {
	
	//Variables
	private double balance;
	private double totalTransactions = 0;
	private double serviceCharge = 1.50;
		
	//Default constructor
	public Account() {
	}
	
	//Sets the balance of the account.
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	//Returns the balance of the account.
	public double getBalance() {
		return this.balance;
	}
	
	//This is a subclass for savings accounts.
	public class Savings extends Account {
		public Savings() {
		}
	}
	
	//This is a subclass for checking accounts.
	public class Checking extends Account {
		public Checking() {
		}
	}
	
	//Deduct a service charge of $1.50 when more than four total withdrawals are made from either account.
	public void serviceFee() {
		if (totalTransactions == 4) {
			System.out.println("You have reached 4 combined withdraws, your next transaction will have a service charge of $1.50 deducted from your account.");
		}
		else if(totalTransactions > 4) {
			this.balance = this.balance - serviceCharge;
			System.out.println("You have exceeded 4 total combined withdraws, a service charge of $1.50 will be deducted for each additional withdrawal.");
		}
	}
		
	/**
	 * Methods for the four button action listeners.
	 */
	
	//Withdraw button method ensures that the withdraw amount does not make the account drop below $0.
	public void withdraw(double withdrawAmount) throws InsufficientFunds {
		totalTransactions++;
		if (this.balance - withdrawAmount < 0) {
			throw new InsufficientFunds();
		}
		this.balance = this.balance - withdrawAmount;
	}
	
	//Deposit button method adds the current balance to the dollar amount given by the user.
	public void deposit(double depositAmount) {
		this.balance = this.balance + depositAmount;
	}
	
	//Transfers from one bank account to the other based on user radio button selection.
	public void transferTo(double transferAmount) throws InsufficientFunds {
		this.balance = this.balance + transferAmount;
	}
	
	//Transfer From button method ensures that the transfer amount does not make the account drop below $0. 
	public void transferFrom(double transferAmount) throws InsufficientFunds {
		if (this.balance - transferAmount < 0) {
			throw new InsufficientFunds();
		}
		this.balance = this.balance - transferAmount;
		}
	}
