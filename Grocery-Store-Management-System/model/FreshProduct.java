package model;

public class FreshProduct extends Product {
    private int daysToExpire;
    private boolean refrigerated;

    public FreshProduct(int productId, String name, double price, int stockQuantity, String category, int daysToExpire, boolean refrigerated) {
        super(productId, name, price, stockQuantity, category);
        setDaysToExpire(daysToExpire);
        this.refrigerated = refrigerated;
    }

    public int getDaysToExpire() { return daysToExpire; }
    public boolean isRefrigerated() { return refrigerated; }

    public void setDaysToExpire(int daysToExpire) {
        if (daysToExpire < 0){
            throw new IllegalArgumentException("Product Expired" + daysToExpire);
        }
        this.daysToExpire = daysToExpire;
    }

    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }

    @Override
    public void handle() {
        if (refrigerated) {
            System.out.println("Fresh product " + name + " must be stored in a refrigerator.");
        } else {
            System.out.println("Fresh product " + name + " can be stored at room temperature.");
        }
    }

    @Override
    public String getType() {
        return "FreshProduct";
    }

    @Override
    public double getFinalPrice() {
        // Example logic: if expiring soon, apply 20% discount
        if (daysToExpire <= 2) return price * 0.8;
        return price;
    }

    public boolean isExpiringSoon() {
        return daysToExpire <= 2;
    }

    public void reduceDays(int days) {
        if (days <= 0) return;
        daysToExpire = daysToExpire - days;
        if (daysToExpire < 0) {
            daysToExpire = 0;
        }
    }
    @Override
    public String toString() {
        return super.toString() +
                " | DaysToExpire: " + daysToExpire +
                ", Refrigerated: " + refrigerated;
    }
}
