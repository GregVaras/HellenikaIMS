package com.website.hellenikaims;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ReceiveInventory extends AppCompatActivity {
    private TableLayout tableLayout;
    private EditText itemCodeEditText;
    private DBHelper DBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiveinventory);

        tableLayout = findViewById(R.id.tableLayout);
        itemCodeEditText = findViewById(R.id.itemCodeTextView);
        Button addItemButton = findViewById(R.id.addItemButton);
        Button submitButton = findViewById(R.id.SubmitButtonReceiveInventory);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToTable();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTableData();
            }
        });

        DBHelper = new DBHelper(this);
    }

    private void addItemToTable() {

        String itemCode = itemCodeEditText.getText().toString();

        String[] itemDetails = DBHelper.getInventoryItem(itemCode);

        if (itemDetails != null) {
            boolean itemExists = false;
            int rowCount = tableLayout.getChildCount();

            for (int i = 0; i < rowCount; i++) {

                TableRow row = (TableRow) tableLayout.getChildAt(i);
                TextView codeTextView = (TextView) row.getChildAt(1);
                String code = codeTextView.getText().toString();

                if (code.equals(itemCode)) {
                    // Item with the same code is already in the table
                    // Increment quantity
                    TextView quantityTextView = (TextView) row.getChildAt(3);
                    int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
                    quantityTextView.setText(String.valueOf(currentQuantity + 1));
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                // Item is not in the table, set quantity to 1 and add a new row
                TableRow newRow = new TableRow(this);

                for (int i = 0; i < itemDetails.length; i++) {
                    TextView detailTextView = new TextView(this);
                    detailTextView.setText(itemDetails[i]);
                    newRow.addView(detailTextView);
                }


                tableLayout.addView(newRow);
            }
        } else {
            Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show();
        }
    }


/*
    private void submitTableData() {
        int rowCount = tableLayout.getChildCount();
        List<String[]> tableData = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);

            // Extract data from the row and add to the list
            TextView idTextView = (TextView) row.getChildAt(0);
            TextView codeTextView = (TextView) row.getChildAt(1);
            TextView nameTextView = (TextView) row.getChildAt(2);
            TextView quantityTextView = (TextView) row.getChildAt(3);

            String id = idTextView.getText().toString();
            String code = codeTextView.getText().toString();
            String name = nameTextView.getText().toString();
            int quantity = Integer.parseInt(quantityTextView.getText().toString());

            tableData.add(new String[]{id, code, name, String.valueOf(quantity)});
        }

        // Call the new method to update total count in the inventory table
        DBHelper.updateInventoryTotalCount(tableData);
    }

    private void submitTableData() {
        int rowCount = tableLayout.getChildCount();

        for (int i = 0; i < rowCount; i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);

            // Extract data from the row and update the database
            TextView idTextView = (TextView) row.getChildAt(0);
            TextView codeTextView = (TextView) row.getChildAt(1);
            TextView nameTextView = (TextView) row.getChildAt(2);
            TextView quantityTextView = (TextView) row.getChildAt(3);

            String id = idTextView.getText().toString();
            String code = codeTextView.getText().toString();
            String name = nameTextView.getText().toString();

            // Check if quantityTextView contains the default text "Quantity"
            String quantityText = quantityTextView.getText().toString();
            if ("Quantity".equals(quantityText)) {
                // Handle default text case, maybe show an error message
                Toast.makeText(this, "Invalid quantity for item: " + code, Toast.LENGTH_SHORT).show();
                continue; // Skip this row and move to the next one
            }

            try {
                int quantity = Integer.parseInt(quantityText);
                DBHelper.updateItemQuantity(code, quantity);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the exception or log an error message
                Toast.makeText(this, "Error parsing quantity for item: " + code, Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    private void submitTableData() {
        int rowCount = tableLayout.getChildCount();

        for (int i = 0; i < rowCount; i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);

            // Extract data from the row and update the database
            TextView idTextView = (TextView) row.getChildAt(0);
            TextView codeTextView = (TextView) row.getChildAt(1);
            TextView nameTextView = (TextView) row.getChildAt(2);
            TextView quantityTextView = (TextView) row.getChildAt(3);

            String id = idTextView.getText().toString();
            String code = codeTextView.getText().toString();
            String name = nameTextView.getText().toString();
            String quantityText = quantityTextView.getText().toString();

            System.out.println("Number in quantity colmun " + quantityText);

            // Check if quantityText is a valid integer representation
            try {
                int quantity = Integer.parseInt(quantityText);
                DBHelper.updateInventoryTotalCount(code, quantity);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the exception or log an error message
                Toast.makeText(this, "Invalid quantity for item: " + code, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
