public class Main {
    public static void main(String[] args) {
        System.out.println("=== Grocery Store Management System ===");
        System.out.println();

        //objects
        Product p1 = new Product(101, "Milk 1L", 550.0, 10, "Dairy");
        Product p2 = new Product(102, "Bread", 200.0, 5, "Bakery");
        Product p3 = new Product(); // default

        Customer c1 = new Customer(1, "Aruzhan S.", "Regular", 12000.0, 10);
        Customer c2 = new Customer();

        Sale s1 = new Sale(9001, c1.getCustomerID(), 0.0, "2025-12-17", "OPEN");

        // 3) Display all objects
        System.out.println("--- PRODUCTS ---");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println();

        System.out.println("--- CUSTOMERS ---");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println();

        System.out.println("--- SALES ---");
        System.out.println(s1);
        System.out.println();

        //Test getters
        System.out.println("\n--- TESTING GETTERS ---");
        System.out.println("Product 1 name: " + p1.getName());
        System.out.println("Product 1 stock: " + p1.getStockQuantity());
        System.out.println("Customer 1 discount rate: " + (c1.getDiscountRate() * 100) + "%");
        System.out.println("Sale 1 status: " + s1.getStatus());

        //Test setters
        System.out.println("\n--- TESTING SETTERS ---");
        System.out.println("Updating default product (p3)...");
        p3.setProductID(103);
        p3.setName("Apples 1kg");
        p3.setPrice(800.0);
        p3.setStockQuantity(20);
        p3.setCategory("Fruits");
        System.out.println("Updated p3: " + p3);

        System.out.println("\nUpdating default customer (c2)...");
        c2.setCustomerID(2);
        c2.setFullName("Dias K.");
        c2.setMembershipLevel("Silver");
        c2.setTotalPurchases(60000.0);
        c2.setPoints(120);
        System.out.println("Updated c2: " + c2);

        //Test additional methods
        System.out.println("\n--- TESTING PRODUCT METHODS ---");
        System.out.println(p1.getName() + " in stock: " + p1.isInStock());
        System.out.println("Selling 3 of " + p1.getName());
        System.out.println("Sold: " + p1.sell(3) + ", new stock: " + p1.getStockQuantity());
        System.out.println("Restocking 5 of " + p2.getName());
        p2.restock(5);
        System.out.println(p2.getName() + " new stock: " + p2.getStockQuantity());
        System.out.println("Applying 10% discount to " + p3.getName());
        p3.applyDiscount(10);
        System.out.println(p3.getName() + " new price: " + p3.getPrice());

        System.out.println("\n--- TESTING CUSTOMER METHODS ---");
        System.out.println(c1.getFullName() + " VIP: " + c1.isVIP());
        System.out.println("Adding purchase 45000 to " + c1.getFullName());
        c1.addPurchase(45000);
        System.out.println("Updated customer: " + c1);
        System.out.println(c1.getFullName() + " VIP: " + c1.isVIP());

        System.out.println("\n--- TESTING SALE METHODS ---");
        System.out.println("Sale open: " + s1.isOpen());
        System.out.println("Adding items to sale...");
        s1.addItem(p1, 2);
        s1.addItem(p3, 4);
        System.out.println("Sale total now: " + s1.getTotalAmount());
        s1.completeSale();
        System.out.println("Sale after completion: " + s1);

        //Final state
        System.out.println("\n--- FINAL STATE ---");
        System.out.println("Products:");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        System.out.println("\nCustomers:");
        System.out.println(c1);
        System.out.println(c2);

        System.out.println("\nSales:");
        System.out.println(s1);

        System.out.println("\n=== Program Complete ===");
    }
}
