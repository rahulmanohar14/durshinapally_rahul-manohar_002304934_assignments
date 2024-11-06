/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.random;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Faculty.FacultyAssignment;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.Person;
import info5100.university.example.Persona.PersonDirectory;
import info5100.university.example.Persona.StudentDirectory;
import info5100.university.example.Persona.StudentProfile;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author kal bugrara
 */
public class Info5001UniversityExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Department department = new Department("Information Systems");
        CourseCatalog coursecatalog = department.getCourseCatalog();
        StudentDirectory studentDirectory = department.getStudentDirectory();
        

        
        // Predefined courses
        Course[] predefinedCourses = {
            new Course("app engineering", "info 5100", 4),
            new Course("web design", "info 5200", 4),
            new Course("database architecture", "info 5300", 4),
            new Course("system design", "info 5400", 4),
            new Course("networking", "info 5500", 4)
        };
        for (Course course : predefinedCourses) {
            coursecatalog.newCourse(course.getName(), course.getCOurseNumber(), course.getCredits());
        }
        
 
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        String semester = scanner.nextLine();
        CourseSchedule courseSchedule = department.newCourseSchedule(semester);
        courseSchedule.generateCourseOffers(coursecatalog);
        
         ArrayList<StudentProfile> students = createStudentProfiles();


        String semester1 = "Fall2024"; 
      
    

 
            boolean exitMenu = false;
            while (!exitMenu) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Course Schedule");
            System.out.println("3. Manage Student Course Registrations");
            System.out.println("4. Generate Semester Report");
            //System.out.println("5. Generate Department Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                manageCourseCatalog(coursecatalog, scanner);
                break;
            case 2:
                manageCourseSchedule(coursecatalog, courseSchedule, scanner);
                break;
            case 3:
                manageStudentCourseRegistrations(studentDirectory, courseSchedule, scanner);
                break;
            case 4:
                generateSemesterReport(students, semester1);
                break;

            case 5:
                exitMenu = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    
        
        scanner.close();
    }

    private static void browseCourseCatalog(CourseCatalog courseCatalog) {
        System.out.println("Course Catalog:");
        for (Course course : courseCatalog.getCourseList()) {
            System.out.println("Name: " + course.getName() + ", Number: " + course.getCOurseNumber() + ", Credits: " + course.getCredits());
        }
    }

    private static void addNewCourse(CourseCatalog courseCatalog, Scanner scanner) {
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        System.out.print("Enter course number: ");
        String number = scanner.nextLine();

        System.out.print("Enter course credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Course course = courseCatalog.newCourse(name, number, credits);
        System.out.println("Course added successfully: " + course.getName());
    }

    private static void addNewCourseOffer(CourseCatalog courseCatalog, CourseSchedule courseSchedule, Scanner scanner) {

        System.out.println("Available Courses:");
        for (Course course : courseCatalog.getCourseList()) {
            System.out.println(course.getCOurseNumber() + ": " + course.getName());
        }

        System.out.print("Enter the course number for the new course offer: ");
        String courseNumber = scanner.nextLine();

        Course course = courseCatalog.getCourseByNumber(courseNumber);
        if (course == null) {
            System.out.println("Course not found in catalog.");
            return;
        }

        System.out.print("Enter the faculty name for this course offer: ");
        String facultyId = scanner.nextLine();

        CourseOffer courseOffer = courseSchedule.newCourseOffer(courseNumber);
        PersonDirectory personDirectory = new PersonDirectory(); // Assuming you have a PersonDirectory class


        Person person = personDirectory.newPerson(facultyId);
        
        if (person == null) {
        System.out.println("Faculty not found.");
        return;
    }

        FacultyProfile facultyProfile = new FacultyProfile(person);
        courseOffer.AssignAsTeacher(facultyProfile);
        System.out.println("Course offer added successfully.");
    }

    private static void displayCourseSchedule(CourseSchedule courseSchedule) {
        
        
        System.out.println("\nCourse Schedule:");
        System.out.println("Semester:  "+courseSchedule.getSemester());
        System.out.println("Course Offers:");
        
       ArrayList<CourseOffer> courseOffers = courseSchedule.getCourseOffers();

    for (CourseOffer courseOffer : courseOffers) {
        Course course = courseOffer.getCourse();
        String courseName = course.getName();
        String courseNumber = course.getCOurseNumber();
        
        // Check if faculty assignment exists
        FacultyAssignment facultyAssignment = courseOffer.getFacultyassignment();
        if (facultyAssignment != null) {
            FacultyProfile facultyProfile = facultyAssignment.getFacultyProfile();
            if (facultyProfile != null) {
                Person facultyPerson = facultyProfile.getPerson();
                String facultyName = facultyPerson.getPersonId(); // Assuming personId is the faculty name
                System.out.println("Course: " + courseName + " | Number: " + courseNumber + " | Faculty: " + facultyName);
            } else {
                System.out.println("Course: " + courseName + " | Number: " + courseNumber + " | No faculty assigned");
            }
        } else {
            System.out.println("Course: " + courseName + " | Number: " + courseNumber + " | No faculty assignment");
        }
    }
    }
    
    
        private static void manageStudentCourseRegistrations(StudentDirectory studentDirectory, CourseSchedule courseSchedule, Scanner scanner) {
           while (true) {
            System.out.println("\nChoose Student Registration Option:");
            System.out.println("1. New Student Registration");
            System.out.println("2. Existing Student Registration");
           // System.out.println("3. View Registrations by Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int registrationOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (registrationOption) {
                case 1:
                    // New student registration
                    registerNewStudent(studentDirectory, courseSchedule, scanner);
                    break;
                case 2:
                    // Existing student registration
                    registerExistingStudent(studentDirectory, courseSchedule, scanner);
                    break;

                
                case 3:
                    // Exit
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void registerNewStudent(StudentDirectory studentDirectory, CourseSchedule courseSchedule, Scanner scanner) {
        // Prompt user for new student ID
        System.out.print("Enter new student ID: ");
        String studentId = scanner.nextLine();

        // Create new student profile
        Person studentPerson = new Person(studentId);
        StudentProfile studentProfile = studentDirectory.newStudentProfile(studentPerson);

        // Proceed with course registration
        processCourseRegistration(studentProfile, courseSchedule, scanner);
    }

     // Inside the registerExistingStudent method

private static void registerExistingStudent(StudentDirectory studentDirectory, CourseSchedule courseSchedule, Scanner scanner) {
    // Predefined students
    String[][] predefinedStudents = {
        {"001", "Rithik"},
        {"002", "Andrew"},
        {"003", "Adith"},
        {"004", "Nikitha"},
        {"005", "Charlie"}
    };

    // Display predefined students
    System.out.println("Predefined Students:");
    for (String[] student : predefinedStudents) {
        System.out.println("ID: " + student[0] + ", Name: " + student[1]);
    }

    // Prompt user to select an existing student by ID
    System.out.print("Enter existing student ID to register: ");
    String studentId = scanner.nextLine();

    // Check if the entered student ID is valid
    boolean isValidStudent = false;
    for (String[] student : predefinedStudents) {
        if (student[0].equals(studentId)) {
            isValidStudent = true;
            break;
        }
    }

    if (!isValidStudent) {
        System.out.println("Invalid student ID.");
        return;
    }

    // Retrieve the existing student profile from the student directory
    StudentProfile studentProfile = studentDirectory.findStudent(studentId);
    if (studentProfile == null) {
        // Create a new student profile if not found
        Person studentPerson = new Person(studentId);
        studentProfile = studentDirectory.newStudentProfile(studentPerson);
    }

    // Proceed with course registration
    processCourseRegistration(studentProfile, courseSchedule, scanner);
}

 
  private static void processCourseRegistration(StudentProfile studentProfile, CourseSchedule courseSchedule, Scanner scanner) {
    // Prompt user to select a semester
    System.out.print("Enter semester: ");
    String semester = scanner.nextLine();

    CourseLoad courseLoad = studentProfile.getCourseLoadBySemester(semester);
    if (courseLoad == null) {
        courseLoad = studentProfile.newCourseLoad(semester);
    }

    // Display available courses from the course catalog for registration
    CourseCatalog courseCatalog = courseSchedule.getCoursecatalog();
    System.out.println("Available Courses for Registration:");
    for (Course course : courseCatalog.getCourseList()) {
        System.out.println("Number: " + course.getCOurseNumber() + ", Name: " + course.getName());
    }

    // Prompt user to select a course for registration
    System.out.print("Enter the course number to register for: ");
    String courseNumber = scanner.nextLine();

    // Check if the entered course number matches any of the predefined courses
    Course selectedCourse = null;
    for (Course course : courseCatalog.getCourseList()) {
        if (course.getCOurseNumber().equals(courseNumber)) {
            selectedCourse = course;
            break;
        }
    }

    // If the selected course is not found in the predefined list, show an error
    if (selectedCourse == null) {
        System.out.println("Course not found.");
        return;
    }

    // Retrieve the course offer from the course schedule
    CourseOffer courseOffer = courseSchedule.getCourseOfferByNumber(courseNumber);
    if (courseOffer == null) {
        System.out.println("Course offer not found.");
        return;
    }

    // Register student for the selected course
    SeatAssignment seatAssignment = courseOffer.assignEmptySeat(courseLoad);
    if (seatAssignment == null) {
        System.out.println("No available seats for this course.");
    } else {
        // If registration is successful, display confirmation message
        System.out.println("Student successfully registered for course.");

        // Retrieve the student ID from the student profile
        String studentId = studentProfile.getStudentId(); // Assuming getStudentId() returns the student's ID

        // Set the student ID in the seat assignment
        seatAssignment.setStudentId(studentId);
    }
}



 private static void viewRegistrationsByCourse(CourseSchedule courseSchedule) {
    System.out.println("\nRegistrations by Course:");

    ArrayList<CourseOffer> courseOffers = courseSchedule.getCourseOffers();

    for (CourseOffer courseOffer : courseOffers) {
        // Retrieve course details
        Course course = courseOffer.getCourse();
        String courseName = course.getName();
        String courseNumber = course.getCOurseNumber();

        System.out.println("\nCourse: " + courseName + " (" + courseNumber + ")");
        ArrayList<SeatAssignment> seatAssignments = courseOffer.getSeatAssignments();

        if (seatAssignments != null && !seatAssignments.isEmpty()) {
            for (SeatAssignment seatAssignment : seatAssignments) {
                String studentId = seatAssignment.getStudentId();
                System.out.println("Student ID: " + studentId);
            }
        } else {
            System.out.println("No registrations yet.");
        }
    }
}
   
        private static void manageCourseCatalog(CourseCatalog courseCatalog, Scanner scanner) {
         boolean exitCatalogMenu = false;
         while (!exitCatalogMenu) {
             System.out.println("\nCourse Catalog Management:");
             System.out.println("1. Browse Course Catalog");
             System.out.println("2. Add a New Course");
             System.out.println("3. Exit Course Catalog Management");
             System.out.print("Enter your choice: ");

             int choice = scanner.nextInt();
             scanner.nextLine(); 

             switch (choice) {
                 case 1:
                     browseCourseCatalog(courseCatalog);
                     break;
                 case 2:
                     addNewCourse(courseCatalog, scanner);
                     break;
                 case 3:
                     exitCatalogMenu = true;
                     break;
                 default:
                     System.out.println("Invalid choice. Please try again.");
             }
         }
        
     }

     private static void manageCourseSchedule(CourseCatalog courseCatalog, CourseSchedule courseSchedule, Scanner scanner) {
         boolean exitScheduleMenu = false;
         while (!exitScheduleMenu) {
             System.out.println("\nCourse Schedule Management:");
             System.out.println("1. Add a New Course Offer");
             System.out.println("2. Display Course Schedule");
             System.out.println("3. Exit Course Schedule Management");
             System.out.print("Enter your choice: ");

             int choice = scanner.nextInt();
             scanner.nextLine(); // Consume newline

             switch (choice) {
                 case 1:
                     System.out.print("Enter the semester for the new course schedule: ");
                     String semester = scanner.nextLine();
                     courseSchedule = courseCatalog.getDepartment().newCourseSchedule(semester); // Update courseSchedule with new semester
                     addNewCourseOffer(courseCatalog, courseSchedule, scanner);
                     break;
                 case 2:
                     displayCourseSchedule(courseSchedule);
                     break;
                 case 3:
                     exitScheduleMenu = true;
                     break;
                 default:
                     System.out.println("Invalid choice. Please try again.");
             }
         }
            
            
     }
     

    private static ArrayList<StudentProfile> createStudentProfiles() {
        ArrayList<StudentProfile> students = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String studentId = String.format("%03d", i); 
            Person person = new Person("Name" + i); 
            StudentProfile studentProfile = new StudentProfile(person); 
            studentProfile.setStudentId(studentId); 
            students.add(studentProfile);
        }
        return students;
    }
    
    
    
  
    
    private static double convertGradeToGPA(char grade) {
        switch (grade) {
            case 'A':
                return 4.0;
            case 'B':
                return 3.0;
            case 'C':
                return 2.0;
            case 'D':
                return 1.0;
            default:
                return 0.0; 
        }
    }
  
    
    private static void populateDepartmentData(Department department, CourseCatalog courseCatalog, CourseSchedule courseSchedule) {
        
          Course coreCourse = courseCatalog.newCourse("Core Course", "INFO_CORE", 4);


    Course[] electiveCourses = { 
    new Course("Info 5100", "Application Engineering and Development", 4),
    new Course("Data Science", "Data Analytics Fundamentals", 4),
    new Course("Machine Learning", "Intro to Machine Learning", 4),
    new Course("Cybersecurity", "Principles of Cybersecurity", 4),
    new Course("Blockchain", "Blockchain Technology", 4),
    new Course("IoT", "Internet of Things", 4),
    new Course("Artificial Intelligence", "AI and Society", 4),
    new Course("Web Design", "User Experience and Interface Design", 4),
    new Course("Big Data", "Big Data Processing", 4),
    new Course("Cloud Computing", "Cloud Infrastructure and Services", 4)
};



    for (Course elective : electiveCourses) {
        courseCatalog.newCourse(elective.getName(), elective.getCOurseNumber(), elective.getCredits());
    }

    Random random = new Random();
    for (Course electiveCourse : electiveCourses) {
    // Assign a random professor to teach the course
    String[] professors = {"Prof. John", "Prof. Gold", "Prof. Wills", "Prof. Brown", "Prof. Manuel", "Prof. Kal", "Prof. Smith", "Prof. Rohit", "Prof. Sameul", "Prof. Ellie"};
    int randomProfessorIndex = random.nextInt(professors.length);
    FacultyProfile facultyProfile = new FacultyProfile(new Person(professors[randomProfessorIndex]));


    CourseOffer courseOffer = courseSchedule.newCourseOffer(electiveCourse.getCOurseNumber());
    courseOffer.generatSeats(20);
    courseOffer.AssignAsTeacher(facultyProfile);
}

    for (int i = 1; i <= 10; i++) {
        String studentId = String.format("%03d", i); 
        Person studentPerson = new Person("Student" + i);
        StudentProfile studentProfile = new StudentProfile(studentPerson);
        studentProfile.setStudentId(studentId); 

        for (int j = 0; j < 5; j++) { 
            int randomCourseIndex = random.nextInt(electiveCourses.length);
            Course electiveCourse = electiveCourses[randomCourseIndex];
            CourseOffer courseOffer = courseSchedule.getCourseOfferByNumber(electiveCourse.getCOurseNumber());
            if (courseOffer != null) {
                SeatAssignment seatAssignment = courseOffer.assignEmptySeat(studentProfile.newCourseLoad("Fall2024"));
                if (seatAssignment != null) {
                    seatAssignment.setStudentId(studentId);
                }
            }
        }
    }


    Arrays.sort(electiveCourses, Comparator.comparing(Course::getName));

    // Print the department report
    System.out.println("Department: " + department.getName());
    System.out.println("Courses:");
    for (Course electiveCourse : electiveCourses) {
        CourseOffer courseOffer = courseSchedule.getCourseOfferByNumber(electiveCourse.getCOurseNumber());
        if (courseOffer != null) {
            System.out.println("Course: Elective subject: " + electiveCourse.getName() + " , Core Subject: " + electiveCourse.getCOurseNumber());
            FacultyProfile facultyProfile = courseOffer.getFacultyProfile();
            System.out.println("FacultyProfile: " + facultyProfile); 
                if (facultyProfile != null) {
                Person person = facultyProfile.getPerson();
                if (person != null) {
                    System.out.println("Professor: " + person.getName());
                } else {
                    System.out.println("Professor's Person object is null.");
                }
            } else {
                System.out.println("FacultyProfile is null.");
            }
            System.out.println("Enrolled Students:");
            ArrayList<SeatAssignment> seatAssignments = courseOffer.getSeatAssignments();
            if (seatAssignments != null && !seatAssignments.isEmpty()) {
                for (SeatAssignment seatAssignment : seatAssignments) {
                    System.out.println("- " + seatAssignment.getStudentId());
                }
            } else {
                System.out.println("No students enrolled yet.");
            }
            System.out.println();
        }
    }
       
    }
    }


      

 


    
      

 



