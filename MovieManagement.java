package BaiTapLession17;

import BaiTapLession17.Until.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieManagement {
    public void addMovie(String title, String director, int year) {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pre = con.prepareStatement("INSERT INTO movies (title,director,year) VALUES (?,?,?)");
        ) {
            pre.setString(1, title);
            pre.setString(2, director);
            pre.setInt(3, year);
            pre.executeUpdate();
            System.out.println("Phim đã được thêm thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listMovies() {
        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pre = con.prepareStatement("SELECT * FROM movies");
        ){
             ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                System.out.printf("ID: %d | Tiêu đề: %s | Đạo diễn: %s | Năm: %d%n", rs.getInt("id"), rs.getString("title"), rs.getString("director"), rs.getInt("year"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateMovie(int id, String title, String director, int year){
        try(Connection con = ConnectionDB.getConnection();
            PreparedStatement pre = con.prepareStatement("UPDATE movies SET title = ? , director = ? , year = ? WHERE id = ?")
        ) {
            pre.setString(1, title);
            pre.setString(2, director);
            pre.setInt(3, year);
            pre.setInt(4, id);
            int row = pre.executeUpdate();
            if(row>0){
                System.out.println("Phim đã được cập nhật.");
            } else {
                System.out.println("Không tìm thấy phim với ID đã nhập.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deleteMovie(int id){
        try(Connection con = ConnectionDB.getConnection();
            PreparedStatement pre = con.prepareStatement("DELETE FROM movies WHERE id = ?")
        ){
            pre.setInt(1, id);
            int row = pre.executeUpdate();
            if(row>0){
                System.out.println("Phim đã được xóa.");
            } else {
                System.out.println("Không tìm thấy phim với ID đã nhập.");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
