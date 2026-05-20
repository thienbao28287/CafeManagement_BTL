package model;

public class CoffeeTable {

    private String tableID;
    private int seatCount;
    private String status;
    private String location;

    public CoffeeTable() {

    }

    public CoffeeTable(
            String tableID,
            int seatCount,
            String status,
            String location
    ) {

        this.tableID = tableID;
        this.seatCount = seatCount;
        this.status = status;
        this.location = location;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {

        return "CoffeeTable [tableID="
                + tableID
                + ", seatCount="
                + seatCount
                + ", status="
                + status
                + ", location="
                + location
                + "]";
    }
}