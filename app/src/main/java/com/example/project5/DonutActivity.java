package com.example.project5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Activity for displaying a list of donut items.
 * @author Thomas Christo
 */

public class DonutActivity extends AppCompatActivity {
    private ArrayList<Donut> donuts = new ArrayList<>();

    /**
     * Initializes the activity and sets up the RecyclerView to display donut items.
     * @param savedInstanceState The saved instance state Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems();
        DonutsAdapter adapter = new DonutsAdapter(this, donuts);
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        for (DonutType type : DonutType.values()) {
            donuts.add(new Donut(type));
        }
    }
}
