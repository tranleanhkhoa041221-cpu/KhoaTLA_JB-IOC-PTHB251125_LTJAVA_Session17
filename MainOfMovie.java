package BaiTapLession17;

import java.util.Scanner;

public class MainOfMovie {
    public static void main(String[] args) {
        MovieManagement mm = new MovieManagement();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Thêm phim");
            System.out.println("2. Liệt kê phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Xóa phim");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Tiêu đề: ");
                        String title = sc.nextLine();
                        System.out.print("Đạo diễn: ");
                        String director = sc.nextLine();
                        System.out.print("Năm phát hành: ");
                        int year = Integer.parseInt(sc.nextLine());
                        mm.addMovie(title, director, year);
                        break;

                    case 2:
                        mm.listMovies();
                        break;

                    case 3:
                        System.out.print("ID phim cần sửa: ");
                        int idUpdate = Integer.parseInt(sc.nextLine());
                        System.out.print("Tiêu đề mới: ");
                        String newTitle = sc.nextLine();
                        System.out.print("Đạo diễn mới: ");
                        String newDirector = sc.nextLine();
                        System.out.print("Năm phát hành mới: ");
                        int newYear = Integer.parseInt(sc.nextLine());
                        mm.updateMovie(idUpdate, newTitle, newDirector, newYear);
                        break;

                    case 4:
                        System.out.print("ID phim cần xóa: ");
                        int idDelete = Integer.parseInt(sc.nextLine());
                        mm.deleteMovie(idDelete);
                        break;

                    case 0:
                        System.out.println("Thoát chương trình.");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }

            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Nhập sai kiểu dữ liệu.");
                choice = -1;
            }

        } while (choice != 0);

        sc.close();
    }
}