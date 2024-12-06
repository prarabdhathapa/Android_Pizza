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

    private ArrayList<Pizza> pizzas;
    private int selectedPosition = 0;
    private OnPizzaClickListener onPizzaClickListener;

    PizzaAdapter(ArrayList<Pizza> pizzas, OnPizzaClickListener listener) {
        this.pizzas = pizzas;
        this.onPizzaClickListener = listener;
    }

    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PizzaViewHolder holder, int position) {
        Pizza pizza = pizzas.get(position);
        holder.nameTextView.setText(pizza.getName());
        holder.pizzaImageView.setImageResource(pizza.getImageResID());
        holder.pizzaSize.setText("Size: " + pizza.getSize()); // Set the pizza size
        holder.pizzaCrust.setText("Crust: " + pizza.getCrust()); // Set the crust information
        holder.pizzaPrice.setText(String.format("Subtotal: $%.2f", pizza.price()));


        if (pizza.getToppings().isEmpty()) {
            holder.pizzaToppings.setText("No Toppings");
        } else {
            holder.pizzaToppings.setText("Toppings: " + pizza.printToppings());
        }
        // Highlight the selected pizza
        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnClickListener(v -> {
            int clickedPosition = holder.getBindingAdapterPosition();
            if (clickedPosition != RecyclerView.NO_POSITION) {
                if (selectedPosition != clickedPosition) {
                    // Update selected position
                    int previousSelectedPosition = selectedPosition;
                    selectedPosition = clickedPosition;

                    // Notify only the item that was previously selected and the newly selected item
                    notifyItemChanged(previousSelectedPosition);
                    notifyItemChanged(selectedPosition);

                    if (onPizzaClickListener != null) {
                        onPizzaClickListener.onPizzaClick(pizza);
                    }
                }
            }
        });
    }

    public void resetSelection() {
        int previousSelectedPosition = selectedPosition;
        selectedPosition = RecyclerView.NO_POSITION;
        if (previousSelectedPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(previousSelectedPosition);
        }
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public interface OnPizzaClickListener {
        void onPizzaClick(Pizza pizza);
    }

    public class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, pizzaSize, pizzaToppings, pizzaCrust, pizzaPrice;
        ImageView pizzaImageView;

        public PizzaViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.pizzaName);
            pizzaImageView = itemView.findViewById(R.id.pizzaImage);
            pizzaSize = itemView.findViewById(R.id.pizza_size);
            pizzaToppings = itemView.findViewById(R.id.pizza_toppings);
            pizzaCrust = itemView.findViewById(R.id.pizza_crust);
            pizzaPrice = itemView.findViewById(R.id.pizza_price);

        }
    }
}
