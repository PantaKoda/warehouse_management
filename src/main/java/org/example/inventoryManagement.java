package org.example;


import org.example.database.DatabaseConnection;
import org.example.database.TableManager;
import org.example.database.productDAOImpl;
import org.example.model.product.Product;
import org.example.model.product.strategy.CreateFromConsole;
import org.example.model.product.strategy.CreateFromJson;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class inventoryManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static Connection conn = DatabaseConnection.getInstance().getConnection();


    public static void main(String[] args) {

        TableManager tableManager = new TableManager(conn);
        tableManager.createTables();

        int selection;
        do {
            selection = mainMenu();
            try {
                handleMenuSelection(selection);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } while (selection != 0);

        scanner.close();

    }

    // Show main meu and return users selection
    private static int mainMenu() {
        // CRUD: Create, Read, Update, Delete

        while (true) { // Show menu until user selects a number
            try { // Try so we can catch invalid inputs (non integer values)
                printMainMenu();

                return getMenuSelection();
            } catch (NumberFormatException e) { // If invalid input, print error
                System.out.println("Invalid menu selection, please try again");
            }
        }
    }

    private static int getMenuSelection() throws NumberFormatException {
        // Get user input and convert to int

        String input = scanner.nextLine();

        // Throws error if invalid input

        try {
            return Integer.parseInt(input.trim()); // Trim the input to remove any leading/trailing whitespace
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid menu selection. Please enter a number.");
        }
    }

    private static void printMainMenu() {
        // Prints main menu
        System.out.print("\n### Main Menu ###\n" +
                "1. Show product catalogue\n" +
                "2. Show warehouses data\n" +
                "3. Add products\n" +
                "4. Add warehouses\n" +
                "5. Show product availability\n" +
                "6. Transfer products between warehouses\n" +
                "0. Exit\n" +
                "Enter selection: ");
    }

    private static void handleMenuSelection(int selection) throws SQLException {
        switch (selection) {
            case 0:
                System.out.println("Exiting the application...");
                scanner.close();
                if (conn != null) {
                    conn.close();
                }
                System.exit(0);
                break;
            case 1:
                var printProducts = new productDAOImpl();
                printProducts.getAll(conn);

            case 3:
                List<Product> newProducts = addProduct();
                if (!newProducts.isEmpty()) {
                    System.out.println("Do you want to save these products to the database? (yes/no)");
                    String input = scanner.nextLine();
                    if ("yes".equalsIgnoreCase(input)) {
                        var saveProductsToDb = new productDAOImpl();
                        saveProductsToDb.saveToDB(conn, newProducts);

                        System.out.println("Products have been saved.");
                    }
                }
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }
    }


    private static List<Product> addProduct() {
        List<Product> products = new ArrayList<>();
        while (true) {
            System.out.println("Create product from console(1) or reading json file(2), exit(0) ? ");
            int input = Integer.parseInt(scanner.nextLine());

            if (input == 1) {
                CreateFromConsole newProductCreator = new CreateFromConsole();
                Product newProduct = newProductCreator.howToCreateProduct();
                products.add(newProduct);
                System.out.println("New product is cached.");
            } else if (input == 2){ CreateFromJson jsonProductCreator = new CreateFromJson("TestProducts.json");
            Product newProduct = jsonProductCreator.howToCreateProduct();
            if (newProduct != null) {
                products.add(newProduct);
                System.out.println("New product created from JSON is cached.");
            } else {
                System.out.println("Failed to create product from JSON.");
            }
        }  else if (input == 0) {
                break;
            } else {
                System.out.println("Invalid input, try again.");
            }
        }
        return products;
    }
}

