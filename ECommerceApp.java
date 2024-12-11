import java.util.*;

class Product {
    int productID;
    String category;
    double price;
    String productName;
    int stockQuantity;
    int numberSold;

    public Product(int productID, String category, double price, String productName, int stockQuantity, int numberSold) {
        this.productID = productID;
        this.category = category;
        this.price = price;
        this.productName = productName;
        this.stockQuantity = stockQuantity;
        this.numberSold = numberSold;
    }

    @Override
    public String toString() {
        return "ProductID: " + productID + ", Name: " + productName + ", Category: " + category + ", Price: " + price + ", Stock: " + stockQuantity + ", Sold: " + numberSold;
    }
}

public class ECommerceApp {

    private List<Product> productList = new ArrayList<>();

    public void populateData() {
        String[] categories = {"Clothing", "Accessories", "Footwear", "Outerwear", "Activewear"};
        Random random = new Random();

        for (int i = 1; i <= 200; i++) {
            String category = categories[random.nextInt(categories.length)];
            double price = Math.round((10 + random.nextDouble() * 90) * 100.0) / 100.0;
            String productName = "Product" + i;
            int stockQuantity = random.nextInt(100) + 1;
            int numberSold = random.nextInt(50) + 1;

            productList.add(new Product(i, category, price, productName, stockQuantity, numberSold));
        }
    }

    public Product searchProductByName(String name) {
        for (Product product : productList) {
            if (product.productName.equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public void sortProductsByPrice() {
        productList.sort(Comparator.comparingDouble(product -> product.price));
    }

    public void deleteProduct(int productID) {
        productList.removeIf(product -> product.productID == productID);
    }

    public double calculateTotalSales() {
        double totalSales = 0;
        for (Product product : productList) {
            totalSales += product.price * product.numberSold;
        }
        return totalSales;
    }

    public void displayProducts(int limit) {
        System.out.println("Displaying up to " + limit + " products (Total: " + productList.size() + "):");
        int count = 0;
        for (Product product : productList) {
            System.out.println(product);
            count++;
            if (count >= limit) break;
        }
    }

    public static void main(String[] args) {
        ECommerceApp app = new ECommerceApp();
        app.populateData();

        // Display a subset of products
        System.out.println("Initial Products (First 10):");
        app.displayProducts(10);

        // Search for a specific product
        System.out.println("\nSearching for 'Product20':");
        Product foundProduct = app.searchProductByName("Product20");
        System.out.println(foundProduct != null ? foundProduct : "Product not found.");

        // Sort products by price
        System.out.println("\nSorting Products by Price:");
        app.sortProductsByPrice();
        app.displayProducts(10);

        // Delete a product by ID
        System.out.println("\nDeleting Product with ID 3:");
        app.deleteProduct(3);
        app.displayProducts(10);

        // Calculate and display total sales
        System.out.println("\nCalculating Total Sales:");
        System.out.printf("Total Sales: £%.2f%n", app.calculateTotalSales());
    }
}