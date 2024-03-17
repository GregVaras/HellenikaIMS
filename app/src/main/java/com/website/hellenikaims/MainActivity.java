package com.website.hellenikaims;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        //Connection connection = MySQLDatabaseHelper.getConnection();

        DBHelper dbHelper = new DBHelper(this);

        // Get a writable database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

    }

    public void onReceiveInventoryClick(View view) {
        Intent intent = new Intent(this, ReceiveInventory.class);
        startActivity(intent);
    }

    public void onViewInventoryClick(View view) {
        Intent intent = new Intent(this, ViewInventory.class);
        startActivity(intent);
    }

    public void onCreateBatchClick(View view){
        Intent intent = new Intent(this, CreateBatch.class);
        startActivity(intent);
    }

    public void onViewBatchesCLick(View view){
        Intent intent = new Intent(this, ViewBatch.class);
        startActivity(intent);
    }

    public void onNewRecipesClick(View view){
        Intent intent = new Intent(this, NewRecipes.class);
        startActivity(intent);
    }

    public void onViewRecipesClick(View view) {
        Intent intent = new Intent(this, ViewRecipes.class);
        startActivity(intent);
    }
    public void onSupplierClick(View view) {
        Intent intent = new Intent(this, Suppliers.class);
        startActivity(intent);
    }
    public void onReportsClick(View view) {
        Intent intent = new Intent(this, Reports.class);
        startActivity(intent);
    }
}