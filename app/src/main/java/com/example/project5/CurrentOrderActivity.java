package com.example.project5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.ObservableArrayList;

/**
 * Activity class for displaying the current order and allowing interactions with it.
 * @author Thomas Christo
 */
public class CurrentOrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    ObservableArrayList<MenuItem> list;
    ArrayAdapter<MenuItem> items;
    private double subtotal;
    private double tax;
    private double total;
    private TextView subtotalTextView;
    private TextView taxTextView;
    private TextView totalTextView;

    /**
     * Initializes the activity and sets up the UI components.
     * @param savedInstanceState The saved instance state Bundle.
     */
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_current_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.currentOrderItems);

        // Get the current order from the GlobalData singleton
        GlobalData globalData = GlobalData.getInstance();
        Order currentOrder = globalData.getCurrentOrder();

        // Create an ObservableArrayList from the current order's menu items
        list = new ObservableArrayList<>();
        list.addAll(currentOrder.getMenuItems());

        // Create an ArrayAdapter for the ListView and set the data source to the ObservableArrayList
        items = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(items); // Set the adapter of the ListView to the source
        listView.setOnItemClickListener(this); // Add a listener to the ListView

        initializeConfirmOrderButton();

        subtotal = calculateSubtotal(list);     // calculate tax and total
        tax = calculateTax(subtotal);
        total = subtotal + tax;
        subtotalTextView = findViewById(R.id.subtotalNum);
        subtotalTextView.setText(String.format("$%.2f", subtotal));
        taxTextView = findViewById(R.id.taxNum);
        taxTextView.setText(String.format("$%.2f", tax));
        totalTextView = findViewById(R.id.totalNum);
        totalTextView.setText(String.format("$%.2f", total));
    }

    /**
     * Initializes the "Confirm Order" button and handles its click event.
     */
    private void initializeConfirmOrderButton() {
        findViewById(R.id.confirmOrderButton).setOnClickListener(v -> {
                GlobalData globalData = GlobalData.getInstance();
                globalData.placeOrder(globalData.getCurrentOrder());
                globalData.newCurrentOrder();
                list.clear();
                items.notifyDataSetChanged();
                Toast.makeText(CurrentOrderActivity.this, "Order has been placed!", Toast.LENGTH_SHORT).show();

                resetTotals();
        });
    }

    /**
     * Handles click events on the ListView items to prompt the user for item removal.
     * @param adapterView The AdapterView where the click happened.
     * @param view The view within the AdapterView that was clicked.
     * @param i The position of the view in the adapter.
     * @param l The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        GlobalData globalData = GlobalData.getInstance();
        new AlertDialog.Builder(this)
                .setTitle("Remove item")
                .setMessage("Are you sure you want to remove this item from the order?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    list.remove(i);
                    globalData.getCurrentOrder().getMenuItems().remove(i);
                    items.notifyDataSetChanged();

                    subtotal = 0.0;
                    subtotal = calculateSubtotal(list);     // calculate new tax and total
                    tax = calculateTax(subtotal);
                    total = subtotal + tax;
                    subtotalTextView.setText(String.format("$%.2f", subtotal));
                    taxTextView.setText(String.format("$%.2f", tax));
                    totalTextView.setText(String.format("$%.2f", total));

                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private double calculateSubtotal(ObservableArrayList<MenuItem> items) {
        for (MenuItem item : items) {
            subtotal += item.price();
        }
        return subtotal;
    }

    private double calculateTax(double subtotal) {
        double taxRate = 0.06625; // 6.625%
        return subtotal * taxRate;
    }

    private void resetTotals() {
        subtotal = 0.00;
        tax = 0.00;
        total = 0.00;
        subtotalTextView.setText(String.format("$%.2f", subtotal));
        taxTextView.setText(String.format("$%.2f", tax));
        totalTextView.setText(String.format("$%.2f", total));
    }
}
