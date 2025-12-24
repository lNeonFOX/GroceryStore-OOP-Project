public class Sale {
    private int saleID;
    private int customerID;
    private double totalAmount;
    private String date;
    private String status;

    // Constructor with parameters
    public Sale(int saleID, int customerID, double totalAmount, String date, String status) {
        this.saleID = saleID;
        this.customerID = customerID;
        this.totalAmount = totalAmount;
        this.date = date;
        this.status = status;
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
        this.saleID = saleID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setTotalAmount(double totalAmount) {
        if (totalAmount < 0) return;
        this.totalAmount = totalAmount;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    //logic
    public boolean isOpen() {
        return "OPEN".equals(status);
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
        if (!"CANCELLED".equals(status)) {
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
