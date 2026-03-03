package BaiTapLession17.BaiTapGioi2;

public class Customer {
    private int id;
    private String name;
    private String email;

    public Customer() {}

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Tên không được để trống");
        this.name = name;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email không được để trống");
        this.email = email;
    }
}