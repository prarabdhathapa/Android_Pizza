package src;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs213_project_5.R;

import java.util.ArrayList;

import src.pizzeria.*;

public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsAdapter.ToppingViewHolder> {

    private ArrayList<Toppings> toppingList;
    private ArrayList<Toppings> selectedToppings = new ArrayList<>(); // List to track selected toppings
    private static final int MAX_TOPPINGS = 7; // Limit of 7 toppings
    private boolean isSelectionEnabled = false; // Flag to check if toppings can be selected

    public ToppingsAdapter(ArrayList<Toppings> toppingList) {
        this.toppingList = toppingList;
    }

    @Override
    public ToppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_toppings, parent, false);
        return new ToppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToppingViewHolder holder, int position) {
        Toppings topping = toppingList.get(position);
        holder.nameTextView.setText(topping.getName());

        // Set the checkbox state based on whether the topping is selected
        holder.checkBox.setChecked(selectedToppings.contains(topping));

        // If toppings are not allowed, disable the checkbox
        holder.checkBox.setEnabled(isSelectionEnabled);

        // Handle checkbox click event to select/deselect topping
        if (isSelectionEnabled) {
            holder.checkBox.setOnClickListener(v -> {
                if (holder.checkBox.isChecked()) {
                    if (selectedToppings.size() < MAX_TOPPINGS) {
                        selectedToppings.add(topping);
                    } else {
                        Toast.makeText(v.getContext(), "You can only select up to 7 toppings.", Toast.LENGTH_SHORT).show();
                        holder.checkBox.setChecked(false); // Uncheck if the limit is reached
                    }
                } else {
                    selectedToppings.remove(topping);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return toppingList.size();
    }

    // Method to get the list of selected toppings
    public ArrayList<Toppings> getSelectedToppings() {
        return selectedToppings;
    }

    // Method to clear selected toppings when toppings are disabled
    public void clearSelectedToppings() {
        selectedToppings.clear();
        notifyDataSetChanged();
    }

    // Method to enable or disable topping selection
    public void setSelectionEnabled(boolean isEnabled) {
        isSelectionEnabled = isEnabled;
        notifyDataSetChanged(); // Update the UI when selection state changes
    }

    public static class ToppingViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        CheckBox checkBox;

        public ToppingViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.toppingName);
            checkBox = itemView.findViewById(R.id.toppingCheckbox);
        }
    }
}
