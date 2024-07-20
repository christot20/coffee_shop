package com.example.project5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.ObservableArrayList;

/**
 * Activity class responsible for managing orders.
 * @author Thomas Christo
 */
public class OrdersActivity extends AppCompatActivity {
    private Spinner ordersSpinner;
    private ListView orderListView;
    private TextView totalTextView;
    private Button removeOrdersButton;
    private ArrayAdapter<Order> ordersAdapter;
    private ArrayAdapter<MenuItem> itemsAdapter;
    private ObservableArrayList<Order> allOrders;
    private ObservableArrayList<MenuItem> currentOrderItems;

    /**
     * Initializes the activity layout and sets up UI components.
     * @param savedInstanceState The saved instance state Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ordersSpinner = findViewById(R.id.ordersSpinner);
        orderListView = findViewById(R.id.orderListView);
        removeOrdersButton = findViewById(R.id.removeOrdersButton);
        totalTextView = findViewById(R.id.orderTotalNum);

        // Get all orders from the GlobalData singleton
        GlobalData globalData = GlobalData.getInstance();
        allOrders = new ObservableArrayList<>();
        allOrders.addAll(globalData.getAllOrders());

        // Create an ArrayAdapter for the Spinner and set the data source to allOrders
        ordersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allOrders);
        ordersSpinner.setAdapter(ordersAdapter);

        // Initialize the ObservableArrayList for the current order's items
        currentOrderItems = new ObservableArrayList<>();

        // Create an ArrayAdapter for the ListView and set the data source to currentOrderItems
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentOrderItems);
        orderListView.setAdapter(itemsAdapter);

        // Set the Spinner's onItemSelectedListener
        setSpinnerOnItemSelectedListener();

        // Set the Button's onClickListener
        setButtonOnClickListener();
    }

    /**
     * Sets the onItemSelectedListener for the ordersSpinner.
     */
    private void setSpinnerOnItemSelectedListener() {
        ordersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Order selectedOrder = (Order) parent.getItemAtPosition(position);
                currentOrderItems.clear();
                currentOrderItems.addAll(selectedOrder.getMenuItems());
                itemsAdapter.notifyDataSetChanged();

                // calculate total for the selected order
                double total = calculateTotal(selectedOrder);
                totalTextView.setText(String.format("$%.2f", total));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private double calculateTotal(Order order) {
        double subtotal = 0;
        double taxRate = 0.06625; // 6.625%
        for (MenuItem item : order.getMenuItems()) {
            subtotal += item.price();
        }
        return subtotal * (1 + taxRate);
    }

    /**
     * Sets the onClickListener for the Remove Orders Button.
     */
    @SuppressLint("DefaultLocale")
    private void setButtonOnClickListener() {
        GlobalData globalData = GlobalData.getInstance();
        removeOrdersButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Remove order")
                    .setMessage("Are you sure you want to remove this order?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        Order selectedOrder = (Order) ordersSpinner.getSelectedItem();
                        allOrders.remove(selectedOrder);
                        globalData.getAllOrders().remove(selectedOrder);
                        ordersAdapter.notifyDataSetChanged();
                        if (!allOrders.isEmpty()) {
                            ordersSpinner.setSelection(0);
                            Order newSelectedOrder = (Order) ordersSpinner.getSelectedItem();
                            currentOrderItems.clear();
                            currentOrderItems.addAll(newSelectedOrder.getMenuItems());

                            double newTotal = calculateTotal(newSelectedOrder);
                            totalTextView.setText(String.format("$%.2f", newTotal));

                            itemsAdapter.notifyDataSetChanged();
                        } else {
                            currentOrderItems.clear();
                            totalTextView.setText(String.format("$%.2f", 0.0));
                            itemsAdapter.notifyDataSetChanged();
                        }
                        Toast.makeText(OrdersActivity.this, "Order has been removed!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });
    }
}
