import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // If you need to regenerate data, uncomment the line below.
        // generateSampleData("ecommerce_products.csv");

        // Now read the data into our dynamic data structure (linked list)
        ProductLinkedList productList = new ProductLinkedList();
        
        try (BufferedReader br = new BufferedReader(new FileReader("ecommerce_products.csv"))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                int id = Integer.parseInt(tokens[0]);
                String category = tokens[1];
                double price = Double.parseDouble(tokens[2]);
                String productName = tokens[3];
                int stockQuantity = Integer.parseInt(tokens[4]);
                int numberSold = Integer.parseInt(tokens[5]);

                Product p = new Product(id, category, price, productName, stockQuantity, numberSold);
                productList.insert(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Demonstrate searching
        Product foundById = productList.searchById(50);
        if (foundById != null) {
            System.out.println("Found product by ID (50): " + foundById);
        } else {
            System.out.println("No product found with ID 50.");
        }

        Product foundByName = productList.searchByName("Product100");
        if (foundByName != null) {
            System.out.println("Found product by name (Product100): " + foundByName);
        } else {
            System.out.println("No product found with name 'Product100'.");
        }

        // Demonstrate sorting by price
        System.out.println("\nSorting products by price...");
        productList.sortByPrice();
        productList.printAll();

        // Demonstrate deletion
        boolean deleted = productList.deleteById(10);
        System.out.println("\nDeletion of product with ID 10: " + (deleted ? "Success" : "Fail"));

        // Demonstrate aggregations
        System.out.println("Total stock in inventory: " + productList.totalStock());
        System.out.println("Average price of products: " + productList.averagePrice());
    }

    private static void generateSampleData(String csvFile) {
        String[] categories = {"Clothing", "Accessories", "Footwear", "Outerwear", "Activewear"};
        Random random = new Random();

        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("ID,Category,Price,ProductName,StockQuantity,NumberSold\n");

            for (int i = 1; i <= 200; i++) {
                String category = categories[random.nextInt(categories.length)];
                double price = Math.round((10 + random.nextDouble() * 90) * 100.0) / 100.0;
                String productName = "Product" + i;
                int stockQuantity = random.nextInt(100) + 1;
                int numberSold = random.nextInt(50) + 1;

                writer.write(i + "," + category + "," + price + "," + productName + "," + stockQuantity + "," + numberSold + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
