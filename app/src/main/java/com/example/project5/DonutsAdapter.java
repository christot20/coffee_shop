package com.example.project5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Adapter class for populating a RecyclerView with donuts data.
 * @author Aditya Pokale, Thomas Christo
 */
class DonutsAdapter extends RecyclerView.Adapter<DonutsAdapter.ItemsHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Donut> donuts; //need the data binding to each row of RecyclerView

    /**
     * Constructor for the DonutsAdapter.
     * @param context The context in which the adapter will be used.
     * @param donuts List of Donut objects to be displayed in the RecyclerView.
     */
    public DonutsAdapter(Context context, ArrayList<Donut> donuts) {
        this.context = context;
        this.donuts = donuts;
    }

    /**
     * This method inflates the row layout for the items in the RecyclerView.
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The type of the new View.
     * @return A new ItemsHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new ItemsHolder(view, donuts);
    }

    /**
     * This method is called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        holder.tv_name.setText(donuts.get(position).getType().getName());
        holder.tv_price.setText(String.valueOf(donuts.get(position).getType().getPrice()));
        holder.im_item.setImageResource(donuts.get(position).getType().getImage());

        //create donut object
        //Donut donut = new Donut(donuts.get(position).getType());

    }

    /**
     * This method returns the total number of items in the data set held by the adapter.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return donuts.size(); //number of MenuItem in the array list.
    }

     /**
     * ViewHolder class to hold the views for each item in the RecyclerView.
     * Gets the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Spinner spinner_quantity;
        private Button btn_add;
        private ConstraintLayout parentLayout; //this is the row layout
        private ArrayList<Donut> donuts; // Reference to the list of donuts

         /**
          * Constructor for the ViewHolder class.
          * @param itemView The View for each item in the RecyclerView.
          * @param donuts List of Donut objects to be displayed in the RecyclerView.
          */
        public ItemsHolder(@NonNull View itemView, ArrayList<Donut> donuts) {
            super(itemView);
            this.donuts = donuts;
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            parentLayout = itemView.findViewById(R.id.rowLayout);

            spinner_quantity = itemView.findViewById(R.id.spinner_quantity); // Initialize spinner
            ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(itemView.getContext(),
                    android.R.layout.simple_spinner_item, Arrays.asList(1, 2, 3, 4, 5, 6));
            quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_quantity.setAdapter(quantityAdapter);

            spinner_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    updatePrice(position); }  //update price shown in spinner
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing
                }
            });

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int quantity = (int) spinner_quantity.getSelectedItem();
                    DonutType selectedType = donuts.get(getAdapterPosition()).getType();
                    Donut selectedDonut = new Donut(selectedType);  // create new Donut instance
                    selectedDonut.setQuantity(quantity);
                    GlobalData.getInstance().getCurrentOrder().getMenuItems().add(selectedDonut);
                    Toast.makeText(itemView.getContext(),"Item successfully added to the order",
                            Toast.LENGTH_SHORT).show();
                    spinner_quantity.setSelection(0, false);    // reset spinner
                    }
            });
        }

         /**
          * This method updates the price of the spinner according to the quantity selected.
          * @param position The position of the spinner.
          */
        private void updatePrice(int position) {
            int quantity = (int) spinner_quantity.getItemAtPosition(position);
            DonutType selectedType = donuts.get(getAdapterPosition()).getType();
            Donut currDonut = new Donut(selectedType);
            currDonut.setQuantity(quantity);
            double price = currDonut.price();
            tv_price.setText(String.valueOf(price));
        }
    }
}
