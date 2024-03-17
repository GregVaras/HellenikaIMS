/*package com.website.hellenikaims;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class InventoryAdapter {

    private List<String[]> data;
    private TableLayout itemTable;

    public InventoryAdapter(List<String[]> data, TableLayout itemTable) {
        this.data = data;
        this.itemTable = itemTable;
    }

    public void addItemToTable(String[] item) {
        TableRow row = new TableRow(itemTable.getContext());

        TextView itemCodeTextView = new TextView(itemTable.getContext());
        itemCodeTextView.setText(item[1]);

        TextView nameTextView = new TextView(itemTable.getContext());
        nameTextView.setText(item[2]);

        TextView quantityTextView = new TextView(itemTable.getContext());
        quantityTextView.setText(item[3]);

        row.addView(itemCodeTextView);
        row.addView(nameTextView);
        row.addView(quantityTextView);

        itemTable.addView(row);
    }
}

 */
