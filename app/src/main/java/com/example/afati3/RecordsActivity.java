package com.example.afati3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class RecordsActivity extends AppCompatActivity {

    private ListView listView;

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        listView = findViewById(R.id.listView);
        databaseManager = new DatabaseManager(this);

        // load via thread
        loadDataInThread();

        // on item long click listener
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(id);
                return true;
            }
        });
    }

    private void showDeleteDialog(long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Item");
        builder.setMessage("Are you sure you want to delete this item?");
        builder.setPositiveButton("Delete", (dialog, which) -> {
            boolean deleteUser = databaseManager.deleteUser((int) id);
            if(deleteUser) {
                Toast.makeText(this, "User deleted successfully!", Toast.LENGTH_LONG).show();
                loadDataInThread();
            }else  {
                Toast.makeText(this, "Failed to delte user!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, whitch) -> dialog.dismiss());
        builder.create().show();
    }


    private void loadDataInThread() {
        // Create a new thread for loading data
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Fetch data in the background
                final Cursor cursor = databaseManager.getAllData();

                // Post back to the main thread to update the UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update the UI (ListView) with the fetched data
                        ItemsAdapter listItemsAdapter = new ItemsAdapter(RecordsActivity.this, cursor);
                        listView.setAdapter(listItemsAdapter);
                    }
                });
            }
        }).start();
    }
}