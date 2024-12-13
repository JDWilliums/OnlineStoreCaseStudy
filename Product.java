public class Product {
    private int id;
    private String category;
    private double price;
    private String productName;
    private int stockQuantity;
    private int numberSold;

    public Product(int id, String category, double price, String productName, int stockQuantity, int numberSold) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.productName = productName;
        this.stockQuantity = stockQuantity;
        this.numberSold = numberSold;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getNumberSold() {
        return numberSold;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public void setNumberSold(int numberSold) {
        this.numberSold = numberSold;
    }
    
    @Override
    public String toString() {
        return "Product[ID=" + id + ", Category=" + category + ", Price=" + price
                + ", Name=" + productName + ", Stock=" + stockQuantity + ", Sold=" + numberSold + "]";
    }
}
