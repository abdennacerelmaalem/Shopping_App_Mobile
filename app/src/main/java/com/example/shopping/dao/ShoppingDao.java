package com.example.shopping.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.shopping.model.ShoppingList;

import java.util.List;

@Dao
public interface ShoppingDao {

    @Query("Select * From ShoppingList Order By ProductName")
    List<ShoppingList> getAll();

    @Query("Select ProductName From ShoppingList Where Cost == :value ")
    List<String> getProductNameByCost(int value);

    @Query("Select ProductName From ShoppingList Where Count == :value ")
    List<String> getProductNameByCount(int value);

    @Insert
    void insertProduct(ShoppingList shoppingList);

    @Query("Delete From ShoppingList")
    void deleteAllProducts();








}
