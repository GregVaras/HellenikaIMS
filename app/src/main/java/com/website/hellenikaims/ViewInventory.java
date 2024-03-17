package com.website.hellenikaims;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.website.hellenikaims.DBHelper;

import java.util.List;

public class ViewInventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinventory);

        DBHelper dbHelper;
        dbHelper = new DBHelper(this);
        List<String[]> data = dbHelper.getViewInventoryData();

       TableLayout tableLayout = findViewById(R.id.tableLayout);

        for (String[] rowData : data) {
            TableRow row = new TableRow(this);

            for (String value : rowData) {
                TextView textView = new TextView(this);
                textView.setText(value);
                textView.setPadding(8, 8, 8, 8);
                row.addView(textView);
            }

           tableLayout.addView(row);
        }
    }

    public void onAddButtonViewInventory(View view) {
        Intent intent = new Intent(this, ReceiveInventory.class);
        startActivity(intent);
    }
}
