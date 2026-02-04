package model;

public class PackagedProduct extends Product {
    private double weightKg;

    public PackagedProduct(int productID, String name, double price, int stockQuantity, String category, double weightKg) {
        super(productID, name, price, stockQuantity, category);
        setWeightKg(weightKg);
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        if (weightKg <= 0) {
            throw new IllegalArgumentException("Weight cannot be negative!");
        }
        this.weightKg = weightKg;
    }
    @Override
    public void handle() {
        System.out.println("Packaged product " + name + " should be stored on shelves safely.");
    }
    @Override
    public String getType() {
        return "PackagedProduct";
    }
    @Override
    public double getFinalPrice() {
        return price + (20 * weightKg);
    }
    public double pricePerKg() {
        return getFinalPrice() / weightKg;
    }
    public boolean isHeavy() {
        return weightKg >= 2.0;
    }
    @Override
    public String toString() {
        return super.toString() + " | WeightKg: " + weightKg;
    }
}