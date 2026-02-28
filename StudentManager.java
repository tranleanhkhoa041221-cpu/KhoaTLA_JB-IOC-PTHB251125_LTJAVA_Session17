package BaiTapLession17.BaiTapXuatSac2;

import BaiTapLession17.Until.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManager {
    public void addStudent(Student student) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pre = con.prepareStatement("SELECT * FROM student WHERE email = ?");
             PreparedStatement pre1 = con.prepareStatement("INSERT INTO student (name, email) VALUES (?, ?)")
        ) {
            pre.setString(1, student.getEmail());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                System.out.println("Email đã tồn tại.");
                return;
            }
            pre1.setString(1, student.getName());
            pre1.setString(2, student.getEmail());
            pre1.executeUpdate();
            System.out.println("Thêm sinh viên thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addCourse(Course course) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pre = con.prepareStatement("SELECT * FROM course WHERE title = ?");
             PreparedStatement pre1 = con.prepareStatement("INSERT INTO course (title, credits) VALUES (?, ?)")
        ) {
            pre.setString(1, course.getTitle());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                System.out.println("Title đã tồn tại.");
                return;
            }
            pre1.setString(1, course.getTitle());
            pre1.setInt(2, course.getCredits());
            pre1.executeUpdate();
            System.out.println("Thêm khóa học thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void enrollStudent(int studentId, int courseId) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pre = con.prepareStatement("SELECT * FROM enrollment WHERE student_id = ? AND course_id = ?");
             PreparedStatement pre1 = con.prepareStatement("INSERT INTO enrollment (student_id, course_id) VALUES (?, ?)")
        ) {
            pre.setInt(1, studentId);
            pre.setInt(2, courseId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                System.out.println("Sinh viên đã ghi danh khóa học này.");
                return;
            }
            pre1.setInt(1, studentId);
            pre1.setInt(2, courseId);
            pre1.executeUpdate();
            System.out.println("Ghi danh thành công.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listStudentsAndGrades(){
        try(Connection con = ConnectionDB.getConnection();
            PreparedStatement pre = con.prepareStatement("SELECT s.name, e.grade, c.title FROM student s JOIN enrollment e ON s.id = e.student_id JOIN course c ON c.id = e.course_id ORDER BY s.name")
        ){
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                System.out.printf("%s : %s - %s\n", rs.getString(1), rs.getObject(2),rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateStudentGrade(int studentId, int courseId, double grade) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pre = con.prepareStatement("UPDATE enrollment SET grade = ? WHERE student_id = ? AND course_id = ?")
        ) {
            pre.setDouble(1, grade);
            pre.setInt(2, studentId);
            pre.setInt(3, courseId);
            int row = pre.executeUpdate();
            if (row > 0) {
                System.out.println("Cập nhật điểm thành công.");
            } else {
                System.out.println("Không tìm thấy bản ghi để cập nhật.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
