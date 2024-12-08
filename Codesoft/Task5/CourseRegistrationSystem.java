package Task5;

import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String courseCode, title, description, schedule;
    int capacity;

    public Course(String courseCode, String title, String description, String schedule, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.schedule = schedule;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + ", Title: " + title + ", Capacity: " + capacity +
                "\nDescription: " + description + "\nSchedule: " + schedule;
    }
}

class Student {
    String id, name;
    ArrayList<Course> registeredCourses = new ArrayList<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void registerCourse(Course course) {
        if (course.capacity > 0) {
            registeredCourses.add(course);
            course.capacity--;
            System.out.println("Successfully registered for " + course.title);
        } else {
            System.out.println("Course is full!");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.capacity++;
            System.out.println("Successfully dropped " + course.title);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void viewRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course.title);
            }
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of CS", "Mon-Wed-Fri", 3));
        courses.add(new Course("MATH101", "Calculus I", "Differential and Integral Calculus", "Tue-Thu", 2));
        courses.add(new Course("PHY101", "Physics I", "Mechanics and Waves", "Mon-Wed", 5));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        Student student = new Student(id, name);

        int choice;
        do {
            System.out.println("\n1. View Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        System.out.println((i + 1) + ". " + courses.get(i));
                    }
                    break;
                case 2:
                    System.out.print("Enter course number to register: ");
                    int registerChoice = scanner.nextInt() - 1;
                    if (registerChoice >= 0 && registerChoice < courses.size()) {
                        student.registerCourse(courses.get(registerChoice));
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                    break;
                case 3:
                    System.out.print("Enter course number to drop: ");
                    int dropChoice = scanner.nextInt() - 1;
                    if (dropChoice >= 0 && dropChoice < courses.size()) {
                        student.dropCourse(courses.get(dropChoice));
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                    break;
                case 4:
                    student.viewRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting the system. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}

