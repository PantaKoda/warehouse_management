package org.example.model.product.strategy;
import org.example.model.product.Product;
import java.util.Scanner;

public class CreateFromConsole implements CreateStrategy {

    @Override
    public  Product howToCreateProduct() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product id: ");
        String productId = scanner.nextLine();

        System.out.println("Enter Product Price: ");
        double productPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter product name: ");
        String productName = scanner.nextLine();

        // Ask for optional parameters and handle empty input
        System.out.println("Enter product description (press enter to skip): ");
        String productDescription = scanner.nextLine();

        System.out.println("Enter product length (press enter to skip): ");
        String lengthInput = scanner.nextLine();
        Double productLength = lengthInput.isEmpty() ? null : Double.parseDouble(lengthInput);

        System.out.println("Enter product width (press enter to skip): ");
        String widthInput = scanner.nextLine();
        Double productWidth = widthInput.isEmpty() ? null : Double.parseDouble(widthInput);

        System.out.println("Enter product weight (press enter to skip): ");
        String weightInput = scanner.nextLine();
        Double productWeight = weightInput.isEmpty() ? null : Double.parseDouble(weightInput);

        Product.Builder builder = new Product.Builder(productId, productPrice, productName);

        // Set optional parameters if they were provided
        if (productDescription != null && !productDescription.trim().isEmpty()) {
            builder.productDescription(productDescription);
        }
        if (productLength != null && productLength > 0.0) {
            builder.productLength(productLength);
        }
        if (productWidth != null && productWidth > 0.0) {
            builder.productWidth(productWidth);
        }
        if (productWeight != null && productWeight > 0.0) {
            builder.productWeight(productWeight);
        }

        Product product = builder.build();

        return product;
    }
}
