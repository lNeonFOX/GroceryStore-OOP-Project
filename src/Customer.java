public class Customer {
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
    public int getPoints() { return points; }

    //Setters
    public void setCustomerID(int customerID) {
        if (customerID <= 0) return;
        this.customerID = customerID;
    }
    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) return;
        this.fullName = fullName;
    }
    public void setMembershipLevel(String membershipLevel) {
        if (membershipLevel == null) return;
        membershipLevel = membershipLevel.trim();
        if (membershipLevel.equals("Regular") || membershipLevel.equals("Silver") || membershipLevel.equals("Gold")) {
            this.membershipLevel = membershipLevel;
        }
    }

    public void setTotalPurchases(double totalPurchases) {
        if (totalPurchases < 0) return;
        this.totalPurchases = totalPurchases;
    }
    public void setPoints(int points) {
        if (points < 0) return;
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
