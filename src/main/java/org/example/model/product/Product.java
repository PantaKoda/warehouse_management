package org.example.model.product;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public final class Product {

    private final String productId;
    private final double productPrice;
    private final String productName;
    private final String productDescription;
    private final double productLength;
    private final double productWidth;
    private final double productWeight;
    private final Instant productCreationTimestampUTC;

    private Product(Builder builder) {
        productId = builder.productId;
        productPrice = builder.productPrice;
        productName = builder.productName;
        productDescription = builder.productDescription;
        productLength = builder.productLength;
        productWidth = builder.productWidth;
        productWeight = builder.productWeight;
        productCreationTimestampUTC = builder.productCreationTimestampUTC;
    }

    public static final class Builder {
        private final String productId;
        private final double productPrice;
        private final String productName;
        //optional
        private String productDescription;
        private double productLength;
        private double productWidth;
        private double productWeight;
        private final Instant productCreationTimestampUTC;

        /**
         * Creates a new Builder instance for the Product class.
         *
         * @param productId    Unique identifier for the product.
         * @param productPrice Price of the product. Must be non-negative.
         * @param productName  Name of the product.
         * @throws IllegalArgumentException if any of the parameters are invalid entries.
         */
        public Builder(String productId, double productPrice, String productName) {
            if (productId == null || productId.trim().isEmpty()) {
                throw new IllegalArgumentException("Product ID cannot be null or empty");
            }
            if (productPrice < 0) {
                throw new IllegalArgumentException("Product price cannot be negative");
            }
            if (productName == null || productName.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be null or empty");
            }

            this.productId = productId;
            this.productPrice = productPrice;
            this.productName = productName;
            this.productCreationTimestampUTC = Instant.now();

        }


        public Builder productDescription(String val) {

            productDescription = val;
            return this;
        }

        public Builder productLength(double val) {
            if (val < 0) {
                throw new IllegalArgumentException("Product length cannot be negative");
            }
            productLength = val;
            return this;
        }

        public Builder productWidth(double val) {
            if (val < 0) {
                throw new IllegalArgumentException("Product width cannot be negative");
            }
            productWidth = val;
            return this;
        }

        public Builder productWeight(double val) {
            if (val < 0) {
                throw new IllegalArgumentException("Product weight cannot be negative");
            }
            productWeight = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public String getProductId() {
        return productId;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductLength() {
        return productLength;
    }

    public double getProductWidth() {
        return productWidth;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public String getProductCreationTimestampUTC() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return formatter.format(productCreationTimestampUTC);
    }
}
