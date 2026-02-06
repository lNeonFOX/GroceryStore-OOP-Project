package database;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public boolean insertBasicProduct(BasicProduct p) {
        String sql = "INSERT INTO product (product_id, name, price, stock_quantity, category, product_type, days_to_expire, refrigerated, weight_kg) " + "VALUES (?, ?, ?, ?, ?, 'BASIC', NULL, NULL, NULL)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, p.getProductID());
            statement.setString(2, p.getName());
            statement.setDouble(3, p.getPrice());
            statement.setInt(4, p.getStockQuantity());
            statement.setString(5, p.getCategory());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("BasicProduct inserted: " + p.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Insert BasicProduct failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean insertFreshProduct(FreshProduct p) {
        String sql = "INSERT INTO product (product_id, name, price, stock_quantity, category, product_type, days_to_expire, refrigerated, weight_kg) " + "VALUES (?, ?, ?, ?, ?, 'FRESH', ?, ?, NULL)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, p.getProductID());
            statement.setString(2, p.getName());
            statement.setDouble(3, p.getPrice());
            statement.setInt(4, p.getStockQuantity());
            statement.setString(5, p.getCategory());
            statement.setInt(6, p.getDaysToExpire());
            statement.setBoolean(7, p.isRefrigerated());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Fresh Product inserted successfully " + p.getName());
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

    public boolean insertPackagedProduct(PackagedProduct p) {
        String sql = "INSERT INTO product (product_id, name, price, stock_quantity, category, product_type, days_to_expire, refrigerated, weight_kg) " + "VALUES (?, ?, ?, ?, ?, 'PACKAGED', NULL, NULL, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, p.getProductID());
            statement.setString(2, p.getName());
            statement.setDouble(3, p.getPrice());
            statement.setInt(4, p.getStockQuantity());
            statement.setString(5, p.getCategory());
            statement.setDouble(6, p.getWeightKg());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Packaged Product inserted successfully " + p.getName());
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

    public boolean insertProduct(Product p) {
        if (p instanceof FreshProduct) return insertFreshProduct((FreshProduct) p);
        if (p instanceof PackagedProduct) return insertPackagedProduct((PackagedProduct) p);
        if (p instanceof BasicProduct) return insertBasicProduct((BasicProduct) p);

        return insertBasicProduct(new BasicProduct(p.getProductID(), p.getName(), p.getPrice(), p.getStockQuantity(), p.getCategory()));
    }

    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product p = extractProductFromResultSet(resultSet);
                if (p != null) products.add(p);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Select all products failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    public List<Product> getFreshProducts() {
        return getProductsByType("FRESH");
    }

    public List<Product> getPackagedProducts() {
        return getProductsByType("PACKAGED");
    }

    private List<Product> getProductsByType(String type) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_type = ? ORDER BY product_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return products;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, type);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product p = extractProductFromResultSet(resultSet);
                if (p != null) products.add(p);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Select products by type failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return products;
    }

    public boolean updateProduct(Product p) {
        String sql = "UPDATE product SET name = ?, price = ?, stock_quantity = ?, category = ?, " + "product_type = ?, days_to_expire = ?, refrigerated = ?, weight_kg = ? " + "WHERE product_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, p.getName());
            statement.setDouble(2, p.getPrice());
            statement.setInt(3, p.getStockQuantity());
            statement.setString(4, p.getCategory());

            if (p instanceof FreshProduct) {
                FreshProduct fp = (FreshProduct) p;
                statement.setString(5, "FRESH");
                statement.setInt(6, fp.getDaysToExpire());
                statement.setBoolean(7, fp.isRefrigerated());
                statement.setNull(8, Types.DOUBLE);

            } else if (p instanceof PackagedProduct) {
                PackagedProduct pp = (PackagedProduct) p;
                statement.setString(5, "PACKAGED");
                statement.setNull(6, Types.INTEGER);
                statement.setNull(7, Types.BOOLEAN);
                statement.setDouble(8, pp.getWeightKg());

            } else {
                statement.setString(5, "BASIC");
                statement.setNull(6, Types.INTEGER);
                statement.setNull(7, Types.BOOLEAN);
                statement.setNull(8, Types.DOUBLE);
            }

            statement.setInt(9, p.getProductID());
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
        int productID = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        int stockQuantity = resultSet.getInt("stock_quantity");
        String category = resultSet.getString("category");
        String productType = resultSet.getString("product_type");

        if (productType == null) productType = "BASIC";

        if ("FRESH".equals(productType)) {
            int daysToExpire = resultSet.getInt("days_to_expire");
            boolean refrigerated = resultSet.getBoolean("refrigerated");
            return new FreshProduct(productID, name, price, stockQuantity, category, daysToExpire, refrigerated);

        } else if ("PACKAGED".equals(productType)) {
            double weightKg = resultSet.getDouble("weight_kg");
            return new PackagedProduct(productID, name, price, stockQuantity, category, weightKg);
        } else {
            return new BasicProduct(productID, name, price, stockQuantity, category);
        }
    }

    public void displayAllProducts() {
        List<Product> products = getAllProduct();
        System.out.println("\n========================================");
        System.out.println("   ALL PRODUCTS FROM DATABASE");
        System.out.println("========================================");

        if (products.isEmpty()) {
            System.out.println("No products in database");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + ". " + p);
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
