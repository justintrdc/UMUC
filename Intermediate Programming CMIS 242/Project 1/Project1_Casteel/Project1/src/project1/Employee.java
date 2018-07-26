package project1;

public class Employee {

	protected String employeeName;
	protected int monthlySalary;
	
	//A constructor that allows the name and monthly salary to be initialized. 
	public Employee(String employeeName, int monthlySalary) {
		this.employeeName = employeeName;
		this.monthlySalary = monthlySalary;
	}
	
	//A method named annualSalary that returns the salary for a whole year.
	public double annualSalary() {
		return monthlySalary * 12;
	}
	
	//A toString method that returns a string containing the name and monthly salary.
	public String toString() {
		return "Employee Name: " + employeeName + " Employee Salary: " + monthlySalary;
	}
}