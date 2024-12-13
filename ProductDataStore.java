import java.util.*;

public class ProductDataStore {
    private HashMap<Integer, Product> productMap;

    public ProductDataStore() {
        productMap = new HashMap<>();
    }

    // Insertion
    public void insert(Product product) {
        productMap.put(product.getId(), product);
    }

    // Search by ID (O(1) average)
    public Product searchById(int id) {
        return productMap.get(id);
    }

    // Search by Name (O(n))
    public Product searchByName(String name) {
        for (Product p : productMap.values()) {
            if (p.getProductName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // Deletion by ID (O(1) average)
    public boolean deleteById(int id) {
        return productMap.remove(id) != null;
    }

    // Sorting by Price (O(n log n)):
    // Extract values into ArrayList, sort them, and return or print them
    public List<Product> sortByPrice() {
        List<Product> productList = new ArrayList<>(productMap.values());
        productList.sort(Comparator.comparingDouble(Product::getPrice));
        return productList;
    }

    // Aggregation operations:
    // Total stock (O(n))
    public int totalStock() {
        int total = 0;
        for (Product p : productMap.values()) {
            total += p.getStockQuantity();
        }
        return total;
    }

    // Average price (O(n))
    public double averagePrice() {
        if (productMap.isEmpty()) return 0.0;

        double sum = 0;
        int count = 0;
        for (Product p : productMap.values()) {
            sum += p.getPrice();
            count++;
        }
        return sum / count;
    }

    public void printAll() {
        for (Product p : productMap.values()) {
            System.out.println(p);
        }
    }

    public int size() {
        return productMap.size();
    }
}
