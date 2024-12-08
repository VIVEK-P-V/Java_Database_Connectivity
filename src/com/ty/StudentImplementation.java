package com.ty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentImplementation implements StudentInterface {

	@Override
	public void addStudent() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Student id: ");
		int id = sc.nextInt();

		System.out.println("Enter Name: ");
		String name = sc.next();

		System.out.println("Enter Age: ");
		int age = sc.nextInt();

		System.out.println("Enter Gender: ");
		String gender = sc.next();

		System.out.println("Enter Phone Number: ");
		long phno = sc.nextLong();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root",
					"root");
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student values(?,?,?,?,?)");

			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, age);
			preparedStatement.setString(4, gender);
			preparedStatement.setLong(5, phno);

			preparedStatement.execute();
			connection.close();

			System.out.println("Record Inserted Successfully");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Student id to update: ");
		int id = sc.nextInt();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root",
					"root");

			PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
			preparedStatement1.setInt(1, id);
			ResultSet resultSet = preparedStatement1.executeQuery();

			if (resultSet.next()) {
				System.out.println("The Details of Student Id: " + id);
				System.out.println("Name: " + resultSet.getString("name"));
				System.out.println("Age: " + resultSet.getInt("age"));
				System.out.println("Gender: " + resultSet.getString("gender"));
				System.out.println("Phone Number: " + resultSet.getLong("phno"));

				System.out.println("-------------------------");
				System.out.println("Update The Record");
				System.out.println("-------------------------");
				System.out.println("1. Update Id");
				System.out.println("2. Update Name");
				System.out.println("3. Update Age");
				System.out.println("4. Update Gender");
				System.out.println("5. Update Phone Number");
				System.out.println("Enter Your Choice: ");
				int choice = sc.nextInt();

				PreparedStatement preparedStatement2 = null;
				switch (choice) {
				case 1:
					System.out.println("Enter new Id: ");
					int newId = sc.nextInt();
					preparedStatement2 = connection.prepareStatement("UPDATE student SET id = ? WHERE id = ?");
					preparedStatement2.setInt(1, newId);
					preparedStatement2.setInt(2, id);
					System.out.println("Id Updated");
					break;
				case 2:
					System.out.println("Enter new Name: ");
					String name = sc.next();
					preparedStatement2 = connection.prepareStatement("UPDATE student SET name = ? WHERE id = ?");
					preparedStatement2.setString(1, name);
					preparedStatement2.setInt(2, id);
					System.out.println("Name Updated");
					break;
				case 3:
					System.out.println("Enter new Age: ");
					int age = sc.nextInt();
					preparedStatement2 = connection.prepareStatement("UPDATE student SET age = ? WHERE id = ?");
					preparedStatement2.setInt(1, age);
					preparedStatement2.setInt(2, id);
					System.out.println("Age Updated");
					break;
				case 4:
					System.out.println("Enter new Gender: ");
					String gender = sc.next();
					preparedStatement2 = connection.prepareStatement("UPDATE student SET gender = ? WHERE id = ?");
					preparedStatement2.setString(1, gender);
					preparedStatement2.setInt(2, id);
					System.out.println("Gender Updated");
					break;
				case 5:
					System.out.println("Enter new Phone Number: ");
					long phno = sc.nextLong();
					preparedStatement2 = connection.prepareStatement("UPDATE student SET phno = ? WHERE id = ?");
					preparedStatement2.setLong(1, phno);
					preparedStatement2.setInt(2, id);
					System.out.println("Phone Number Updated");
					break;
				default:
					System.out.println("Invalid choice");
					break;
				}

				if (preparedStatement2 != null) {
					preparedStatement2.execute();
				}
			} else {
				System.out.println("Student Id not found.");
			}

			preparedStatement1.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteStudent() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the student Id: ");
		int id = sc.nextInt();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root",
					"root");

			PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
			preparedStatement1.setInt(1, id);
			ResultSet resultSet = preparedStatement1.executeQuery();

			if (resultSet.next()) {
				System.out.println("The Details of Student Id: " + id);
				System.out.println("Name: " + resultSet.getString("name"));
				System.out.println("Age: " + resultSet.getInt("age"));
				System.out.println("Gender: " + resultSet.getString("gender"));
				System.out.println("Phone Number: " + resultSet.getLong("phno"));
				System.out.println("\n");

				System.out.println("Type 'confirm' to delete: ");
				String confirm = sc.next().toLowerCase();
				if (confirm.equals("confirm")) {
					PreparedStatement preparedStatement = connection
							.prepareStatement("DELETE FROM student WHERE id = ?");
					preparedStatement.setInt(1, id);
					preparedStatement.execute();
					System.out.println("Record Deleted Successfully");
				} else {
					System.out.println("Deletion cancelled\n");
				}
			} else {
				System.out.println("Student Id not found.\n");
			}

			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getAllStudent() {
		{
			try {
				// Load MySQL JDBC driver
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Establish connection to the database
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root",
						"root");

				// Prepare SQL select statement
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student");
				ResultSet resultSet = preparedStatement.executeQuery();

				// Check if there are any results
				if (!resultSet.isBeforeFirst()) {
					System.out.println("No Records Found!\n");
				} else {
					// Display student details
					while (resultSet.next()) {
						System.out.println("The Id is: " + resultSet.getInt(1));
						System.out.println("The Name is: " + resultSet.getString(2));
						System.out.println("The Age is: " + resultSet.getInt(3));
						System.out.println("The Gender is: " + resultSet.getString(4));
						System.out.println("The Phone number is: " + resultSet.getLong(5));
						System.out.println("");
						System.out.println("---------------------------------");
						System.out.println("");
					}
				}

				// Close the connection
				connection.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteAllStudent() {
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root",
					"root");

			java.sql.Statement statement = connection.createStatement();
			System.out.println("Type 'confirm' to delete: ");

			String confirm = sc.next().toLowerCase();
			if (confirm.equals("confirm")) {
				statement.execute("TRUNCATE TABLE student");
				System.out.println("All Record Deleted Successfully");
			} else {
				System.out.println("Deletion cancelled");
			}

			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getStudentById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the student Id: ");
		int id = sc.nextInt();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root",
					"root");

			PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
			preparedStatement1.setInt(1, id);
			ResultSet resultSet = preparedStatement1.executeQuery();

			if (resultSet.next()) {
				System.out.println("The Details of Student Id: " + id);
				System.out.println("Name: " + resultSet.getString("name"));
				System.out.println("Age: " + resultSet.getInt("age"));
				System.out.println("Gender: " + resultSet.getString("gender"));
				System.out.println("Phone Number: " + resultSet.getLong("phno"));
			} else {
				System.out.println("Student not exist");
			}

			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
