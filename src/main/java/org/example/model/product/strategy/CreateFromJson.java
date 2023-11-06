package org.example.model.product.strategy;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.File;
import java.io.IOException;
import org.example.model.product.Product;

public class CreateFromJson implements CreateStrategy {

    private final String jsonFilePath;

    public CreateFromJson(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    @Override
    public Product howToCreateProduct() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Add the custom deserializer
        mapper.registerModule(new SimpleModule().addDeserializer(Product.class, new ProductDeserializer()));

        try {
            // Read the JSON file and map it to the Product class using the custom deserializer
            return mapper.readValue(new File(jsonFilePath), Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class ProductDeserializer extends JsonDeserializer<Product> {

        @Override
        public Product deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            ObjectMapper mapper = (ObjectMapper) jp.getCodec();
            JsonNode node = mapper.readTree(jp);

            Product.Builder builder = new Product.Builder(
                    node.get("productId").asText(),
                    node.get("productPrice").asDouble(),
                    node.get("productName").asText()
            );

            if (node.hasNonNull("productDescription")) {
                builder.productDescription(node.get("productDescription").asText());
            }
            if (node.hasNonNull("productLength")) {
                builder.productLength(node.get("productLength").asDouble());
            }
            if (node.hasNonNull("productWidth")) {
                builder.productWidth(node.get("productWidth").asDouble());
            }
            if (node.hasNonNull("productWeight")) {
                builder.productWeight(node.get("productWeight").asDouble());
            }

            return builder.build();
        }
    }
}
