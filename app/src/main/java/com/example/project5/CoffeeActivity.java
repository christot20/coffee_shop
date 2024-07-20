package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Activity class for selecting coffee options and adding them to the order.
 * @author Thomas Christo
 */
public class CoffeeActivity extends AppCompatActivity {
    private Coffee coffee;
    private CheckBox mochaCheckBox;
    private CheckBox caramelCheckBox;
    private CheckBox frenchVanillaCheckBox;
    private CheckBox irishCreamCheckBox;
    private CheckBox sweetCreamCheckBox;

    /**
     * Initializes the activity layout and sets up the coffee selection UI components.
     * @param savedInstanceState The saved instance state Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_coffee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize coffee object
        initCoffee();

        // Initialize spinners and checkboxes and button
        initSpinners();
        initCheckBoxes();
        initAddToOrderButton();

        // Update coffee price when page is initialized
        updateCoffeePrice();
    }

    /**
     * Initializes the coffee object.
     */
    private void initCoffee() {
        coffee = new Coffee(CupSize.SHORT, new ArrayList<>());
    }

    /**
     * Initializes the spinners.
     */
    private void initSpinners() {
        initCoffeeSizeSpinner();
        initAmountCoffeeSpinner();
    }

    /**
     * Initializes the checkboxes.
     */
    private void initCheckBoxes() {
        mochaCheckBox = findViewById(R.id.mocha);
        initCheckBox(mochaCheckBox, CoffeeAddIn.MOCHA);
        caramelCheckBox = findViewById(R.id.caramel);
        initCheckBox(caramelCheckBox, CoffeeAddIn.CARAMEL);
        frenchVanillaCheckBox = findViewById(R.id.frenchVanilla);
        initCheckBox(frenchVanillaCheckBox, CoffeeAddIn.FRENCH_VANILLA);
        irishCreamCheckBox = findViewById(R.id.irishCream);
        initCheckBox(irishCreamCheckBox, CoffeeAddIn.IRISH_CREAM);
        sweetCreamCheckBox = findViewById(R.id.sweetCream);
        initCheckBox(sweetCreamCheckBox, CoffeeAddIn.SWEET_CREAM);
    }

    /**
     * Initializes the "Add to Order" button.
     */
    private void initAddToOrderButton() {
        findViewById(R.id.addToOrderCoffee).setOnClickListener(v -> {
            GlobalData.getInstance().getCurrentOrder().getMenuItems().add(coffee);
            Toast.makeText(this, "Item successfully added to the order", Toast.LENGTH_SHORT).show();
            resetPageToDefault();
        });
    }

    /**
     * Resets the page to its default state.
     */
    private void resetPageToDefault() {
        initCoffee();
        updateCoffeePrice();
        mochaCheckBox.setChecked(false);
        caramelCheckBox.setChecked(false);
        frenchVanillaCheckBox.setChecked(false);
        sweetCreamCheckBox.setChecked(false);
        irishCreamCheckBox.setChecked(false);

        Spinner coffeeSizeSpinner = findViewById(R.id.coffeeSize);
        coffeeSizeSpinner.setSelection(0); // Set to "Short"

        Spinner amountCoffeeSpinner = findViewById(R.id.amountCoffee);
        amountCoffeeSpinner.setSelection(0); // Set to 1
    }

    /**
     * Initializes a checkbox with its associated CoffeeAddIn.
     * @param checkBox The CheckBox to initialize.
     * @param addIn The CoffeeAddIn associated with the CheckBox.
     */
    private void initCheckBox(CheckBox checkBox, CoffeeAddIn addIn) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                coffee.getAddIns().add(addIn);
            } else {
                coffee.getAddIns().remove(addIn);
            }
            updateCoffeePrice();
        });
    }

    /**
     * Initializes the coffee size spinner.
     */
    private void initCoffeeSizeSpinner() {
        Spinner coffeeSizeSpinner = findViewById(R.id.coffeeSize);
        List<String> cupSizes = new ArrayList<>();
        for (CupSize size : CupSize.values()) {
            cupSizes.add(capitalizeWords(size.name()));
        }
        ArrayAdapter<String> coffeeSizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cupSizes);
        coffeeSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeSizeSpinner.setAdapter(coffeeSizeAdapter);
        coffeeSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coffee.setCupSize(CupSize.values()[position]);
                updateCoffeePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    /**
     * Initializes the amount coffee spinner.
     */
    private void initAmountCoffeeSpinner() {
        Spinner amountCoffeeSpinner = findViewById(R.id.amountCoffee);
        List<Integer> quantities = Arrays.asList(1, 2, 3, 4, 5);
        ArrayAdapter<Integer> amountCoffeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quantities);
        amountCoffeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amountCoffeeSpinner.setAdapter(amountCoffeeAdapter);
        amountCoffeeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coffee.setQuantity(quantities.get(position));
                updateCoffeePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    /**
     * Helper method for capitalizing words.
     * @param str The string to be capitalized.
     */
    private String capitalizeWords(String str) {
        String[] words = str.split("_");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }

    /**
     * Updates the coffee price displayed according to selection.
     */
    private void updateCoffeePrice() {
        TextView coffeeNumTextView = findViewById(R.id.coffeeNum);
        coffeeNumTextView.setText(String.format("%.2f", coffee.price()));
    }
}
