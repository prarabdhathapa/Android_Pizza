package src;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs213_project_5.R;

import java.util.ArrayList;

import src.pizzeria.*;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private ArrayList<Pizza> pizzaList;
    private Pizza selectedPizza = null;
    private OnPizzaClickListener onPizzaClickListener; // Reference to listener interface

    public PizzaAdapter(ArrayList<Pizza> pizzaList, OnPizzaClickListener listener) {
        this.pizzaList = pizzaList;
        this.onPizzaClickListener = listener; // Set the listener
    }

    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.nameTextView.setText(pizza.getName());
        holder.imageImageView.setImageResource(pizza.getImageResID());

        // Set the selection behavior (when clicked)
        holder.itemView.setOnClickListener(v -> {
            selectedPizza = pizza;  // Set the selected pizza
            notifyDataSetChanged(); // Notify adapter to update UI (e.g., highlight selected item)

            // Notify the activity about the click
            if (onPizzaClickListener != null) {
                onPizzaClickListener.onPizzaClick(pizza);
            }
        });

        // Visual cue for the selected pizza
        if (selectedPizza == pizza) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);  // Highlight selected item
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);  // Default state
        }
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    // Method to get the selected pizza
    public Pizza getSelectedPizza() {
        return selectedPizza;
    }

    // Interface for the click listener
    public interface OnPizzaClickListener {
        void onPizzaClick(Pizza pizza);
    }

    public class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageImageView;

        public PizzaViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.pizzaName);
            imageImageView = itemView.findViewById(R.id.pizzaImage);
        }
    }
}

