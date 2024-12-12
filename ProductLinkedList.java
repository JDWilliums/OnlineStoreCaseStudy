public class ProductLinkedList {
    private class Node {
        Product product;
        Node next;

        public Node(Product product) {
            this.product = product;
        }
    }

    private Node head;
    private int size;

    public void insert(Product product) {
        // Insert at the head for simplicity; O(1) operation.
        Node newNode = new Node(product);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Search by ID
    public Product searchById(int id) {
        Node current = head;
        while (current != null) {
            if (current.product.getId() == id) {
                return current.product;
            }
            current = current.next;
        }
        return null; // not found
    }

    // Search by product name
    public Product searchByName(String name) {
        Node current = head;
        while (current != null) {
            if (current.product.getProductName().equalsIgnoreCase(name)) {
                return current.product;
            }
            current = current.next;
        }
        return null;
    }

    // Delete a product by ID
    public boolean deleteById(int id) {
        if (head == null) return false;

        // If head is the node to delete
        if (head.product.getId() == id) {
            head = head.next;
            size--;
            return true;
        }

        // Otherwise, find the node before the one to delete
        Node current = head;
        while (current.next != null && current.next.product.getId() != id) {
            current = current.next;
        }

        if (current.next == null) {
            // Not found
            return false;
        } else {
            // current.next is to be deleted
            current.next = current.next.next;
            size--;
            return true;
        }
    }

    public int getSize() {
        return size;
    }

    // Merge sort wrapper
    public void sortByPrice() {
        head = mergeSort(head);
    }

    private Node mergeSort(Node h) {
        if (h == null || h.next == null) {
            return h;
        }

        Node middle = getMiddle(h);
        Node nextOfMiddle = middle.next;
        middle.next = null;

        Node left = mergeSort(h);
        Node right = mergeSort(nextOfMiddle);

        return sortedMerge(left, right);
    }

    private Node sortedMerge(Node a, Node b) {
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.product.getPrice() <= b.product.getPrice()) {
            a.next = sortedMerge(a.next, b);
            return a;
        } else {
            b.next = sortedMerge(a, b.next);
            return b;
        }
    }

    private Node getMiddle(Node h) {
        if (h == null) return h;
        Node slow = h, fast = h;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Aggregation example: total stock
    public int totalStock() {
        int total = 0;
        Node current = head;
        while (current != null) {
            total += current.product.getStockQuantity();
            current = current.next;
        }
        return total;
    }

    // Another Aggregation example: average price
    public double averagePrice() {
        if (head == null) return 0.0;
        double sum = 0;
        int count = 0;
        Node current = head;
        while (current != null) {
            sum += current.product.getPrice();
            count++;
            current = current.next;
        }
        return sum / count;
    }

    public void printAll() {
        Node current = head;
        while (current != null) {
            System.out.println(current.product);
            current = current.next;
        }
    }
}
