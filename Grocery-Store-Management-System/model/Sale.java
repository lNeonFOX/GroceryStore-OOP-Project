package model;

public class Sale {
    protected int saleID;
    protected int customerID;
    protected double totalAmount;
    protected String date;
    protected String status;

    // Constructor
    public Sale(int saleID, int customerID, double totalAmount, String date, String status) {
    setSaleID(saleID);
    setCustomerID(customerID);
    setTotalAmount(totalAmount);
    setDate(date);
    setStatus(status);
    }
    //Default constructor
    public Sale() {
        this.saleID = 0;
        this.customerID = 0;
        this.totalAmount = 0.0;
        this.date = "Unknown";
        this.status = "OPEN";
    }

    //Getters
    public int getSaleID() {
        return saleID;
    }
    public int getCustomerID() {
        return customerID;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public String getDate() {
        return date;
    }
    public String getStatus() {
        return status;
    }

    //Setters
    public void setSaleID(int saleID) {
        if (saleID <= 0) throw new IllegalArgumentException("Sale ID cannot be negative");
        this.saleID = saleID;
    }
    public void setCustomerID(int customerID) {
        if (customerID <= 0) throw new IllegalArgumentException("Customer ID cannot be negative");
        this.customerID = customerID;
    }
    public void setTotalAmount(double totalAmount) {
        if (totalAmount < 0) throw new IllegalArgumentException("Total amount cannot be negative");
        this.totalAmount = totalAmount;
    }
    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) throw new IllegalArgumentException("Date cannot be empty");
        this.date = date.trim();
    }
    public void setStatus(String status) {
        if (status == null) throw new IllegalArgumentException("Status can not be NULL");
        status = status.trim();
        if (status.equals("OPEN") || status.equals("COMPLETED") || status.equals("CANCELLED")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status must be OPEN, COMPLETED, or CANCELLED");
        }
    }

    //logic
    public boolean isOpen() {
        return status.equals("OPEN");
    }

    public void addItem(Product product, int qty) {
        if (product == null || qty <= 0) return;
        if (!isOpen()) return;

        boolean sold = product.sell(qty);
        if (sold) {
            totalAmount += product.getPrice() * qty;
        }
    }

    public void completeSale() {
        if (!status.equals("CANCELLED")) {
            status = "COMPLETED";
        }
    }

    public void cancelSale() {
        status = "CANCELLED";
    }

    // toString()
    @Override
    public String toString() {
        return "Sale{saleID=" + saleID +
                ", customerID=" + customerID +
                ", totalAmount=" + totalAmount +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
