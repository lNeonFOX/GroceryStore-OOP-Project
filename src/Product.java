public class Product {
    private int productID;
    private String name;
    private double price;
    private int stockQuantity;
    private String category;

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
    if(productID <= 0) return;
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

//logic 1, 2, 3
public boolean isInStock(){
    return stockQuantity > 0;
}
public void restock(int amount){
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
        return "Product{productId=" + productID +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", stockQuantity=" + stockQuantity +
            ", category='" + category + '\'' +
            '}';
        }
    }
