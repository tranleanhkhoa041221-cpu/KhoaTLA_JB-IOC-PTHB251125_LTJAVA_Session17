package BaiTapLession17.BaiTapXuatSac2;

import java.util.Scanner;

public class MainOfStudent {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            StudentManager manager = new StudentManager();
            while (true) {
                System.out.println("\n1. Thêm sinh viên");
                System.out.println("2. Thêm khóa học");
                System.out.println("3. Ghi danh sinh viên");
                System.out.println("4. Hiển thị sinh viên và điểm");
                System.out.println("5. Cập nhật điểm");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Tên: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        manager.addStudent(new Student(0, name, email));
                        break;
                    case 2:
                        System.out.print("Tiêu đề: ");
                        String title = sc.nextLine();
                        System.out.print("Số tín chỉ: ");
                        int credits = sc.nextInt();
                        sc.nextLine();
                        manager.addCourse(new Course(0, title, credits));
                        break;
                    case 3:
                        System.out.print("ID sinh viên: ");
                        int sid = sc.nextInt();
                        System.out.print("ID khóa học: ");
                        int cid = sc.nextInt();
                        manager.enrollStudent(sid, cid);
                        break;
                    case 4:
                        manager.listStudentsAndGrades();
                        break;
                    case 5:
                        System.out.print("ID sinh viên: ");
                        int sid2 = sc.nextInt();
                        System.out.print("ID khóa học: ");
                        int cid2 = sc.nextInt();
                        System.out.print("Điểm: ");
                        double grade = sc.nextDouble();
                        manager.updateStudentGrade(sid2, cid2, grade);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

