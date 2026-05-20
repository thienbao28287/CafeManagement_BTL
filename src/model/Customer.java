package model;

public class Customer {

    private String customerID;
    private String customerName;
    private String phoneNumber;
    private String address;

    public Customer() {

    }

    public Customer(
            String customerID,
            String customerName,
            String phoneNumber,
            String address
    ) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {

        return "Customer [customerID="
                + customerID
                + ", customerName="
                + customerName
                + ", phoneNumber="
                + phoneNumber
                + ", address="
                + address
                + "]";
    }
}