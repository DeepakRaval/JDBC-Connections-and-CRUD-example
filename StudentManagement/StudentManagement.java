package jdbc.example.StudentManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManagement {

	static String url = "jdbc:mysql://localhost:3306/studentinfo";
	static String userName = "root";
	static String password = "1996";
	static String driverClassPath = "com.mysql.jdbc.Driver";

	public static Connection getDataBaseConnetion() {
		Connection con = null;
		try {
			Class.forName(driverClassPath);
			con = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
	
	// Method to save students information.... 
	public static void saveStudent(StudentInfo stdf) {

		int affetctedRow = 0;

		Connection con = getDataBaseConnetion();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("insert into students (id,firstname,lastname,marks) values(?,?,?,?)");
			stmt.setInt(1, stdf.getId());
			stmt.setString(2, stdf.getFirstName());
			stmt.setString(3, stdf.getLastName());
			stmt.setInt(4, stdf.getMarks());
			affetctedRow = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(affetctedRow == 0){
			System.out.println("Student information can not be saved ... !!! : affected Rows are = "+affetctedRow);
		}else{
			
			System.out.println("Student information saved succesfully : affected Rows are = "+affetctedRow);
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Method to update Student information...
	public static int updateStudent(int id,String fname) {

		int affetctedRow = 0;

		Connection con = getDataBaseConnetion();
		PreparedStatement stmt;
		try {
			String query = "UPDATE students SET firstname = ? WHERE id = ?";
			stmt = con.prepareStatement(query);
			stmt.setInt(2, id);
			stmt.setString(1, fname);
			affetctedRow = stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(affetctedRow == 0){
			System.out.println("Student information can not be Update ... !!! : affected Rows are = "+affetctedRow);
		}else{
			
			System.out.println("Student information updated succesfully : affected Rows are = "+affetctedRow);
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return affetctedRow;
	}
	
	
	// Method to Delete student information... 
	public static void deleteStudent(int id) {

		int affetctedRow = 0;

		Connection con = getDataBaseConnetion();
		PreparedStatement stmt;
		try {
			String query = "delete from students where id = ?";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			affetctedRow = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(affetctedRow == 0){
			System.out.println("Student information can not be Deleted ... !!! : affected Rows are = "+affetctedRow);
		}else{
			
			System.out.println("Student information Deleted succesfully : affected Rows are = "+affetctedRow);
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	method to get information of student ...
	public static void getStudent(int id) {


		Connection con = getDataBaseConnetion();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "select * from students where id ="+id;
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Student with Id : "+id+"is =>");
			while(rs.next()){
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	method to get information of student ...
	public static void getTopperStudent() {


		Connection con = getDataBaseConnetion();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "select max(marks) as marks from students";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("maximun marks is =>");
		
			while(rs.next()){
				System.out.println(rs.getInt("marks"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
