package com.ty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student {

	public static void main(String[] args) {

		// Create a new database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
			Statement statement = connection.createStatement();
			statement.execute("CREATE DATABASE studentdb");
			System.out.println("---Student Database Created.---\n");
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// Create a new table in the database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root",
					"root");
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE student (\r\n" + "  id INT NOT NULL,\r\n"
					+ "  name VARCHAR(45) NOT NULL,\r\n" + "  age INT,\r\n" + "  gender VARCHAR(45),\r\n"
					+ "  phno BIGINT,\r\n" + "  PRIMARY KEY (id)\r\n" + ");");
			System.out.println("---Student Table Created.---");
			System.out.println("-------------------------------------");
			System.out.println("\n\n");
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		StudentInterface si = new StudentImplementation();

		// Main loop for menu options
		while (true) {
			System.out.println("WELCOME TO STUDENT DATABASE");
			System.out.println("---------------------------");
			System.out.println();

			System.out.println("1. Add Student.\n");
			System.out.println("2. Update Student.\n");
			System.out.println("3. Delete Student.\n");
			System.out.println("4. Get All Students.\n");
			System.out.println("5. Get Student.\n");
			System.out.println("6. Delete All Students.\n");
			System.out.println("7. Exit.\n");
			System.out.println("Enter Your Choice: ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				si.addStudent(); // Method to add a student
				break;
			case 2:
				si.updateStudent(); // Method to update a student
				break;
			case 3:
				si.deleteStudent(); // Method to delete a student
				break;
			case 4:
				si.getAllStudent(); // Method to get all students
				break;
			case 5:
				si.getStudentById(); // Method to get a student by ID
				break;
			case 6:
				si.deleteAllStudent(); // Method to delete all students
				break;
			case 7:
				System.out.println("----------See You Again!----------");
				System.exit(0); // Exit the program
				break;
			default:
				System.out.println("Invalid Choice");
			}

		}
	}
}
