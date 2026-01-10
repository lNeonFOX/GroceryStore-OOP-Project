public class Product {
    protected int productID;
    protected String name;
    protected double price;
    protected int stockQuantity;
    protected String category;

    //constructor
    public Product(int productID, String name, double price, int stockQuantity, String category) {
        setProductID(productID);
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
        setCategory(category);
    }

    //default constructor
    public Product() {
        this.productID = 0;
        this.name = "Unknown";
        this.price = 0.0;
        this.stockQuantity = 0;
        this.category = "General";
    }


    //getters
    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getCategory() {
        return category;
    }

    //setters
    public void setProductID(int productID) {
        if (productID <= 0) return;
        this.productID = productID;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) return;
        this.name = name.trim();
    }

    public void setPrice(double price) {
        if (price < 0) return;
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) return;
        this.stockQuantity = stockQuantity;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) return;
        this.category = category;
    }

    public void handle() {
        System.out.println(name + " is being handled like a regular product.");
    }

    public String getType() {
        return "Product";
    }

    public double getFinalPrice() {
        return price;
    }

    //logic 1, 2, 3
    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void restock(int amount) {
        if (amount <= 0) return;
        stockQuantity += amount;
    }

    public boolean sell(int amount) {
        if (amount <= 0) return false;
        if (amount > stockQuantity) return false;
        stockQuantity -= amount;
        return true;
    }

    public void applyDiscount(double percentage) {
        if (percentage <= 0 || percentage >= 100) return;
        price = price * (1 - percentage / 100.0);
    }

    //ToString
    @Override
    public String toString() {
        return "[" + getType() + "] " + name +
                " (ID: " + productID +
                ", Price: " + getFinalPrice() +
                ", Stock: " + stockQuantity +
                ", Category: " + category + ")";
    }
}
