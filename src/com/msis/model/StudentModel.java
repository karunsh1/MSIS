package com.msis.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.msis.DBConnection.MySQLAccess;
import com.msis.DTO.Student;

public class StudentModel {
	protected String tableName = "student";
	
	/**
	 * get a student information and return it
	 * @param email
	 * @return student object with data
	 */
	public Student selectStudent(String email) {
		Student student = null;
		String sql = "select * from "+ tableName +" where email=?";
		
		// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();
		
		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			prepareStm.setString(1, email);
			ResultSet results = prepareStm.executeQuery();
			while(results.next()){
				email = results.getString("email");
				int id = results.getInt("id");
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				String mobile = results.getString("mobile");
				String address = results.getString("address");
	
				student = new Student(id, first_name, last_name, email,mobile, address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	public boolean validateStudent(int studentId){
		boolean returnStudentValidity = false;
		String sql = "select * from "+ tableName +" where id=?";
		
		// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();
		
		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			prepareStm.setInt(1, studentId);
			ResultSet results = prepareStm.executeQuery();
			while(results.next()){
				returnStudentValidity = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnStudentValidity;
	}
}
