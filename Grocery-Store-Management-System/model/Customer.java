package model;

import exception.InvalidInputException;

public class Customer implements turnback {
    protected int customerID;
    protected String fullName;
    protected String membershipLevel;
    protected double totalPurchases;
    protected int points;


    //Constructor
    public Customer(int customerID, String fullName, String membershipLevel, double totalPurchases, int points) {
        setCustomerID(customerID);
        setFullName(fullName);
        setMembershipLevel(membershipLevel);
        setTotalPurchases(totalPurchases);
        setPoints(points);
    }

    //Default constructor
    public Customer() {
        this.customerID = 0;
        this.fullName = "Customer";
        this.membershipLevel = "Regular";
        this.totalPurchases = 0.0;
        this.points = 0;
    }

    //Getters
    public int getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public double getTotalPurchases() {
        return totalPurchases;
    }

    public int getPoints() {
        return points;
    }

    //Setters
    public void setCustomerID(int customerID) {
        if (customerID <= 0) throw new IllegalArgumentException("Customer ID cannot be negative");
        this.customerID = customerID;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        this.fullName = fullName;
    }

    public void setMembershipLevel(String membershipLevel) {
        if (membershipLevel == null) throw new IllegalArgumentException("Membership Level must be set");
        membershipLevel = membershipLevel.trim();
        if (membershipLevel.equals("Regular") || membershipLevel.equals("Silver") || membershipLevel.equals("Gold")) {
            this.membershipLevel = membershipLevel;
        } else {
            throw new IllegalArgumentException("Membership level must be Regular, Silver, or Gold");
        }
    }

    public void setTotalPurchases(double totalPurchases) {
        if (totalPurchases < 0) throw new IllegalArgumentException("Total purchases cannot be negative");
        this.totalPurchases = totalPurchases;
    }

    public void setPoints(int points) {
        if (points < 0) throw new IllegalArgumentException("Points cannot be negative");
        this.points = points;
    }

    //logic
    public void addPurchase(double amount) {
        if (amount <= 0) return;
        totalPurchases += amount;

        int earned = (int) (amount / 1000);
        points += earned;

        if (totalPurchases >= 100000) {
            membershipLevel = "Gold";
        } else if (totalPurchases >= 50000) {
            // Upgrade to Silver only if not already Gold
            if (!membershipLevel.equals("Gold")) {
                membershipLevel = "Silver";
            }
        }
    }

    public boolean isVIP() {
        return membershipLevel.equals("Gold") || points >= 200;
    }

    public double getDiscountRate() {
        if ("Gold".equals(membershipLevel)) return 0.10;
        if ("Silver".equals(membershipLevel)) return 0.05;
        return 0.0;
    }

    @Override
    public double turnBack(Sale sale, Product product, int qty) {
        if (sale == null) throw new IllegalArgumentException("Sale is null");
        if (product == null) throw new IllegalArgumentException("Product is null");
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        if (sale.getCustomerID() != this.customerID) throw new IllegalArgumentException("This sale is not for this customer");
        double refund = product.getPrice() * qty;
        if (refund < 0) throw new IllegalArgumentException("Refund must be > 0")
        product.setStockQuantity(product.getStockQuantity() + qty);
        this.totalPurchases = (this.totalPurchases - refund);
        return refund;
    }

    // toString
    @Override
    public String toString() {
        return "Customer{customerID=" + customerID +
                ", fullName='" + fullName + '\'' +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", totalPurchases=" + totalPurchases +
                ", points=" + points +
                '}';
    }
}

