package entity;

public class CustomerDetails {
    private int customerId;
    private String phone;
    private String address;
    private String sex;

    public CustomerDetails(int customerId, String phone, String address, String sex) {
        this.customerId = customerId;
        this.phone = phone;
        this.address = address;
        this.sex = sex;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getSex() {
        return sex;
    }
}