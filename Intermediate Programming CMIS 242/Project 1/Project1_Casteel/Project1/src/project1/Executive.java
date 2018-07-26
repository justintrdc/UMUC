package project1;

public class Executive extends Employee {
	
	private int stockPrice;

	//A constructor that allows the name, monthly salary and stock price to be initialized.
	public Executive(String employeeName, int monthlySalary, int stockPrice) {
		super(employeeName, monthlySalary);
		this.stockPrice = stockPrice;
}
	
	//An overridden method annualSalary that returns the salary for a whole year.
	public double annualSalary() {
		double bonus = 0;
		
		if(this.stockPrice > 50) {
			bonus = 30000;
		}
		
		return super.annualSalary() + bonus;
	}
	
	//An overridden toString method that returns a string containing the name, monthly salary and stock price.
	public String toString() {
		return "Employee Name: " + employeeName + " Monthly Salary: " + monthlySalary + " Stock Price: " + stockPrice;
	}
}