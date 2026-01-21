package model;

public class BasicProduct extends Product {
    public BasicProduct(int productID, String name, double price, int stockQuantity, String category){
        super(productID, name, price, stockQuantity, category);
    }
    public BasicProduct() {
        super();
    }
    @Override
    public void handle(){
        System.out.println(name + " is being handled as a regular product.");
    }
    @Override
    public String getType(){
        return "Product";
    }
}
