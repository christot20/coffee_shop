package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity class for selecting donut options and adding them to the order.
 * @author Thomas Christo, Aditya Pokale
 */
public class DonutSelectedActivity extends AppCompatActivity {

    private Donut donut;
    private Spinner spinnerQuantity;
    private Button btnAddToOrder;

    /**
     * This method is called when the activity is starting. It initializes the donut object, views, spinners, and add to order button, and updates the donut price.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_selected);

        // Initialize donut object
        initDonut();

        // Initialize views
        initViews();

        // Initialize spinners
        initSpinners();

        // Handle add to order button click
        initAddToOrderButton();

        // Update donut price when page is initialized
        updateDonutPrice();
    }

    /**
     * This method retrieves the selected donut from the intent and initializes the donut object based on the selected item.
     */
    private void initDonut() {
        // Retrieve selected donut from intent
        Intent intent = getIntent();
        String selectedItem = intent.getStringExtra("ITEM");
        // Initialize donut object based on selected item
        DonutType selectedDonutType = DonutType.valueOf(selectedItem);
        donut = new Donut(selectedDonutType);
    }

    /**
     * This method initializes the views.
     */
    private void initViews() {
        spinnerQuantity = findViewById(R.id.spinner_quantity);
        btnAddToOrder = findViewById(R.id.btn_add_to_order);
    }

    /**
     * This method initializes the spinners.
     */
    private void initSpinners() {
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getQuantityList());
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuantity.setAdapter(quantityAdapter);
        spinnerQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update donut quantity based on user selection
                int selectedQuantity = (int) parent.getItemAtPosition(position);
                donut.setQuantity(selectedQuantity);
                updateDonutPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    /**
     * This method handles the add to order button click.
     */
    private void initAddToOrderButton() {
        btnAddToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the selected donut to the order
                GlobalData.getInstance().getCurrentOrder().getMenuItems().add(donut);
                Toast.makeText(DonutSelectedActivity.this, "Donut successfully added to the order", Toast.LENGTH_SHORT).show();
                // Reset the page to its default state
                resetPageToDefault();
                // Finish the activity
                finish();
            }
        });
    }

    /**
     * This method resets the page to its default state.
     */
    private void resetPageToDefault() {
        // Initialize a new Donut object with default values
        initDonut();

        // Update the displayed price to reflect the default Donut
        updateDonutPrice();

        // Reset the quantity spinner to the first item
        spinnerQuantity.setSelection(0);
    }

    /**
     * This method defines the list of quantities to display in the spinner.
     * @return A list of quantities to display in the spinner.
     */
    private List<Integer> getQuantityList() {
        // Define the list of quantities to display in the spinner
        List<Integer> quantities = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            quantities.add(i);
        }
        return quantities;
    }

    /**
     * Updates the donut price displayed according to selection.
     */
    private void updateDonutPrice() {
        TextView tvPrice = findViewById(R.id.tv_price);
        tvPrice.setText(String.format("$%.2f", donut.price()));
    }
}