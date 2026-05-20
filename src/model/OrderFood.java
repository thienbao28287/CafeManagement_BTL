package model;


public class OrderFood {
    private int foodID;
    private String orderID;
    private String foodName;
    private double price;
    private int quantity;

    public OrderFood() {
    }

    public OrderFood(int foodID, String orderID, String foodName, double price, int quantity) {
        this.foodID = foodID;
        this.orderID = orderID;
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
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
        return "OrderFood{" +
                "foodID=" + foodID +
                ", orderID='" + orderID + '\'' +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
