package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The main activity of the application responsible for displaying the main menu options.
 * @author Thomas Christo
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Initializes the main activity layout and sets up click listeners for menu options.
     * @param savedInstanceState The saved instance state Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView donutView = findViewById(R.id.donut);
        ImageView coffeeView = findViewById(R.id.coffee);
        ImageView sandwichView = findViewById(R.id.sandwich);
        ImageView currentOrderView = findViewById(R.id.currentOrder);
        ImageView ordersView = findViewById(R.id.orders);

        donutView.setOnClickListener(this::openDonutView);
        coffeeView.setOnClickListener(this::openCoffeeView);
        sandwichView.setOnClickListener(this::openSandwichView);
        currentOrderView.setOnClickListener(this::openCurrentOrderView);
        ordersView.setOnClickListener(this::openOrdersView);
    }

    /**
     * Opens the DonutActivity when Donut is clicked on the main page.
     * @param v The View that was clicked.
     */
    public void openDonutView(View v) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the CoffeeActivity when Coffee is clicked on the main page.
     * @param v The View that was clicked.
     */
    public void openCoffeeView(View v) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the SandwichActivity when Sandwich is clicked on the main page.
     * @param v The View that was clicked.
     */
    public void openSandwichView(View v) {
        Intent intent = new Intent(this, SandwichActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the CurrentOrderActivity when current order button is clicked on the main page.
     * @param v The View that was clicked.
     */
    public void openCurrentOrderView(View v) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the OrdersActivity when the corresponding Orders button is clicked on the main page.
     * @param v The View that was clicked.
     */
    public void openOrdersView(View v) {
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }
}
