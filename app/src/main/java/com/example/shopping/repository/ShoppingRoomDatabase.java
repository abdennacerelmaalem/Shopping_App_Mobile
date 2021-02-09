package com.example.shopping.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.shopping.dao.ShoppingDao;
import com.example.shopping.model.ShoppingList;

@Database(entities = {ShoppingList.class}, version = 2)
public abstract class ShoppingRoomDatabase extends RoomDatabase {
    public abstract ShoppingDao shoppingDao();
}
