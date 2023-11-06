package org.example;

import java.util.List;


//Implements Builder
public class Warehouse {

    private final String warehouseName;
    private final String warehouseId;
    private final String warehouseAddress;
    private final String warehouseZipcode;
    private final String warehouseCity;
    private final String warehouseCountry;

    private Warehouse(Builder builder) {
        warehouseName = builder.warehouseName;
        warehouseId = builder.warehouseId;
        warehouseAddress = builder.warehouseAddress;
        warehouseZipcode = builder.warehouseZipcode;
        warehouseCity = builder.warehouseCity;
        warehouseCountry = builder.warehouseCountry;
    }


    public static final class Builder {
        private final String warehouseName;
        private final String warehouseId;
        private String warehouseAddress;
        private String warehouseZipcode;
        private String warehouseCity;
        private String warehouseCountry;

        public Builder(String warehouseName, String warehouseId) {
            this.warehouseName = warehouseName;
            this.warehouseId = warehouseId;
        }

        public Builder warehouseAddress(String val) {
            warehouseAddress = val;
            return this;
        }

        public Builder warehouseZipcode(String val) {
            warehouseZipcode = val;
            return this;
        }

        public Builder warehouseCity(String val) {
            warehouseCity = val;
            return this;
        }

        public Builder warehouseCountry(String val) {
            warehouseCountry = val;
            return this;
        }

        public Warehouse build() {
            return new Warehouse(this);
        }
    }
}
