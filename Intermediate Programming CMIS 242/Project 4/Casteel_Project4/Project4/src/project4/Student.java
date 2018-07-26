package project4;

public class Student {
	
	//Instance variables for the student name, major and two variables that are used to compute the GPA.
	private String studentName;
	private String major;
	private int credits;
	private int qualityPoints;
	private boolean isCompleted = false;
	
	//A constructor that is used when new student records are created.
	public Student(String studentName, String major) {
		this.studentName = studentName;
		this.major = major;
		credits = 0;
		qualityPoints = 0;
	}
	
	//Accepts the course grade and credit hours and updates the variables used to compute the GPA. It will be called when an Update request is made.
	public double courseCompleted(String courseGrade, int creditHours) {
		int grade = 0;
		if ("A".equals(courseGrade)) {
			grade = 4;
		} else if ("B".equals(courseGrade)) {
			grade = 3;
		} else if ("C".equals(courseGrade)) {
			grade = 2;
		} else if ("D".equals(courseGrade)) {
			grade = 1;
		}
		
		credits += creditHours;
		qualityPoints += (grade * creditHours);
		isCompleted = true;
		
		if (credits == 0)
			return 4.0;
		else 
			return (double)qualityPoints / credits;
	}
	
	//Overrides toString and returns a labeled string containing the student name, major and GPA.
	public String toString() {
		if (!isCompleted) {
			return "Name: " + studentName + "\nMajor: " + major + "\nGPA: 4.0 (not completed course)";
		}
		double gpa = qualityPoints / credits;
		return "Name: " + studentName + "\nMajor: " + major + "\nGPA: " + courseCompleted("4",0); 
	}
}
	
