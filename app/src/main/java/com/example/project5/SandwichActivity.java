package com.example.project5;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

/**
 * Activity class for selecting sandwich options and adding them to the order.
 * @author Thomas Christo
 */
public class SandwichActivity extends AppCompatActivity {

    private Sandwich sandwich;
    private CheckBox lettuceCheckBox;
    private CheckBox tomatoCheckBox;
    private CheckBox cheeseCheckBox;
    private CheckBox onionCheckBox;
    private RadioGroup breadRadioGroup;
    private RadioGroup proteinRadioGroup;

    /**
     * Initializes the activity layout and sets up the sandwich selection UI components.
     * @param savedInstanceState The saved instance state Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sandwich);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initSandwich();

        initCheckBoxes();
        initRadioGroups();
        initAddToOrderButton();

        updateSandwichPrice();
    }

    /**
     * Initializes the sandwich object.
     */
    private void initSandwich() {
        sandwich = new Sandwich(BreadType.BAGEL, ProteinType.BEEF, new ArrayList<>());
    }

    /**
     * Initializes the checkboxes for sandwich add-ons.
     */
    private void initCheckBoxes() {
        lettuceCheckBox = findViewById(R.id.lettuce);
        initCheckBox(lettuceCheckBox, AddOn.LETTUCE);
        tomatoCheckBox = findViewById(R.id.tomato);
        initCheckBox(tomatoCheckBox, AddOn.TOMATO);
        cheeseCheckBox = findViewById(R.id.cheese);
        initCheckBox(cheeseCheckBox, AddOn.CHEESE);
        onionCheckBox = findViewById(R.id.onion);
        initCheckBox(onionCheckBox, AddOn.ONION);
    }

    /**
     * Initializes the radio groups for selecting bread and protein types.
     */
    private void initRadioGroups() {
        breadRadioGroup = findViewById(R.id.breadRadio);
        breadRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            String breadType = radioButton.getText().toString().toUpperCase().replace(" ", "_");
            sandwich.setBreadType(BreadType.valueOf(breadType));
            updateSandwichPrice();
        });

        proteinRadioGroup = findViewById(R.id.proteinRadio);
        proteinRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            String proteinType = radioButton.getText().toString().toUpperCase();
            sandwich.setProteinType(ProteinType.valueOf(proteinType));
            updateSandwichPrice();
        });
    }

    /**
     * Initializes the Add to Order button and its onClickListener.
     */
    private void initAddToOrderButton() {
        findViewById(R.id.addToOrderSandwich).setOnClickListener(v -> {
            GlobalData.getInstance().getCurrentOrder().getMenuItems().add(sandwich);
            Toast.makeText(this, "Item successfully added to the order", Toast.LENGTH_SHORT).show();
            resetPageToDefault();
        });
    }

    /**
     * Resets the page to its default state.
     */
    private void resetPageToDefault() {
        initSandwich();
        updateSandwichPrice();
        lettuceCheckBox.setChecked(false);
        tomatoCheckBox.setChecked(false);
        cheeseCheckBox.setChecked(false);
        onionCheckBox.setChecked(false);

        breadRadioGroup.check(R.id.bagel);
        proteinRadioGroup.check(R.id.beef);
    }

    /**
     * Initializes a checkbox for a specific add-on.
     * @param checkBox The CheckBox view.
     * @param addOn The type of add-on.
     */
    private void initCheckBox(CheckBox checkBox, AddOn addOn) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sandwich.getAddOns().add(addOn);
            } else {
                sandwich.getAddOns().remove(addOn);
            }
            updateSandwichPrice();
        });
    }

    /**
     * Updates the displayed price of the sandwich.
     */
    private void updateSandwichPrice() {
        TextView sandwichNumTextView = findViewById(R.id.sandwichNum);
        sandwichNumTextView.setText(String.format("%.2f", sandwich.price()));
    }
}
