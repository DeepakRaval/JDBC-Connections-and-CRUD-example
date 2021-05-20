package jdbc.example.StudentManagement;

public class SaveStudent {
	
	public static void main(String[] args) {
		StudentInfo si = new StudentInfo(3, "CCC", "AUC", 257);
		
	    StudentManagement.saveStudent(si);
	    StudentManagement.updateStudent(7, "DEE");
	    StudentManagement.deleteStudent(4);
		StudentManagement.getStudent(3);
		StudentManagement.getTopperStudent();
	}

}
