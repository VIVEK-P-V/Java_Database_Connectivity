# Java Database Connectivity (JDBC) - Student Management System

This project demonstrates a simple **Student Management System** using **Java Database Connectivity (JDBC)**. It allows you to create, read, update, and delete student records from a MySQL database.

## Features

- **Create**: Add a new student to the database.
- **Read**: Retrieve a student's details by their ID, or view all students.
- **Update**: Modify an existing student's details.
- **Delete**: Remove a student by their ID, or delete all student records at once.
- **Database**: Interacts with a MySQL database for CRUD operations.
  
The application uses basic SQL queries and JDBC to connect to a MySQL database and manage student records.

## Technologies Used

- **Java 8+**
- **MySQL 8.0+**
- **JDBC (Java Database Connectivity)**

## Project Structure

The project follows a simple structure, where the database connection and the CRUD operations are handled through Java classes and interfaces.

### Core Components

1. **StudentInterface**:
   - This is the interface that defines the basic CRUD operations for managing student records.
   
2. **StudentImplementation**:
   - This class implements the `StudentInterface` and contains the actual logic for adding, updating, deleting, and retrieving student records from the MySQL database.
   
3. **Student**:
   - A simple Java class representing the `Student` object, which holds the student's information such as `id`, `name`, `age`, `gender`, and `phone number`.

4. **Database Setup**:
   - The MySQL database named `studentdb` is created, with a `student` table containing columns for `id`, `name`, `age`, `gender`, and `phno`.

### Database Schema

Before running the application, make sure to create the database and the table in MySQL using the following SQL commands:

```sql
CREATE DATABASE studentdb;

USE studentdb;

CREATE TABLE student (
  id INT NOT NULL PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  age INT,
  gender VARCHAR(45),
  phno BIGINT
);
```

## How It Works

### 1. **Connecting to MySQL**:
   The program connects to a MySQL database using the **JDBC Driver**. The `StudentImplementation` class contains all database interactions, using `PreparedStatement` for executing SQL queries.

### 2. **CRUD Operations**:
   - **Add Student**: The program accepts student details and inserts a new record into the database.
   - **Update Student**: The program allows updating any student's details (e.g., name, age, gender, phone number).
   - **Delete Student**: It allows deleting a student record by providing the student's ID.
   - **Get All Students**: This option displays all students currently stored in the database.
   - **Get Student by ID**: This retrieves a student’s details based on their unique `id`.
   - **Delete All Students**: Clears all student records from the database.

### 3. **Interface**:
   The project uses an interface `StudentInterface` to define the operations, which are implemented in the `StudentImplementation` class.

### 4. **Student Class**:
   The `Student` class is a POJO (Plain Old Java Object) that stores the student's information. It is used for reading data from and writing data to the database.

## Methods in `StudentInterface`

```java
public interface StudentInterface {

    boolean addStudent();           // Adds a student to the database
    void updateStudent();           // Updates an existing student
    void deleteStudent();           // Deletes a student from the database
    List<Student> getAllStudent();  // Retrieves all students
    void deleteAllStudent();       // Deletes all students from the database
    Student getStudentById(int id); // Retrieves a student by ID
}
```

### Method Explanation

1. **`addStudent()`**: Adds a new student to the database. Returns `true` if successful.
2. **`updateStudent()`**: Updates an existing student's details (name, age, etc.).
3. **`deleteStudent()`**: Deletes a student from the database based on their unique `id`.
4. **`getAllStudent()`**: Fetches and returns a list of all students in the database.
5. **`deleteAllStudent()`**: Clears all records from the student table.
6. **`getStudentById(int id)`**: Retrieves a student's details by their `id`.

## Example Usage

The main class (`Student.java`) contains a simple menu interface that allows the user to select options from a list of actions (e.g., adding, updating, or deleting students).

### Example of Main Menu Interaction

```text
WELCOME TO STUDENT DATABASE
---------------------------

1. Add Student.
2. Update Student.
3. Delete Student.
4. Get All Students.
5. Get Student by ID.
6. Delete All Students.
7. Exit.

Enter Your Choice: 
```

The program then prompts for the required information depending on the selected action (e.g., for adding a student, it will ask for the student's `id`, `name`, `age`, etc.).

### Example of Adding a Student

```java
Enter Student id: 101
Enter Name: John
Enter Age: 20
Enter Gender: Male
Enter Phone Number: 9876543210
```

After the details are entered, the student record is added to the database.

## Dependencies

Ensure you have the **MySQL JDBC Driver** in your classpath. You can download it from the [MySQL official website](https://dev.mysql.com/downloads/connector/j/), or use it via Maven or Gradle if your project uses build automation tools.

If you're not using Maven or Gradle, make sure the JDBC driver `.jar` is added to your `lib` folder, and included in your classpath.

## Troubleshooting

1. **Database Connection Issues**:
   - Ensure your MySQL database is running on the default `localhost` with port `3306`.
   - Make sure the username (`root`) and password (`root`) are correct for your MySQL instance.

2. **SQL Syntax Errors**:
   - Double-check the SQL commands used to create the database and table. Ensure the table structure matches the one expected by the Java code.

3. **JDBC Driver Not Found**:
   - Ensure the **MySQL JDBC driver** is included in your project’s build path.

4. **Empty Results or No Data**:
   - If no data is displayed, check your database for records. If it’s empty, use the "Add Student" option to insert data.

## Conclusion

This project demonstrates how to use Java's JDBC API to interact with a MySQL database, allowing basic CRUD operations for managing student records. You can further enhance this by adding more features such as validation, custom exception handling, or integrating with a front-end application.

---
