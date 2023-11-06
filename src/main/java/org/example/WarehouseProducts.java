package org.example;


public class WarehouseProducts {

    private Warehouse warehouse;
    private int productId;
    private int quantity;

    public WarehouseProducts(Warehouse warehouse, int productId, int quantity) {
        this.warehouse = warehouse;
        this.productId = productId;
        this.quantity = quantity;
    }

}
