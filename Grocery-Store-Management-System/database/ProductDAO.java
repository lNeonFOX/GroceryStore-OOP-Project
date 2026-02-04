package database;

import model.*;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public boolean insertProduct(Product p) {
        String sql = "INSERT INTO product (name, price, stock_quantity, category) VALUES (?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            //SET parameters (? => actual values)
            statement.setString(1, p.getName());
            statement.setDouble(2, p.getPrice());
            statement.setInt(3, p.getStockQuantity());
            statement.setString(4, p.getCategory());

            //execute insert
            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Product inserted successfully " + p.getName());
                return true;
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n--- ALL PRODUCTs FROM DATABASE ---");
            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int stock_quantity = resultSet.getInt("stock_quantity");
                String category = resultSet.getString("category");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Price: " + price);
                System.out.println("Stock Quantity" + stock_quantity);
                System.out.println("category: " + category);
                System.out.println("---");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Select failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    public List<Product> getFreshProducts() {
        List<Product> freshProducts = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category ILIKE ? ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return freshProducts;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%Fresh%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product p = extractProductFromResultSet(resultSet);
                if (p != null) freshProducts.add(p);
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + freshProducts.size() + " fresh products");

        } catch (SQLException e) {
            System.out.println("Select fresh products failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return freshProducts;
    }

    public List<Product> getPackagedProducts() {
        List<Product> packagedProducts = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category ILIKE ? ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return packagedProducts;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%Fresh%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product p = extractProductFromResultSet(resultSet);
                if (p != null) packagedProducts.add(p);
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + packagedProducts.size() + " fresh products");

        } catch (SQLException e) {
            System.out.println("Select fresh products failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return packagedProducts;
    }

    public boolean updateProduct(Product p) {
        String sql = "UPDATE product SET name = ?, price = ? stock_quantity = ?, category = ?" + "WHERE product_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, p.getName());
            statement.setDouble(2, p.getPrice());
            statement.setInt(3, p.getStockQuantity());
            statement.setString(4, p.getCategory());
            statement.setInt(5, p.getProductID());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("Product updated: " + p.getName());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean deleteProduct(int productID) {
        String sql = "DELETE FROM product WHERE product_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productID);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("Product deleted (ID: " + productID + ")");
                return true;
            } else {
                System.out.println("No Product found with ID: " + productID);
            }
        } catch (SQLException e) {
            System.out.println("Delete failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product p = extractProductFromResultSet(resultSet);
                if (p != null) products.add(p);
            }

            resultSet.close();
            statement.close();

            System.out.println("Found " + products.size() + " products");
        } catch (SQLException e) {
            System.out.println("Search failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product " + "WHERE price BETWEEN ? AND ? " + "ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product p = extractProductFromResultSet(resultSet);
                if (p != null) products.add(p);
            }
            resultSet.close();
            statement.close();

            System.out.println("Found " + products.size() + " products");
        } catch (SQLException e) {
            System.out.println("Search failed: ");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    public List<Product> searchByMinPrice(double minPrice) {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product " + "WHERE price >= ? " + "ORDER BY price DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product p = extractProductFromResultSet(resultSet);
                if (p != null) {
                    products.add(p);
                }
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            int productID = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int stockQunatity = resultSet.getInt("stock_quantity");
            String category = resultSet.getString("category");

            return new BasicProduct(productID, name, price, stockQunatity, category);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void displayAllProducts() {
        List<Product> products = getAllProduct();
        System.out.println("\n========================================");
        System.out.println("   ALL PRODUCTS FROM DATABASE");
        System.out.println("========================================");

        if(products.isEmpty()) {
            System.out.println("No products in database");
        } else {
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
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
    }

    public void demonstratePolymorphism() {
        List<Product> products = getAllProduct();
        System.out.println("\n--- POLYMORPHISM DEMO: handle() ---");
        if (products.isEmpty()) {
            System.out.println("No products to demonstrate");
        } else {
            for (Product p : products) {
                p.handle();
            }
        }

    }
}
