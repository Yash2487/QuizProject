package com.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.connection.ConnectionTest;
import com.dashboard.Dashboard;

import CustomExceptions.InvalidInputException;

public class StudentFunctions extends StudentDetails {
	Scanner sc = new Scanner(System.in);
	static int qCount = 0;//10
	static int caCount = 0;//6

	static Connection connection() throws SQLException {
		Connection con = ConnectionTest.getConnection();
		return con;

	}

	StudentDetails studentDetails = new StudentDetails();

	public void startQuiz() {
		try {
			// "select * from question order by rand() limit 10"
			PreparedStatement ps = connection().prepareStatement("select * from question");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				qCount++;// 1,2,3,4,5,6,7,8,9,10
				System.out.println(" Question No " + qCount);
				System.out.println(" Que :" + rs.getString(2));
				System.out.println(" A :" + rs.getString(3));
				System.out.println(" B :" + rs.getString(4));
				System.out.println(" C :" + rs.getString(5));
				System.out.println(" D :" + rs.getString(6));
				String ans = sc.next();//a
				if (ans.equalsIgnoreCase(rs.getString(7))) {// a.equals(a)-true
					caCount++;//1
				}
			}
			System.out.println("Quiz Done Thank You..");
			//new Student().studentDashboard();
			System.out.println("Enter 4 to Store Quiz Result");
			int uInput=sc.nextInt();
			if (uInput==4) {
				storeQuizResult();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public void storeQuizResult() {
		try {
			PreparedStatement pst = connection().prepareStatement(
					// "insert into result (total_question,correct_answers,marks_obtained,
					// remark,std_id) values(?,?,?,?,?)");
					"insert into result (std_id,total_question,correct_answer,marks_obtained) values(?,?,?,?)");
			pst.setInt(1, studentDetails.getStd_id());
			pst.setInt(2, qCount);
			pst.setInt(3, caCount);
			pst.setInt(4, caCount);
			// pst.setString(4, rem);
			//pst.setInt(4, studentDetails.getStd_id());

			pst.executeUpdate();
			System.out.println("Result Store, Thank You");
			new Student().studentDashboard();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void studentDetails() {

		System.out.println("Id: " + getStd_id());
		System.out.println("First Name: " + getFirstName());
		System.out.println("Last Name: " + getLastName());
		System.out.println("City: " + getCity());
		System.out.println("Email : " + getEmail());
		System.out.println("Mobile: " + getMobileNo());

		int cond = 1;
		while (cond == 1) {
			try {
				System.out.println("Home - Enter 1");
				System.out.println("Logout - Enter 2");
				int userInput = sc.nextInt();
				if (userInput == 1) {
					cond = 0;
					new Student().studentDashboard();
				} else if (userInput == 2) {
					cond = 0;
					Dashboard.mainDashboard();
				} else {
					cond = 1;
					throw new InvalidInputException("Invalid Input, Try Again");
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println(e);
			}
		}
	}
}
