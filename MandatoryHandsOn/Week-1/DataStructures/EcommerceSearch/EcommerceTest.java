import java.util.Arrays;
import java.util.Comparator;

public class EcommerceTest {
    public static void main(String[] args) {
        Product[] products = {
            new Product(3, "Camera",  "Electronics"),
            new Product(1, "Laptop",  "Electronics"),
            new Product(5, "Monitor", "Electronics"),
            new Product(2, "Mouse",   "Accessories"),
            new Product(4, "Tablet",  "Electronics")
        };

        System.out.println("=== Linear Search ===");
        Product result1 = SearchAlgorithms.linearSearch(products, "Mouse");
        System.out.println(result1 != null ? result1 : "Not found");

        Arrays.sort(products, Comparator.comparing(Product::getProductName));

        System.out.println("\n=== Binary Search ===");
        Product result2 = SearchAlgorithms.binarySearch(products, "Monitor");
        System.out.println(result2 != null ? result2 : "Not found");

        System.out.println("\n=== Search for non-existing product ===");
        Product result3 = SearchAlgorithms.binarySearch(products, "Headphones");
        System.out.println(result3 != null ? result3 : "Not found");
    }
}
