package src;

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

public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsAdapter.ToppingsViewHolder> {
    private static final int MAX_TOPPINGS = 7;
    private final ArrayList<Toppings> toppings;
    private final ArrayList<Toppings> selectedToppings = new ArrayList<>();
    private boolean isSelectionEnabled = true;
    private OnToppingClickListener listener;

    public ToppingsAdapter(ArrayList<Toppings> toppings, OnToppingClickListener listener) {
        this.toppings = toppings;
        this.listener = listener;
    }

    @Override
    public ToppingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_toppings, parent, false);
        return new ToppingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToppingsViewHolder holder, int position) {
        Toppings topping = toppings.get(position);
        holder.nameTextView.setText(topping.getName());
        holder.checkBox.setTag(topping);

        // Check if this topping is selected
        holder.checkBox.setChecked(selectedToppings.contains(topping));

        // Enable or disable checkbox based on selection state
        holder.checkBox.setEnabled(isSelectionEnabled);
    }

    public void setSelectedToppings(ArrayList<Toppings> selectedToppings) {
        this.selectedToppings.clear();
        this.selectedToppings.addAll(selectedToppings);
        notifyDataSetChanged();
    }

    public void enableSelection(boolean isEnabled) {
        isSelectionEnabled = isEnabled;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return toppings.size();
    }

    public interface OnToppingClickListener {
        void onToppingClick(Toppings topping);
    }

    // ViewHolder class
    public class ToppingsViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        CheckBox checkBox;

        public ToppingsViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.toppingName);
            checkBox = itemView.findViewById(R.id.toppingCheckbox);

            // Click listener to handle topping selection
            checkBox.setOnClickListener(v -> {
                Toppings topping = (Toppings) v.getTag();
                int position = getBindingAdapterPosition();
                if (position == RecyclerView.NO_POSITION) {
                    return;
                }
                if (checkBox.isChecked()) {
                    if (selectedToppings.size() < MAX_TOPPINGS) {
                        selectedToppings.add(topping); // Add topping
                        listener.onToppingClick(topping); // Notify listener
                    } else {
                        Toast.makeText(v.getContext(), "You can only select up to 7 toppings.", Toast.LENGTH_SHORT).show();
                        checkBox.setChecked(false);  // Uncheck if the limit is exceeded
                    }
                } else {
                    selectedToppings.remove(topping); // Remove topping
                    listener.onToppingClick(topping); // Notify listener
                }
                notifyItemChanged(position);
            });
        }
    }
}
