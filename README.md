BLOOD BANK MANAGEMENT SYSTEM
TEAM MEMEBERS
ANGEL MARIYA LAL-24UBC111
MEKHA JAIMON-24UBC142

PROBLEM STATEMENT

Managing blood donor records manually is time-consuming, error-prone, and inefficient. There is a need for a digital system to store, update, and manage donor information easily.

OBJECTIVE

The objective of this mini-project is to develop a Java Swing-based Blood Bank Management System that allows users to:

Store donor details

Update donor information

Delete donor records

View donor data in tabular form

Connect Java application with MySQL using JDBC

FEATURES
GUI-based desktop application

Add donor details

Update existing donor records

Delete donor records

Display donor details using JTable

MySQL database connectivity

Uses PreparedStatement for secure data insertion

User-friendly interface

TECHNOLOGIES USED
Category	Technology
Programming Language	Java
GUI Framework	Java Swing
Database	MySQL
Connectivity	JDBC
IDE	Eclipse / IntelliJ / NetBeans
Database Tool	MySQL Workbench
STEPS TO THE PROGRAM
1️ Clone the Repository
git clone https://github.com/your-username/Blood-Bank-Management-System.git
Open Project

Open the project in Eclipse / IntelliJ / NetBeans

3️ Configure Database

Create database and table in MySQL:

CREATE DATABASE bloodbank;
USE bloodbank;

CREATE TABLE donors (
    name VARCHAR(50),
    age INT,
    blood_group VARCHAR(5),
    phone VARCHAR(15),
    city VARCHAR(50),
    units INT
);
4️ Update DBConnection.java

Modify database credentials:

String url = "jdbc:mysql://localhost:3306/bloodbank";
String user = "root";
String password = "your_password";
5️ Run the Application

Run BloodBankApp.java

Sample Input & Output
Sample Input:
Name: Anu
Age: 22
Blood Group: A+
Phone: 9876543210
City: Kochi
Units: 2
Sample Output:
Donor Added Successfully!


