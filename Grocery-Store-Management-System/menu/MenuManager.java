package menu;

import model.*;
import database.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {
    private Scanner scanner;
    private ProductDAO productDAO;

    public MenuManager() {
        this.scanner = new Scanner(System.in);
        this.productDAO = new ProductDAO();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  RESTAURANT MANAGEMENT SYSTEM v2.0    ║");
        System.out.println("║  Week 8: Fully Database-Driven 🗄️     ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("✅ All data is stored in PostgreSQL");
        System.out.println("✅ No in-memory ArrayLists");
        System.out.println("✅ Complete CRUD operations");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println(" GROCERY STORE MANAGEMENT SYSTEM");
        System.out.println("┌─ PRODUCT MANAGEMENT ───────────────────┐");
        System.out.println("│1. Add BasicProduct                     │");
        System.out.println("│2. Add FreshProduct                     │");
        System.out.println("│3. Add Packaged Product                 │");
        System.out.println("│4. View All Products (polymorphism)     │");
        System.out.println("│5. View FreshProducts Only              │");
        System.out.println("│6. View PackagedProducts Only           │");
        System.out.println("│7. Update Product                       │");
        System.out.println("│8. Delete Product                       │");
        System.out.println("├─ SEARCH & FILTER ──────────────────────┤");
        System.out.println("│ 9. Search by Name                      │");
        System.out.println("│ 10. Search by Price Range              │");
        System.out.println("│ 11. Search by Min Price                │");
        System.out.println("├─ CUSTOMER AND SALE MANAGEMENT ─────────┤");
        System.out.println("12. Add Customer                         │");
        System.out.println("13. View All Customers                   │");
        System.out.println("14. Create Sale                          │");
        System.out.println("15. View All Sales                       │");
        System.out.println("├─ DEMO & OTHER ─────────────────────────┤");
        System.out.println("│16. Polymorphism Demo                   │");
        System.out.println("│17. Return product                      │");
        System.out.println("│0. Exit                                 │");
        System.out.println("└────────────────────────────────────────┘");
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
                        addBasicProduct();
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
                        updateProduct();
                        break;
                    case 8:
                        deleteProduct();
                        break;
                    case 9:
                        searchByName();
                        break;
                    case 10:
                        searchByPriceRange();
                        break;
                    case 11:
                        searchByMinPrice();
                        break;
                    case 12:
                        addCustomer();
                        break;
                    case 13:
                        viewAllCustomers();
                        break;
                    case 14:
                        createSale();
                        break;
                    case 15:
                        viewAllSales();
                        break;
                    case 16:
                        demonstratePolymorphism();
                        break;
                    case 17:
                        returnProduct;
                        break;

                    case 0:
                        System.out.println("\nGoodbye! ");
                        running = false;
                        break;
                    default:
                        System.out.println("\n Invalid choice!");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println(" Error: Please enter a valid number!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(" Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void addBasicProduct() {
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
            productDAO.insertProduct(p);
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
            productDAO.insertProduct(p);
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
            productDAO.insertProduct(p);
            System.out.println("\nPackaged product added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("NO" + e.getMessage());
        }
    }

    private void viewAllProducts() {
        productDAO.displayAllProducts();
    }

    private void viewFreshOnly() {
        List<Product> freshProducts = productDAO.getFreshProducts();

        System.out.println("\n--- FRESH PRODUCTS ONLY ---");
        if (freshProducts.isEmpty()) {
            System.out.println("No fresh products in Database");
        } else {
            for (int i = 0; i < freshProducts.size(); i++)
            Product p = freshProducts.get(i);
            System.out.println(i + 1 + ". " + Pr);
        }
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