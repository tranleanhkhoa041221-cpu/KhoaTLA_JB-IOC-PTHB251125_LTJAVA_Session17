package BaiTapLession17.BaiTapGioi2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            OrderManager manager = new OrderManager();

            while (true) {

                System.out.println("\n===== ORDER MANAGEMENT =====");
                System.out.println("1. Thêm sản phẩm mới");
                System.out.println("2. Cập nhật khách hàng");
                System.out.println("3. Tạo đơn hàng mới");
                System.out.println("4. Hiển thị danh sách đơn hàng");
                System.out.println("5. Tìm đơn hàng theo khách hàng");
                System.out.println("0. Thoát");
                System.out.print("Chọn: ");

                int choice;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ!");
                    continue;
                }

                switch (choice) {

                    case 1:
                        try {
                            System.out.print("Tên sản phẩm: ");
                            String name = sc.nextLine();

                            System.out.print("Giá: ");
                            double price = Double.parseDouble(sc.nextLine());

                            manager.addProduct(new Product(name, price));

                        } catch (NumberFormatException e) {
                            System.out.println("Giá không hợp lệ!");
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("ID khách hàng: ");
                            int id = Integer.parseInt(sc.nextLine());

                            System.out.print("Tên mới: ");
                            String newName = sc.nextLine();

                            System.out.print("Email mới: ");
                            String email = sc.nextLine();

                            manager.updateCustomer(id, new Customer(newName, email));

                        } catch (NumberFormatException e) {
                            System.out.println("ID không hợp lệ!");
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("ID khách hàng: ");
                            int customerId = Integer.parseInt(sc.nextLine());

                            Map<Integer, Integer> productMap = new HashMap<>();

                            while (true) {
                                System.out.print("ID sản phẩm (0 để dừng): ");
                                int productId = Integer.parseInt(sc.nextLine());
                                if (productId == 0) break;

                                System.out.print("Số lượng: ");
                                int quantity = Integer.parseInt(sc.nextLine());

                                productMap.put(productId, quantity);
                            }

                            manager.createOrder(customerId, productMap);

                        } catch (NumberFormatException e) {
                            System.out.println("Dữ liệu nhập không hợp lệ!");
                        }
                        break;

                    case 4:
                        manager.listAllOrders();
                        break;

                    case 5:
                        try {
                            System.out.print("ID khách hàng: ");
                            int customerId = Integer.parseInt(sc.nextLine());
                            manager.getOrdersByCustomer(customerId);

                        } catch (NumberFormatException e) {
                            System.out.println("ID không hợp lệ!");
                        }
                        break;

                    case 0:
                        System.out.println("Thoát chương trình...");
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            }
        }
    }