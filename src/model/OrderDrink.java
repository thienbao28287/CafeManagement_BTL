package model;

public class OrderDrink {
    private int drinkID;
    private String orderID;
    private String drinkName;
    private double price;
    private int quantity;

    public OrderDrink() {
    }

    public OrderDrink(int drinkID, String orderID, String drinkName, double price, int quantity) {
        this.drinkID = drinkID;
        this.orderID = orderID;
        this.drinkName = drinkName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getDrinkID() {
        return drinkID;
    }

    public void setDrinkID(int drinkID) {
        this.drinkID = drinkID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDrink{" +
                "drinkID=" + drinkID +
                ", orderID='" + orderID + '\'' +
                ", drinkName='" + drinkName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
