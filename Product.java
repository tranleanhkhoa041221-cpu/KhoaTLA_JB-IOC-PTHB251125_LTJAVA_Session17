package BaiTapLession17.BaiTapGioi2;

public class Product {
    private int id;
    private String name;
    private double price;

    public Product() {}

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Tên không được để trống");
        this.name = name;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price <= 0)
            throw new IllegalArgumentException("Giá phải > 0");
        this.price = price;
    }
}