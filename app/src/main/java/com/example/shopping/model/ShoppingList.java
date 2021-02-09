package com.example.shopping.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class ShoppingList {

    @PrimaryKey(autoGenerate = true)
    private int productId;

    @NonNull
    @ColumnInfo(name = "ProductName")
    private String product;

    @NonNull
    @ColumnInfo(name = "Count")
    private int count;

    @NonNull
    @ColumnInfo(name = "Cost")
    private int cost;

    public ShoppingList(String product, int count, int cost) {
        this.product = product;
        this.count = count;
        this.cost = cost;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return product + "," + count + "," + cost +"€";
    }

}
