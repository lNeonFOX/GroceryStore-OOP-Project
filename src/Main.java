import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Grocery Store Management System ===\n");

        //objects
        products.add(new Product(101, "Milk 1L", 550.0, 10, "Diary"));
        products.add(new Product(102, "Bread", 500.0, 20, "Bakery"));
        products.add(new Product(103, "Potato 1kg", 280.0, 30, "Vegetables"));

        customers.add(new Customer(1, "Aruzhan S.", "Regular", 12000.0, 10));
        customers.add(new Customer(2, "Daria A", "Gold", 100000.0, 200));

        sales.add(new Sale(9000, 1, 2.0, "2026-09.01", "OPEN"));

        boolean running = true;
        while (running) {
            displayMenu(); // Show menu options
            int choice = scanner.nextInt(); // Read user's choice
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    viewAllCustomers();
                    break;
                case 5:
                    createSale();
                    break;
                case 6:
                    viewAllSales();
                    break;
                case 0:
                    System.out.println("\nGoodbye! ");
                    running = false; // Exit loop
                    break;
                default:
                    System.out.println("\n Invalid choice!");
            }
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine(); // Wait for user
            }
        }
        scanner.close(); // Clean up
    } // End of main method

    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Add Customer");
        System.out.println("4. View All Customers");
        System.out.println("5. Create Sale (OPEN)");
        System.out.println("6. View All Sales");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    private static void addProduct() {
        System.out.println("\n--- ADD PRODUCT ---");

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

        Product p = new Product(productID, name, price, stockQuantity, category);
        products.add(p);

        System.out.println("\n Product added successfully!");
    }

    private static void viewAllProducts() {
        System.out.println("\n========================================");
        System.out.println(" ALL PRODUCTS");
        System.out.println("========================================");

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("Total items: " + products.size());
        System.out.println();

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);

            System.out.println((i + 1) + ". " + p.getName() + "- " + p.getPrice());
            System.out.println(" Category: " + p.getCategory());
            System.out.println(" Available: " + p.getStockQuantity());
            System.out.println();
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