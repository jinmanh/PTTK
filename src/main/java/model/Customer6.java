package model;

public class Customer6 extends Account6 {
    private String customerCode;
    private String customerType;

    public Customer6() {
        super();
        setRole("CUSTOMER");
    }

    public Customer6(int id, String username, String password, String fullName, String email, String phone, String customerCode, String customerType) {
        super(id, username, password, fullName, null, null, email, phone, "CUSTOMER");
        this.customerCode = customerCode;
        this.customerType = customerType;
    }

    public String getCustomerCode() { return customerCode; }
    public void setCustomerCode(String customerCode) { this.customerCode = customerCode; }
    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }
}
