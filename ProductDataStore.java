import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDataStore {
    private HashMap<Integer, Product> productMap;

    public ProductDataStore() {
        productMap = new HashMap<>();
    }

    // Insertion O(1)
    public void insert(Product product) {
        productMap.put(product.getId(), product);
    }

    // Search by ID O(1)
    public Product searchById(int id) {
        return productMap.get(id);
    }

    // Search by Name O(n)
    public Product searchByName(String name) {
        for (Product p : productMap.values()) {
            if (p.getProductName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // Deletion by ID O(1)
    public boolean deleteById(int id) {
        return productMap.remove(id) != null;
    }

    // Sorting by Category (alphabetical order) using mergesort algorithm
    public List<Product> sortByCategory() {
        List<Product> productList = new ArrayList<>(productMap.values());
        mergeSort(productList, 0, productList.size() - 1);
        return productList;
    }

    // Aggregation operations:
    // Total stock O(n)
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
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }
    

    public int size() {
        return productMap.size();
    }


    // MergeSort implementation for category
    private void mergeSort(List<Product> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private void merge(List<Product> list, int left, int mid, int right) {
        // Create the two temporary lists
        int sizeLeft = mid - left + 1;
        int sizeRight = right - mid;

        List<Product> leftList = new ArrayList<>(sizeLeft);
        List<Product> rightList = new ArrayList<>(sizeRight);

        for (int i = 0; i < sizeLeft; i++) {
            leftList.add(list.get(left + i));
        }
        for (int j = 0; j < sizeRight; j++) {
            rightList.add(list.get(mid + 1 + j));
        }

        // Merge the temporary lists back into the main list
        int i = 0; // Initial index of left sublist
        int j = 0; // Initial index of right sublist
        int k = left; // Initial index of merged sublist

        while (i < sizeLeft && j < sizeRight) {
            // Compare categories alphabetically
            String leftCategory = leftList.get(i).getCategory();
            String rightCategory = rightList.get(j).getCategory();
            if (leftCategory.compareToIgnoreCase(rightCategory) <= 0) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftList if any
        while (i < sizeLeft) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        // and rightlist if any
        while (j < sizeRight) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}
