package BaiTapLession17.BaiTapGioi2;


import BaiTapLession17.Until.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderManager {
        public void addProduct(Product product) {
            try (Connection con = ConnectionDB.getConnection();
                 PreparedStatement check = con.prepareStatement("SELECT 1 FROM product WHERE name = ? LIMIT 1");
                 PreparedStatement insert = con.prepareStatement("INSERT INTO product (name, price) VALUES (?, ?)")) {

                check.setString(1, product.getName());
                ResultSet rs = check.executeQuery();
                if (rs.next()) {
                    System.out.println("Tên sản phẩm đã tồn tại.");
                    return;
                }

                insert.setString(1, product.getName());
                insert.setDouble(2, product.getPrice());
                insert.executeUpdate();
                System.out.println("Thêm sản phẩm thành công.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void updateCustomer(int customerId, Customer customer) {
            try (Connection con = ConnectionDB.getConnection();
                 PreparedStatement check = con.prepareStatement("SELECT 1 FROM customer WHERE id = ?");
                 PreparedStatement update = con.prepareStatement("UPDATE customer SET name = ?, email = ? WHERE id = ?")) {

                check.setInt(1, customerId);
                ResultSet rs = check.executeQuery();
                if (!rs.next()) {
                    System.out.println("Không tìm thấy khách hàng.");
                    return;
                }

                update.setString(1, customer.getName());
                update.setString(2, customer.getEmail());
                update.setInt(3, customerId);
                update.executeUpdate();
                System.out.println("Cập nhật khách hàng thành công.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void createOrder(int customerId, Map<Integer, Integer> productQuantities) {
            try (Connection con = ConnectionDB.getConnection();
                    PreparedStatement stmt = con.prepareStatement("SELECT price FROM product WHERE id = ?");) {
                double total = 0;
                for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
                    stmt.setInt(1, entry.getKey());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        total += rs.getDouble("price") * entry.getValue();
                    }
                }

                PreparedStatement insert = con.prepareStatement("INSERT INTO orders (customer_id, order_date, total_amount) VALUES (?, CURRENT_DATE, ?)");
                insert.setInt(1, customerId);
                insert.setDouble(2, total);
                insert.executeUpdate();
                System.out.println("Tạo đơn hàng thành công. Tổng tiền: " + total);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void listAllOrders() {
            try (Connection con = ConnectionDB.getConnection();
                 PreparedStatement stmt = con.prepareStatement(
                         "SELECT o.id, c.name, o.order_date, o.total_amount FROM orders o JOIN customer c ON o.customer_id = c.id")) {

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.printf("ID: %d | KH: %s | Ngày: %s | Tổng: %.2f\n",
                            rs.getInt("id"), rs.getString("name"), rs.getDate("order_date"), rs.getDouble("total_amount"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void getOrdersByCustomer(int customerId) {
            try (Connection con = ConnectionDB.getConnection();
                 PreparedStatement stmt = con.prepareStatement("SELECT id, order_date, total_amount FROM orders WHERE customer_id = ?")) {

                stmt.setInt(1, customerId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.printf("ID: %d | Ngày: %s | Tổng: %.2f\n",
                            rs.getInt("id"), rs.getDate("order_date"), rs.getDouble("total_amount"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


