package project1;

public class Salesman extends Employee {
	
	private double annualSales;

	//A constructor that allows the name, monthly salary and annual sales to be initialized.
	public Salesman(String employeeName, int monthlySalary, int annualSales) {
		super(employeeName, monthlySalary);
		this.annualSales = annualSales;
	}
	
	//The commission is computed as 2% of that salesman's annual sales.
	public double annualSalary() {
		double commission = this.annualSales * .02;
	
		//The maximum commission a salesman can earn is $20,000
		if (commission > 20000) {
			commission = 20000;
		}
		
		return super.annualSalary() + commission;
	}
	
	//An overridden toString method that returns a string containing the name, monthly salary and annual sales.
	public String toString() {
		return "Employee Name: " + employeeName + " Monthly Salary: " + monthlySalary + " Annual Sales: " + annualSales;
	}
}
