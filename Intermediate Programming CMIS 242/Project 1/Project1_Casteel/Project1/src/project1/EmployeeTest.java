package project1;

import java.io.IOException;
import java.util.Scanner;

public class EmployeeTest {

	//Main method
    public static void main (String [] args) throws IOException {
    	
    	try {
    		
    		java.io.File file = new java.io.File("employeedata.txt");
    		
    		Scanner input = new Scanner(file);
    		
    		//Create arrays for years 2014 and 2015
    		Employee year2014[] = new Employee[17];
    		Employee year2015[] = new Employee[17];
    		
    		//Create index for year 2014 and 2015 arrays.
    		int indexyear2014 = 0, indexyear2015 = 0;
    		
    		//Read data from employeedata.txt
    		while (input.hasNext()) {
    			String year = input.next();
    			String employeeType = input.next();
    			String employeeName = input.next();
    			int monthlySalary = input.nextInt();
    			
    			//Checks to see if the current employee is a regular employee.
    			if (employeeType.equalsIgnoreCase("Employee")) {
    				Employee regularEmployee = new Employee(employeeName, monthlySalary);
    		    				
    				if (year.equals("2014")) {
    					year2014[indexyear2014++] = regularEmployee;
    				}
    					
    				if (year.equals("2015")) {
        				year2015[indexyear2015++] = regularEmployee;
    				}
    			}
        					
        		//Checks to see if the current Employee is a salesman.
        		if (employeeType.equalsIgnoreCase("Salesman")) {
        			 int annualSales = input.nextInt();
        			 
        			 Salesman salesmanEmployee = new Salesman(employeeName, monthlySalary, annualSales);
        			 
        			 if (year.equals("2014")) {
     					year2014[indexyear2014++] = salesmanEmployee;
        			 }
     					
     				 if (year.equals("2015")) {
         				year2015[indexyear2015++] = salesmanEmployee;
     				 }
        		}
         				
         		//Checks to see if the current Employee is an Executive.
         		if (employeeType.equalsIgnoreCase("Executive")) {
         			int stockPrice = input.nextInt();
         		         		               		               			 
               		Executive executiveEmployee = new Executive(employeeName, monthlySalary, stockPrice);
               			 
               		if (year.equals("2014")) {
            			year2014[indexyear2014++] = executiveEmployee;
               		}
            					
            		if (year.equals("2015")) {
                		year2015[indexyear2015++] = executiveEmployee;
            		}
         		}
    		}
    		
    		//Print report for 2014 Employees
    		int totalSalary = 0;

            System.out.println("***** 2014 Salary Report *****");

            for (int i = 0; i < indexyear2014; i++) {
                System.out.print(year2014[i]);
                System.out.println(" Annual Salary: " +
                        year2014[i].annualSalary());
            }

            for (int i = 0; i < indexyear2014; i++) {
                totalSalary+= year2014[i].annualSalary();
            } 

            System.out.println("\nAverage Salary for 2014: " +
                    (totalSalary / indexyear2014));  
          
            //return totalSalary to 0 to calculate 2015 report.
            totalSalary = 0;
            
            // Print report for 2015 Employees
            System.out.println("\n***** 2015 Salary Report *****");
            
            for (int i = 0; i < indexyear2015; i++) {
                System.out.print(year2015[i]);
                System.out.println(" Annual Salary: " +
                        year2015[i].annualSalary());
            } 

            for (int i = 0; i < indexyear2015; i++) {
                totalSalary+= year2015[i].annualSalary();
            }

            System.out.println("\nAverage Salary for 2015: " +
                    (totalSalary / indexyear2015));
            
            input.close();
            
    	}                       
            		  catch (IOException i) {
                      System.out.println("Error: File Not Found");
            		}  
    			}
}