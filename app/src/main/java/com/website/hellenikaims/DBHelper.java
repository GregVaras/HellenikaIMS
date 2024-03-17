package com.website.hellenikaims;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // Database Name and Version
    private static final String DATABASE_NAME = "hellenika_database";
    private static final int DATABASE_VERSION = 15;

    // Table creation script
    private static final String CREATE_SUPPLIER_SCRIPT = "CREATE TABLE supplier ("
            + "idSupplier INTEGER PRIMARY KEY, "
            + "nameSupplier TEXT, "
            + "addressSupplier TEXT);";

    private static final String CREATE_INVENTORY_TABLE_SCRIPT = "CREATE TABLE inventory ("
            + "idInventory TEXT, "
            + "itemCodeInventory REAL,"
            + "nameInventory TEXT, "
            + "inQuantityInventory TEXT, "
            + "inMeasureUnitInventory TEXT,"
            + "inCostInventory REAL,"
            + "outQuantityInventory TEXT, "
            + "outMeasureUnitInventory TEXT,"
            + "outCostInventory REAL,"
            + "totalQuantityInventory REAL"
            + ");";


    private static final String CREATE_BATCH_TABLE_SCRIPT = "CREATE TABLE batches ("
            + "batchID TEXT, "
            + "batchCreateDate REAL,"
            + "batchCode REAL,"
            + "batchName TEXT,"
            + "batchQuantity TEXT"
            + ");";


    private static final String CREATE_RECIPES_TABLE_SCRIPT = "CREATE TABLE recipes ("
            + "recipeID TEXT, "
            + "recipeName TEXT"
            + ");";

    // SQL script to insert data
    private static final String INSERT_SUPPLIER_DATA_SCRIPT = "INSERT INTO supplier (idSupplier, nameSupplier, addressSupplier) " +
            "VALUES " +
            "(01, 'Amazon', 'Online'), " +
            "(02, 'Bake Mark USA', '5958 1st Ave S, Seattle, WA 98108'), " +
            "(03, 'Borracchini Foods', '001 S Plum St, Seattle, WA 98144'), " +
            "(04, 'Edaleen Dairy', ''), " +
            "(05, 'Green Mountain Flavors', '442 Treasure Dr, Oswego, IL 60543'), " +
            "(06, 'Hellenika', '1920a Pike Pl, Seattle, WA 98101'), " +
            "(07, 'MarketSpice', '85 Pike St, Seattle, WA 98101'), " +
            "(08, 'Medo Sweet Farms', '915 1st Ave S, Kent, WA 98032'), " +
            "(09, 'Merlino Foods', '4100 4th Ave S, Seattle, WA 98134'), " +
            "(10, 'Nuts.com', 'Online'), " +
            "(11, 'PreGel', 'Online'), " +
            "(12, 'Restaurant Depot', '3670 E Marginal Way S, Seattle, WA 98134'), " +
            "(13, 'Sunny Honey Co', '89 Pike St, Seattle, WA 98101'), " +
            "(14, 'Wild Willis', '');";

    private static final String INSERT_INVENTORY_DATA_SCRIPT = "INSERT INTO inventory (idInventory, itemCodeInventory, nameInventory, inQuantityInventory, inMeasureUnitInventory, inCostInventory, outQuantityInventory, outMeasureUnitInventory, outCostInventory, totalQuantityInventory) " +
            "VALUES " +
            "(1, 'B07FT2XLR9', 'Ube Flavoring', 'L', '', 39.88, '', 'mL', 0.03988, 0), " +
            "(2, 'B078T3RLS3', 'Shredded Coconut', '96 oz', '', 36.99, '', 'g', 0.0136, 0), " +
            "(3, '75919', 'Dextrose', '55 lbs', '', 45.99, '', 'g', 0.00184, 1), " +
            "(4, '360280', 'IQF Marionberries', '30 lbs', '', 119.2, '', 'lbs', 3.973, 0), " +
            "(5, '360520', 'IQF Strawberries', '30 lbs', '', 75.83, '', 'g', 0.00557, 0), " +
            "(6, '360120', 'IQF Blueberries', '30 lbs', '', 52.6, '', 'g', 0.00386, 0), " +
            "(7, '22000', 'Milk', 'gal', '', 3.35, '', 'gal', 3.35, 1), " +
            "(8, '115100', 'Cream', '1/2 gal', '', 8.25, '', 'gal', 16.5, 0), " +
            "(9, '502012', 'Skim Milk Powder', '50 lbs', '', 100.32, '', 'g', 0.004424, 0), " +
            "(10, '', 'Mango Puree', 'Bottle', '', 30, '', 'g', 0.0154, 0), " +
            "(11, '52300', 'Pure Bergamot Extract', '1/2 gallon', '', 64, '', 'mL', 0.0338, 0), " +
            "(12, '53130', 'Natural Champagne Flavor', 'Pint', '', 11.75, '', 'mL', 0.0248, 0), " +
            "(13, '58350', 'Huckleberry Extract', '1/2 gallon', '', 39, '', 'mL', 0.0206, 0), " +
            "(14, 'Lavndrwtr', 'Pure Lavenderwater', 'gal', '', 52, '', 'g', 0.0103, 0), " +
            "(15, '62130', 'Lemon Extract', '1/2 gal', '', 35.5, '', 'g', 0.0188, 0), " +
            "(16, 'Rosewater', 'Pure Rosewater', 'quart', '', 11.25, '', 'mL', 0.0195, 0), " +
            "(17, '69126', 'Natural Strawberry Flavor', 'gal', '', 83, '', 'mL', 0.0219, 0), " +
            "(18, 'AnnattoExt', 'Annatto Extract', 'quart', '', 50, '', 'g', 0.0528, 0), " +
            "(19, '57310', 'Green Color Blend', 'quart', '', 43, '', 'g', 0.0454, 0), " +
            "(20, 'PurCarExt', 'Purple Carrot Extract', 'quart', '', 51, '', 'g', 0.0539, 0), " +
            "(21, 'TurExt', 'Turmeric Extract', 'quart', '', 62, '', 'g', 0.0655, 0), " +
            "(22, 'Citric', 'Citric Acid', 'quart', '', 13.5, '', 'mL', 0.0143, 0), " +
            "(23, 'Malic', 'Malic Acid', 'quart', '', 18.5, '', 'mL', 0.0195, 0), " +
            "(24, 'IceBracer100', 'Stabilizer', '6 lbs', '', 105, '', 'g', 0.0385, 0), " +
            "(25, '53077', 'Toasted Coconut Flavoring', 'quart', '', 22.75, '', 'mL', 0.024, 0), " +
            "(26, '63020', 'Macadamia Flavor Extract', 'half-gallon', '', 38, '', 'mL', 0.002, 0), " +
            "(27, '-', 'Lemon Curd', '114 lbs', '', 161.58, '', 'g', 0.00312, 0), " +
            "(28, '-', 'Marionberry Puree', '15 kg', '', 121.68, '', 'g', 0.008112, 0), " +
            "(29, '-', 'Base', '42 kg', '', 163.267, '', 'g', 0.00389, 0), " +
            "(30, '-', 'Cold Brew Tea', '6 cups', '', 4.63, '', 'mL', 0.00326, 0), " +
            "(31, '-', 'Strawberry Puree', '2160 g', '', 14.37, '', 'g', 0.00665, 0), " +
            "(32, '-', 'Huckleberry Puree', '395 g', '', 8.34, '', 'g', 0.021, 0), " +
            "(33, '550', 'Earl Grey Tea Leaves', 'lb', '', 21, '', 'g', 0.0463, 0), " +
            "(34, '10012', 'Liquid Eggs', '20 lbs', '', 58.793, '', 'lbs', 2.94, 0), " +
            "(35, '3205', 'Strained Yogurt', '11 lbs', '', 44, '', 'g', 0.008811, 0), " +
            "(36, 'CBCACAOPOWDER', 'Cocoa Powder', '6 kg', '', 113.69, '', 'g', 0.0189, 0), " +
            "(37, '', 'Chopped Macadamia Nuts', '25 lbs', '', 353, '', 'g', 0.0311, 0), " +
            "(38, '45972', 'Forest Berries', '6 kg', '', 128.79, '', 'g', 0.0215, 0), " +
            "(39, '45872', 'Strawberry (Paste)', '6 kg', '', 131.12, '', 'g', 0.0219, 0), " +
            "(40, '301098', 'Base Purolat', '16 kg', '', 321.26, '', 'g', 0.0535, 0), " +
            "(41, '301148', 'Totalbase', '16 kg', '', 245.25, '', 'g', 0.040875, 0), " +
            "(42, '1844', 'Superneutro', '8 kg', '', 342.92, '', 'g', 0.042865, 0), " +
            "(43, '307138', 'Yoggi', '16 kg', '', 354.64, '', 'g', 0.02955, 0), " +
            "(44, '50402', 'Coconut (Pregel)', '10 kg', '', 236.65, '', 'g', 0.023665, 0), " +
            "(45, '50802', 'Malaga', '12 kg', '', 269.44, '', 'g', 0.02245, 0), " +
            "(46, '23502', 'Vanilla (Paste)', '6 kg', '', 224.95, '', 'g', 0.0187458, 0), " +
            "(47, '51172', 'Mint (Paste)', '6 kg', '', 130.42, '', 'g', 0.02174, 0), " +
            "(48, '307138', 'Sugar', '25 lbs', '', 20.04, '', 'g', 0.00177, 0), " +
            "(49, '1110032', 'Lemon Juice', '3 gal', '', 40.77, '', 'gal', 13.59, 0), " +
            "(50, '450017', 'Unsalted Butter', '30 lbs', '', 108.43, '', 'lbs', 3.614, 0), " +
            "(51, '-', 'Honey', 'gal', '', 140, '', 'g', 0.02177, 0), " +
            "(52, '-', 'Sweet Clover Honey', '3 lb', '', 30, '', 'g', 0.022, 0), " +
            "(53, '-', 'Huckleberries', '10 lbs', '', 150, '', 'g', 0.033, 0);";

    private static final String INSERT_RECIPES_DATA_SCRIPT = "INSERT INTO recipes (recipeID, recipeName) " +
            "VALUES " +
            "(1, 'Chocolate'), " +
            "(2, 'Ube'), " +
            "(3, 'Caramel'), " +
            "(4, 'Lemon Curd'), " +
            "(5, 'Marionberry'), " +
            "(6, 'Strawberry (Pregel)'), " +
            "(7, 'Mango'), " +
            "(8, 'Hazelnut'), " +
            "(9, 'Mint'), " +
            "(10, 'Lavender Honey'), " +
            "(11, 'Pistachio'), " +
            "(12, 'Matcha'), " +
            "(13, 'Vanilla (Extract)'), " +
            "(14, 'Passionfruit'), " +
            "(15, 'London Fog'), " +
            "(16, 'Marionberry (Puree)'), " +
            "(17, 'Huckleberry'), " +
            "(18, 'Ube (Shredded Coconut)'), " +
            "(19, 'Coffee'), " +
            "(20, 'Rice Pudding'), " +
            "(21, 'Yogurt Base (G10)'), " +
            "(22, 'Rosé'), " +
            "(23, 'Huckleberry Puree'), " +
            "(24, 'Chocolate (For PB)'), " +
            "(25, 'Chocolate Peanut Butter'), " +
            "(26, 'Honey Macadamia'), " +
            "(27, 'Hibiscus Orange'), " +
            "(28, 'Pumpkin Pie Puree'), " +
            "(29, 'Hibiscus Cherry'), " +
            "(30, 'Turkish Delight'), " +
            "(31, 'Yogurt Base (G11)'), " +
            "(32, 'Pumpkin Spice'), " +
            "(33, 'Lemon Curd (Puree)'), " +
            "(34, 'Yogurt Base (G12)'), " +
            "(35, 'Vanilla (Paste)'), " +
            "(36, 'Bananas Foster'), " +
            "(37, 'Key Lime');";

    private static final String INSERT_BATCH_DATA_SCRIPT = "INSERT INTO batches (batchID, batchCreateDate, batchCode, batchName, batchQuantity) " +
            "VALUES " +
            "('BID1001', '2023-10-30 09:00:00', 8472, 'Chocolate Batch', '100 boxes'), " +
            "('BID1002', '2023-10-30 10:00:00', 8473, 'Ube Batch', '120 boxes'), " +
            "('BID1003', '2023-10-30 11:00:00', 8474, 'Caramel Batch', '80 boxes'), " +
            "('BID1004', '2023-10-30 12:00:00', 8475, 'Lemon Curd Batch', '95 boxes'), " +
            "('BID1005', '2023-10-30 13:00:00', 8476, 'Marionberry Batch', '110 boxes'), " +
            "('BID1006', '2023-10-30 14:00:00', 8477, 'Strawberry (Pregel) Batch', '70 boxes'), " +
            "('BID1007', '2023-10-30 15:00:00', 8478, 'Mango Batch', '85 boxes'), " +
            "('BID1008', '2023-10-30 16:00:00', 8479, 'Hazelnut Batch', '75 boxes'), " +
            "('BID1009', '2023-10-30 17:00:00', 8480, 'Mint Batch', '60 boxes'), " +
            "('BID1010', '2023-10-30 18:00:00', 8481, 'Lavender Honey Batch', '90 boxes');";


    /*
    public void insertRecipesData() {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] recipeNames = {
                "Chocolate",
                "Ube",
                "Caramel",
                "Lemon Curd",
                "Marionberry",
                "Strawberry (Pregel)",
                "Mango",
                "Hazelnut",
                "Mint",
                "Lavender Honey",
                "Pistachio",
                "Matcha",
                "Vanilla (Extract)",
                "Passionfruit",
                "London Fog",
                "Marionberry (Puree)",
                "Huckleberry",
                "Ube (Shredded Coconut)",
                "Coffee",
                "Rice Pudding",
                "Yogurt Base (G10)",
                "Rosé",
                "Huckleberry Puree",
                "Chocolate (For PB)",
                "Chocolate Peanut Butter",
                "Honey Macadamia",
                "Hibiscus Orange",
                "Pumpkin Pie Puree",
                "Hibiscus Cherry",
                "Turkish Delight",
                "Yogurt Base (G11)",
                "Pumpkin Spice",
                "Lemon Curd (Puree)",
                "Yogurt Base (G12)",
                "Vanilla (Paste)",
                "Bananas Foster",
                "Key Lime"
        };

        for (String recipeName : recipeNames) {
            ContentValues values = new ContentValues();
            values.put("recipeName", recipeName);

            db.insert("recipes", null, values);
        }

        db.close();
    }

     */



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tables
        db.execSQL(CREATE_SUPPLIER_SCRIPT);
        db.execSQL(CREATE_INVENTORY_TABLE_SCRIPT);
        db.execSQL(CREATE_RECIPES_TABLE_SCRIPT);
        db.execSQL(CREATE_BATCH_TABLE_SCRIPT);

        // Insert initial data
        db.execSQL(INSERT_SUPPLIER_DATA_SCRIPT);
        db.execSQL(INSERT_INVENTORY_DATA_SCRIPT);
        db.execSQL(INSERT_RECIPES_DATA_SCRIPT);
        db.execSQL(INSERT_BATCH_DATA_SCRIPT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If you want to handle database upgrades, do it here
    }


    public List<String[]> getAllSupplierData() {
        List<String[]> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM supplier", null);

        if (cursor.moveToFirst()) {
            do {
                String[] rowData = new String[3]; // Assuming there are 3 columns

                rowData[0] = cursor.getString(cursor.getColumnIndex("idSupplier"));
                rowData[1] = cursor.getString(cursor.getColumnIndex("nameSupplier"));
                rowData[2] = cursor.getString(cursor.getColumnIndex("addressSupplier"));

                dataList.add(rowData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dataList;
    }

    public List<String[]> getAllRecipeData() {
        List<String[]> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recipes", null);

        if (cursor.moveToFirst()) {
            do {
                String[] rowData = new String[3]; // Assuming there are 3 columns

                rowData[0] = cursor.getString(cursor.getColumnIndex("recipeID"));
                rowData[1] = cursor.getString(cursor.getColumnIndex("recipeName"));

                dataList.add(rowData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dataList;
    }

    public List<String[]> getAllBatchData() {
        List<String[]> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM batches", null);

        if (cursor.moveToFirst()) {
            do {
                String[] rowData = new String[5]; // Assuming there are 5 columns

                rowData[0] = cursor.getString(cursor.getColumnIndex("batchID"));
                rowData[1] = cursor.getString(cursor.getColumnIndex("batchCreateDate"));
                rowData[2] = cursor.getString(cursor.getColumnIndex("batchCode"));
                rowData[3] = cursor.getString(cursor.getColumnIndex("batchName"));
                rowData[4] = cursor.getString(cursor.getColumnIndex("batchQuantity"));

                dataList.add(rowData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dataList;
    }


    public List<String[]> getAllInventoryData() {
        List<String[]> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM inventory", null);

        if (cursor.moveToFirst()) {
            do {
                String[] rowData = new String[10]; // Assuming there are 7 columns

                rowData[0] = cursor.getString(cursor.getColumnIndex("idInventory"));
                rowData[1] = cursor.getString(cursor.getColumnIndex("itemCodeInventory"));
                rowData[2] = cursor.getString(cursor.getColumnIndex("nameInventory"));
                rowData[3] = cursor.getString(cursor.getColumnIndex("inQuantityInventory"));
                rowData[4] = cursor.getString(cursor.getColumnIndex("inMeasureUnitInventory"));
                rowData[5] = cursor.getString(cursor.getColumnIndex("inCostInventory"));
                rowData[6] = cursor.getString(cursor.getColumnIndex("outQuantityInventory"));
                rowData[7] = cursor.getString(cursor.getColumnIndex("outMeasureUnitInventory"));
                rowData[8] = cursor.getString(cursor.getColumnIndex("outCostInventory"));
                rowData[9] = cursor.getString(cursor.getColumnIndex("totalQuantityInventory"));

                dataList.add(rowData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dataList;
    }

    public List<String[]> getViewInventoryData() {
        List<String[]> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT idInventory, itemCodeInventory, nameInventory, totalQuantityInventory FROM inventory", null);

        if (cursor.moveToFirst()) {
            do {
                String[] rowData = new String[4]; // Since we're now fetching 4 columns

                rowData[0] = cursor.getString(cursor.getColumnIndex("idInventory"));
                rowData[1] = cursor.getString(cursor.getColumnIndex("itemCodeInventory"));
                rowData[2] = cursor.getString(cursor.getColumnIndex("nameInventory"));
                rowData[3] = cursor.getString(cursor.getColumnIndex("totalQuantityInventory"));

                dataList.add(rowData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return dataList;
    }

    public String[] getInventoryItem(String itemCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM inventory WHERE itemCodeInventory = ?", new String[]{itemCode});

        String[] item = null;

        if (cursor.moveToFirst()) {
            item = new String[4];
            item[0] = cursor.getString(cursor.getColumnIndex("idInventory"));
            item[1] = cursor.getString(cursor.getColumnIndex("itemCodeInventory"));
            item[2] = cursor.getString(cursor.getColumnIndex("nameInventory"));
            item[3] = "1"; //cursor.getString(cursor.getColumnIndex("totalQuantityInventory"));
        }

        cursor.close();
        db.close();

        return item;
    }

    public void updateItemQuantity(String itemCode, int newQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("totalQuantityInventory", newQuantity);

        String whereClause = "itemCodeInventory" + " = ?";
        String[] whereArgs = { itemCode };

        db.update("inventory", values, whereClause, whereArgs);

        db.close();
    }

    public void updateInventory(List<String[]> inventoryData) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (String[] item : inventoryData) {
            ContentValues values = new ContentValues();
            values.put("inQuantityInventory", item[3]);
            values.put("totalQuantityInventory", item[3]);
            db.update("inventory", values, "idInventory = ?", new String[]{item[0]});
        }

        db.close();
    }

    /*
    public void updateInventoryTotalCount(List<String[]> tableData) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (String[] rowData : tableData) {
            String itemCode = rowData[1]; // Assuming itemCode is in the second position

            // Get the current total count from the database
            int currentTotalCount = 0;
            Cursor cursor = db.rawQuery("SELECT totalQuantityInventory FROM inventory WHERE itemCodeInventory = ?", new String[]{itemCode});
            if (cursor.moveToFirst()) {
                currentTotalCount = cursor.getInt(0);
            }
            cursor.close();

            // Update the total count with the new quantity entered by the user
            int newQuantity = Integer.parseInt(rowData[3]); // Assuming quantity is in the fourth position
            int updatedTotalCount = currentTotalCount + newQuantity;

            // Update the total count in the database
            ContentValues values = new ContentValues();
            values.put("totalQuantityInventory", updatedTotalCount);

            String whereClause = "itemCodeInventory" + " = ?";
            String[] whereArgs = { itemCode };

            db.update("inventory", values, whereClause, whereArgs);
        }

        db.close();
    }*/

    public void updateInventoryTotalCount(String itemCode, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Get the current total count from the database
        int currentTotalCount = 0;
        Cursor cursor = db.rawQuery("SELECT totalQuantityInventory FROM inventory WHERE itemCodeInventory = ?", new String[]{itemCode});
        if (cursor.moveToFirst()) {
            currentTotalCount = cursor.getInt(0);
        }
        cursor.close();

        // Update the total count with the new quantity entered by the user
        int updatedTotalCount = currentTotalCount + quantity;

        // Update the total count in the database
        ContentValues values = new ContentValues();
        values.put("totalQuantityInventory", updatedTotalCount);

        String whereClause = "itemCodeInventory" + " = ?";
        String[] whereArgs = {itemCode};

        db.update("inventory", values, whereClause, whereArgs);

        db.close();
    }



}

