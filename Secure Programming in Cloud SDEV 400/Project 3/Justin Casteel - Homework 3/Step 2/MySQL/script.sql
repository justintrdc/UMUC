Create table Courses (
	Subject varchar(50) NOT NULL,
	CatalogNbr Integer NOT NULL,
	Title varchar(75) NOT NULL,
	NumCredits Integer NOT NULL,
	CourseID Integer PRIMARY KEY
	);
	
Create table Students (
	Firstname varchar(50) NOT NULL,
	Lastname varchar(50) NOT NULL,
	email varchar(75) NOT NULL,
	YearofEnrollment Integer NOT NULL,
	StudentID Integer PRIMARY KEY
	);
	
Create table StudentCourses (
	StudentID Integer NOT NULL,
	CourseID Integer NOT NULL,
	PRIMARY KEY (StudentID, CourseID),
	FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
	FOREIGN KEY (CourseID) REFERENCES Courses(CourseID)
	);
	
insert into Courses values ('Math', 54, 'Learning Math', 3, 1);
insert into Courses values ('Science', 78, 'Learning Science', 3, 2);
insert into Courses values ('Social Studies', 12, 'The Frontier', 4, 3);
insert into Courses values ('Programming', 400, 'Secure Programming in the Cloud', 3, 4);
insert into Courses values ('Graphics', 320, 'Illustration Graphics', 3, 5);

insert into Students values ('Justin', 'Casteel', 'justin@gmail.com', 2016, 1);
insert into Students values ('Mary', 'Jones', 'jones@gmail.com', 2016, 2);
insert into Students values ('Jerreak', 'Smith', 'smith@gmail.com', 2015, 3);
insert into Students values ('Donna', 'Casteel', 'donna@gmail.com', 2018, 4);
insert into Students values ('Erin', 'Casteel', 'erin@gmail.com', 2018, 5);

insert into StudentCourses values (2, 2);
insert into StudentCourses values (2, 3);
insert into StudentCourses values (2, 1);
insert into StudentCourses values (1, 2);
insert into StudentCourses values (1, 4);
insert into StudentCourses values (1, 5);
insert into StudentCourses values (3, 1);
insert into StudentCourses values (4, 1);
insert into StudentCourses values (5, 4);
insert into StudentCourses values (5, 5);