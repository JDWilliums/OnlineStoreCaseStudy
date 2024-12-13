import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static ProductDataStore dataStore = new ProductDataStore();

    public static void main(String[] args) {
        // If you need to regenerate data, uncomment the line below.
        // generateSampleData("ecommerce_products.csv");

        loadDataFromCSV("ecommerce_products.csv");

        // Launch the interactive menu
        runMenu();
    }

    private static void loadDataFromCSV(String csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
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
                dataStore.insert(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== E-COMMERCE PRODUCT DATA MENU ===");
            System.out.println("1. Search Product by ID");
            System.out.println("2. Search Product by Name");
            System.out.println("3. Insert New Product");
            System.out.println("4. Delete Product by ID");
            System.out.println("5. Sort Products by Category and Display");
            System.out.println("6. Display Total Stock and Average Price");
            System.out.println("7. Display All Products");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchById(scanner);
                    break;
                case 2:
                    searchByName(scanner);
                    break;
                case 3:
                    insertNewProduct(scanner);
                    break;
                case 4:
                    deleteById(scanner);
                    break;
                case 5:
                    sortByCategoryAndDisplay();
                    break;
                case 6:
                    displayStockAndAverage();
                    break;
                case 7:
                    displayAllProducts();
                    break;
                case 0:
                    System.out.println("Exiting program. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void searchById(Scanner scanner) {
        System.out.print("Enter product ID to search: ");
        int id = scanner.nextInt();
        Product p = dataStore.searchById(id);
        if (p != null) {
            System.out.println("Product found: " + p);
        } else {
            System.out.println("No product found with ID: " + id);
        }
    }

    private static void searchByName(Scanner scanner) {
        System.out.print("Enter product name to search: ");
        scanner.nextLine(); // consume the leftover newline
        String name = scanner.nextLine();
        Product p = dataStore.searchByName(name);
        if (p != null) {
            System.out.println("Product found: " + p);
        } else {
            System.out.println("No product found with name: " + name);
        }
    }

    private static void insertNewProduct(Scanner scanner) {
        System.out.print("Enter new product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter product category: ");
        String category = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter stock quantity: ");
        int stock = scanner.nextInt();
        System.out.print("Enter number sold: ");
        int sold = scanner.nextInt();

        Product p = new Product(id, category, price, productName, stock, sold);
        dataStore.insert(p);
        System.out.println("Product inserted successfully!");
    }

    private static void deleteById(Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        boolean deleted = dataStore.deleteById(id);
        if (deleted) {
            System.out.println("Product with ID " + id + " deleted successfully.");
        } else {
            System.out.println("No product found with ID: " + id);
        }
    }

    private static void sortByCategoryAndDisplay() {
        List<Product> sortedProducts = dataStore.sortByCategory();
        System.out.println("Products sorted by category:");
        for (Product p : sortedProducts) {
            System.out.println(p);
        }
    }

    private static void displayStockAndAverage() {
        int totalStock = dataStore.totalStock();
        double averagePrice = dataStore.averagePrice();
        System.out.println("Total stock in inventory: " + totalStock);
        System.out.println("Average product price: " + averagePrice);
    }

    private static void displayAllProducts() {
        System.out.println("All Products in the Data Store:");
        dataStore.printAll();
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
