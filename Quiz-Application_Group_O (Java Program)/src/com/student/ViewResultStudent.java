package com.student;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.connection.ConnectionTest;
import com.dashboard.Dashboard;

import CustomExceptions.InvalidInputException;

public class ViewResultStudent {
	ViewResultStudent() {
	}

	static Connection connection() throws SQLException {
		Connection con = ConnectionTest.getConnection();
		return con;
	}

	public void showStudentResult(int stuId) {//1
		// used to show personal quiz result in User Login
		try {
			PreparedStatement ps = connection().prepareStatement(
					"select student.std_id,marks_obtained from student inner join result on result.std_id=student.std_id where student.std_id=?");
			ps.setInt(1, stuId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ab = rs.getInt(1);
				System.out.println("ID>> " + ab);
				String str = rs.getString(2);
				System.out.println("Your Score Is>> " + str);
				// ID>>1
				// Your Score Is>>6
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int cond = 1;
		Scanner sc = new Scanner(System.in);
		while (cond == 1) {
			System.out.println("For Home - Enter 1");
			System.out.println("Logout - Enter 2");
			System.out.println("Quit - Enter 3");
			try {
				int inp = sc.nextInt();
				if (inp == 1) {
					cond = 0;
					new Student().studentDashboard();
				} else if (inp == 2) {
					cond = 0;
					Dashboard.mainDashboard();
				} else if (inp == 3) {
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
		}
		sc.close();
	}

}
