package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class StudentManagementSystem {
   private static final String FILE_NAME = "students.txt";
   private static ArrayList<Student> students = new ArrayList<>();


    public static void addStudent(Scanner sc) {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Grade: ");
        String grade = sc.nextLine();

        students.add(new Student(id, name, age, grade));
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
            }
        }

    private static void deleteStudent(Scanner sc) {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                System.out.println("Student record deleted successfully!");
            }
            else{
                System.out.println("Student with ID " + id + " not found.");
            }
        }

    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println(student);
                return;
            }else{
                System.out.println("Student with ID " + id + " not found.");
            }
        }

    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.print("Enter new Name: ");
                student.setName(scanner.nextLine());

                System.out.print("Enter new Age: ");
                student.setAge(scanner.nextInt());
                scanner.nextLine();

                System.out.print("Enter new Grade: ");
                student.setGrade(scanner.nextLine());

                System.out.println("Student details updated successfully!");
            }else{
            System.out.println("Student with ID " + id + " not found.");
            }
        }
    }


    private static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Student> loadedStudents = (List<Student>) ois.readObject();
            students.addAll(loadedStudents);
        }
        catch (FileNotFoundException e) {
                System.out.println("No such file exists.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
               loadFromFile();
               Scanner sc = new Scanner(System.in);
               int n;

                do {
                    System.out.println("--- Student Management System ---");
                    System.out.println("1. Add Student");
                    System.out.println("2. View All Students");
                    System.out.println("3. Search Student by ID");
                    System.out.println("4. Update Student Details");
                    System.out.println("5. Delete Student Record");
                    System.out.println("6. Exit");
                    System.out.print("Enter your choice: ");
                    n = sc.nextInt();
                    sc.nextLine();
                    switch (n) {
                        case 1 -> addStudent(sc);
                        case 2 -> viewStudents();
                        case 3 -> searchStudent(sc);
                        case 4 -> updateStudent(sc);
                        case 5 -> deleteStudent(sc);
                        case 6 -> {
                            saveToFile();
                            System.out.println("Exiting..Data is saved to file");
                        }
                        default -> System.out.println("Invalid Choice");
                    }
                }while(n != 6);


        }


}
