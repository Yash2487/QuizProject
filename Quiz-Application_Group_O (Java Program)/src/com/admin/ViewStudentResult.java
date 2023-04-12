package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.connection.ConnectionTest;
import com.dashboard.Dashboard;

import CustomExceptions.InvalidInputException;

public class ViewStudentResult {
	ViewStudentResult() {
	}

	static Connection connection() throws SQLException {
		Connection con = ConnectionTest.getConnection();
		return con;
	}

	public void showStudentResult() {
		// this is used to show marks of all student by admin
		try {
			PreparedStatement ps = connection().prepareStatement(
					"select student.first_name,last_name,marks_obtained from student inner join result on result.std_id=student.std_id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String str = rs.getString(1);
				System.out.println("First Name>> " + str);
				String str1 = rs.getString(2);
				System.out.println("Last Name>> " + str1);
				String str2 = rs.getString(3);
				System.out.println("Marks>> " + str2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int cond = 1;

		Scanner sc = new Scanner(System.in);
		while (cond == 1) {
			System.out.println("To view result of student by ID - Enter 1");
			System.out.println("For Home - Enter 2");
			System.out.println("Logout - Enter 3");
			System.out.println("Quit - Enter 4");
			try {
				int inp = sc.nextInt();
				if (inp == 1) {
					cond = 0;
					System.out.println("Enter Student Id");
					try {
						int sid = sc.nextInt();
						showStudentResult(sid);
					} catch (Exception e) {
						System.out.println(e);
					}
				} else if (inp == 2) {
					cond = 0;
					new AdminLogin().adminDashboard();
				} else if (inp == 3) {
					cond = 0;
					Dashboard.mainDashboard();
				} else if (inp == 4) {
					cond = 0;
					System.out.println("Thank You, Bye Bye");
					System.exit(0);
				} else {
					throw new InvalidInputException("Invalid Input, Try Again");
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println(e);
			}
			sc.close();
		}
	}

	public void showStudentResult(int stuId) {
		// this is used to show mark of student by ID in Admin User
		try {
			PreparedStatement ps = connection().prepareStatement(
					"select student.std_id,marks_obtained from student inner join result on result.std_id=student.std_id where student.std_id=?");
			ps.setInt(1, stuId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ab = rs.getInt(1);
				System.out.println("ID>> " + ab);
				String str = rs.getString(2);
				System.out.println("Marks>> " + str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int cond = 1;

		Scanner sc = new Scanner(System.in);
		while (cond == 1) {
			System.out.println("For Another Student - Enter 1");
			System.out.println("For Home - Enter 2");
			System.out.println("Logout - Enter 3");
			System.out.println("Quit - Enter 4");
			try {
				int inp = sc.nextInt();
				if (inp == 1) {
					cond = 0;
					System.out.println("Enter Student Id");
					try {
						int sid = sc.nextInt();
						showStudentResult(sid);
					} catch (Exception e) {
						System.out.println(e);
					}
				} else if (inp == 2) {
					cond = 0;
					new AdminLogin().adminDashboard();
				} else if (inp == 3) {
					cond = 0;
					Dashboard.mainDashboard();
				} else if (inp == 4) {
					cond = 0;
					System.out.println("Thank You, Bye Bye");
					System.exit(0);
				} else {
					throw new InvalidInputException("Invalid Input, Try Again");
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println(e);
			}
			sc.close();
		}
	}
}
