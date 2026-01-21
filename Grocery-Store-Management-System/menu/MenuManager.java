package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {
    static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Product> inventory = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();

    public MenuManager() {
        System.out.println("=== Grocery Store Management System ===\n");

        //objects
        inventory.add(new BasicProduct(100, "Sugar", 450.0, 20, "Baking"));
        inventory.add(new FreshProduct(101, "Milk 1L", 550.0, 10, "Dairy", 2, true));
        inventory.add(new PackagedProduct(102, "Pasta", 600.0, 15, "Grocery", 1.0));

        customers.add(new Customer(1, "Aruzhan S.", "Regular", 12000.0, 10));
        customers.add(new Customer(2, "Daria A", "Gold", 100000.0, 200));

        sales.add(new Sale(9000, 1, 2.0, "2026-09.01", "OPEN"));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println(" GROCERY STORE MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add BasicProduct");
        System.out.println("2. Add FreshProduct");
        System.out.println("3. Add Packaged Product");
        System.out.println("4. View All Products (polymorphism)");
        System.out.println("5. View FreshProducts Only");
        System.out.println("6. View PackagedProducts Only");
        System.out.println("7. Demonstrate Polymorphism (handle())");
        System.out.println("----------------------------------------");
        System.out.println("8. Add Customer");
        System.out.println("9. View All Customers");
        System.out.println("10. Create Sale");
        System.out.println("11. View All Sales");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addParentProduct();
                        break;
                    case 2:
                        addFreshProduct();
                        break;
                    case 3:
                        addPackagedProduct();
                        break;
                    case 4:
                        viewAllProducts();
                        break;
                    case 5:
                        viewFreshOnly();
                        break;
                    case 6:
                        viewPackagedOnly();
                        break;
                    case 7:
                        demonstratePolymorphism();
                        break;
                    case 8:
                        addCustomer();
                        break;
                    case 9:
                        viewAllCustomers();
                        break;
                    case 10:
                        createSale();
                        break;
                    case 11:
                        viewAllSales();
                        break;
                    case 0:
                        System.out.println("\nGoodbye! ");
                        running = false;
                        break;
                    default:
                        System.out.println("\n Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void addParentProduct() {
        try {
            System.out.print("Enter product ID ");
            int productID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter product price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter stock quantity: ");
            int stockQuantity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Category name: ");
            String category = scanner.nextLine();

            Product p = new BasicProduct(productID, name, price, stockQuantity, category);
            inventory.add(p);
            System.out.println("Basic Product added!");
        } catch (IllegalArgumentException e) {
            System.out.println("NO" + e.getMessage());
        }
    }

    private void addFreshProduct() {
        try {
            System.out.print("Enter product ID ");
            int productID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter product price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter stock quantity: ");
            int stockQuantity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Category name: ");
            String category = scanner.nextLine();

            System.out.print("Enter days to expire: ");
            int daysToExpire = scanner.nextInt();

            System.out.print("Refrigerated? (true/false");
            boolean refrigerated = scanner.nextBoolean();

            Product p = new FreshProduct(productID, name, price, stockQuantity, category, daysToExpire, refrigerated);
            inventory.add(p);
            System.out.println("Fresh Product added!");
        } catch (IllegalArgumentException e) {
            System.out.println("NO" + e.getMessage());
        }
    }

    private void addPackagedProduct() {
        try {
            System.out.print("Enter product ID ");
            int productID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter product price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter stock quantity: ");
            int stockQuantity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Category name: ");
            String category = scanner.nextLine();

            System.out.print("Enter weight: ");
            double weightKg = scanner.nextDouble();

            Product p = new PackagedProduct(productID, name, price, stockQuantity, category, weightKg);
            inventory.add(p);
            System.out.println("\nPackaged product added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("NO" + e.getMessage());
        }
    }

    private void viewAllProducts() {
        System.out.println("\n========================================");
        System.out.println(" ALL PRODUCTS");
        System.out.println("========================================");

        if (inventory.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("Total products: " + inventory.size());
        System.out.println();

        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);

            System.out.println((i + 1) + ". " + p.getName() + "- " + p.getPrice());
            System.out.println(" Category: " + p.getCategory());
            System.out.println(" Available: " + p.getStockQuantity());
            if (p instanceof FreshProduct) {
                FreshProduct fp = (FreshProduct) p;
                System.out.println("   Days to expire: " + fp.getDaysToExpire());
            } else if (p instanceof PackagedProduct) {
                PackagedProduct pp = (PackagedProduct) p;
                System.out.println("   Weight (kg): " + pp.getWeightKg());
            }
            System.out.println();
        }
    }

    private void viewFreshOnly() {
        System.out.println("\n--- FRESH PRODUCTS ONLY ---");
        int count = 0;
        for (Product p : inventory) {
            if (p instanceof FreshProduct) {
                count++;
                System.out.println(count + ". " + p);
            }
        }
        if (count == 0) System.out.println("No fresh products found.");
    }

    private static void viewPackagedOnly() {
        System.out.println("\n--- PACKAGED PRODUCTS ONLY ---");
        int count = 0;
        for (Product p : inventory) {
            if (p instanceof PackagedProduct) {
                count++;
                System.out.println(count + ". " + p);
            }
        }
        if (count == 0) System.out.println("No packaged products found.");
    }

    private static void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO: handle() ---");
        for (Product p : inventory) {
            p.handle();
        }
    }

    private static void addCustomer() {
        System.out.println("\n--- ADD CUSTOMER ---");

        System.out.print("Enter customer ID ");
        int CustomerID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter membership level: ");
        String membershipLevel = scanner.nextLine();

        System.out.print("Enter total purchases: ");
        double totalPurchases = scanner.nextDouble();

        System.out.print("Enter number of points: ");
        int points = scanner.nextInt();

        Customer c = new Customer(CustomerID, fullName, membershipLevel, totalPurchases, points);
        customers.add(c);

        System.out.println("\n Customer added successfully!");
    }

    private static void viewAllCustomers() {
        System.out.println("\n========================================");
        System.out.println(" ALL CUSTOMERS");
        System.out.println("========================================");

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        System.out.println("Total customers: " + customers.size());
        System.out.println();

        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);

            System.out.println((i + 1) + ". " + c.getCustomerID() + "- " + c.getFullName());
            System.out.println(" Total purchases: " + c.getTotalPurchases());
            System.out.println(" Membership Level: " + c.getMembershipLevel());
            System.out.println(" Points: " + c.getPoints());
            System.out.println();
        }
    }

    private static void createSale() {
        System.out.println("\n--- ADD SALE ---");

        System.out.print("Enter SALE ID ");
        int saleID = scanner.nextInt();

        System.out.print("Enter customer ID: ");
        int customerID = scanner.nextInt();

        System.out.print("Enter total amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter date: ");
        String date = scanner.nextLine();

        System.out.print("Enter Status: ");
        String status = scanner.nextLine();

        Sale s = new Sale(saleID, customerID, totalAmount, date, status);
        sales.add(s);

        System.out.println("\n Sale created successfully!");
    }

    private static void viewAllSales() {
        System.out.println("\n========================================");
        System.out.println(" ALL SALES");
        System.out.println("========================================");

        if (sales.isEmpty()) {
            System.out.println("No sales found.");
            return;
        }

        System.out.println("Total items: " + sales.size());
        System.out.println();

        for (int i = 0; i < sales.size(); i++) {
            Sale s = sales.get(i);

            System.out.println((i + 1) + ". " + s.getSaleID() + "  " + s.getCustomerID());
            System.out.println(" Date: " + s.getDate());
            System.out.println(" Total Amount: " + s.getTotalAmount());
            System.out.println(" Status: " + s.getStatus());
            System.out.println();
        }
    }
}