package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.shopping.dao.ShoppingDao;
import com.example.shopping.model.ShoppingList;
import com.example.shopping.repository.ShoppingRoomDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import static android.R.layout.simple_list_item_1;

public class MainActivity extends AppCompatActivity {


    private ArrayAdapter<String> adapter;
    List<String> listString = new ArrayList<>();
    List<ShoppingList> list = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);
        adapter =  new ArrayAdapter<>(getApplicationContext(), simple_list_item_1,listString);

        // AsyncTask with database
        class DatabaseTask extends AsyncTask<ShoppingList, Void, List<ShoppingList> > {
            @Override
            protected List<ShoppingList>  doInBackground(ShoppingList... orders) {

                ShoppingRoomDatabase shoppingDatabase ;
                ShoppingDao shoppingDao;

                shoppingDatabase = Room.databaseBuilder(getApplicationContext(),
                        ShoppingRoomDatabase.class, "database-shopping")
                        .build();

                shoppingDao = shoppingDatabase.shoppingDao();

                shoppingDao.deleteAllProducts();

                for (ShoppingList order : orders) {
                    shoppingDao.insertProduct(order);
                }

                list = shoppingDatabase.shoppingDao().getAll();

                return list;
            }

            @Override
            protected void onPostExecute(List<ShoppingList> list) {
                super.onPostExecute(list);

                listString.clear();
                listString = list.stream()
                        .map(order  -> order.toString())
                        .collect(Collectors.toList());

                adapter =  new ArrayAdapter<>(getApplicationContext(), simple_list_item_1,listString);
                listView.setAdapter(adapter);


            }
        };
        DatabaseTask task = new DatabaseTask();

        ShoppingList order1 = new ShoppingList("mac pro", 1, 600);
        ShoppingList order2 = new ShoppingList("T-shirt", 3, 30);
        ShoppingList order3 = new ShoppingList("Milk", 6, 7);
        ShoppingList order4 = new ShoppingList("book", 1, 20);
        ShoppingList order5 = new ShoppingList("water", 3, 5);

        task.execute(order1,order2,order3,order4,order5);
    }
}