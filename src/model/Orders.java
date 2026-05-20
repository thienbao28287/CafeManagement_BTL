package model;

public class Orders {
    private String orderId;
    private String tableId;
    private double totalAmount;
    private String status;

    public Orders(String orderId, String tableId, double totalAmount, String status) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getOrderId() { return orderId; }
    public String getTableId() { return tableId; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", tableId=" + tableId + ", totalAmount=" + totalAmount + ", status="
				+ status + "]";
	}

	
}