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
        System.out.println("├─ DEMO & OTHER ─────────────────────────┤");
        System.out.println("│12. Polymorphism Demo                   │");
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
                        demonstratePolymorphism();
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
            return;
        }

        for (int i = 0; i < freshProducts.size(); i++) {
            System.out.println((i + 1) + ". " + freshProducts.get(i));
        }
    }


    private void viewPackagedOnly() {
        List<Product> packaged = productDAO.getPackagedProducts();

        System.out.println("\n--- PACKAGED PRODUCTS ONLY ---");
        if (packaged.isEmpty()) {
            System.out.println("No packaged products in database.");
            return;
        }

        for (int i = 0; i < packaged.size(); i++) {
            System.out.println((i + 1) + ". " + packaged.get(i));
        }
    }

    private void demonstratePolymorphism() {
        productDAO.demonstratePolymorphism();
    }

    private void updateProduct() {
        try {
            System.out.println("\n--- UPDATE PRODUCT ---");
            System.out.println("Choose type to update:");
            System.out.println("1) BASIC   2) FRESH   3) PACKAGED");
            System.out.print("Type: ");
            int typeChoice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter product ID to update: ");
            int productID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter new stock quantity: ");
            int stockQuantity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new category: ");
            String category = scanner.nextLine();

            Product p;
            if (typeChoice == 2) {
                System.out.print("Enter days to expire: ");
                int daysToExpire = scanner.nextInt();

                System.out.print("Refrigerated? (true/false): ");
                boolean refrigerated = scanner.nextBoolean();
                scanner.nextLine();

                p = new FreshProduct(productID, name, price, stockQuantity, category, daysToExpire, refrigerated);

            } else if (typeChoice == 3) {
                System.out.print("Enter weight (kg): ");
                double weightKg = scanner.nextDouble();
                scanner.nextLine();

                p = new PackagedProduct(productID, name, price, stockQuantity, category, weightKg);

            } else {
                p = new BasicProduct(productID, name, price, stockQuantity, category);
            }

            productDAO.updateProduct(p);

        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void deleteProduct() {
        try {
            System.out.println("\n--- DELETE PRODUCT ---");
            System.out.print("Enter product ID to delete: ");
            int productID = scanner.nextInt();
            scanner.nextLine();

            productDAO.deleteProduct(productID);

        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
            scanner.nextLine();
        }
    }

    // ========================================
    // SEARCH
    // ========================================

    private void searchByName() {
        System.out.println("\n--- SEARCH BY NAME ---");
        System.out.print("Enter name keyword: ");
        String keyword = scanner.nextLine();

        List<Product> results = productDAO.searchByName(keyword);
        if (results.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i));
        }
    }

    private void searchByPriceRange() {
        try {
            System.out.println("\n--- SEARCH BY PRICE RANGE ---");
            System.out.print("Enter min price: ");
            double min = scanner.nextDouble();

            System.out.print("Enter max price: ");
            double max = scanner.nextDouble();
            scanner.nextLine();

            List<Product> results = productDAO.searchByPriceRange(min, max);
            if (results.isEmpty()) {
                System.out.println("No products found.");
                return;
            }

            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Please enter valid numbers.");
            scanner.nextLine();
        }
    }

    private void searchByMinPrice() {
        try {
            System.out.println("\n--- SEARCH BY MIN PRICE ---");
            System.out.print("Enter minimum price: ");
            double min = scanner.nextDouble();
            scanner.nextLine();

            List<Product> results = productDAO.searchByMinPrice(min);
            if (results.isEmpty()) {
                System.out.println("No products found.");
                return;
            }

            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Please enter a valid number.");
            scanner.nextLine();
        }
    }
}