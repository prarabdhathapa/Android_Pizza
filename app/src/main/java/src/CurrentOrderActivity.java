package src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs213_project_5.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import src.pizzeria.Order;
import src.pizzeria.Pizza;

public class CurrentOrderActivity extends AppCompatActivity {

    private RecyclerView orderRecyclerView;
    private PizzaAdapter pizzaAdapter;
    private Pizza selectedPizza = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        orderRecyclerView = findViewById(R.id.order_list_view);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Order currentOrder = Pizzeria.getCurrentOrder();
        ArrayList<Pizza> pizzas = currentOrder.getPizzas();

        selectedPizza = null;

        pizzaAdapter = new PizzaAdapter(pizzas, pizza -> {
            selectedPizza = pizza;
            Toast.makeText(this, "Selected: " + pizza.getName(), Toast.LENGTH_SHORT).show();
        });

        orderRecyclerView.setAdapter(pizzaAdapter);
        pizzaAdapter.resetSelection();

        TextView subTotalView = findViewById(R.id.subtotal);
        TextView taxView = findViewById(R.id.tax);
        TextView totalView = findViewById(R.id.total);

        updatePriceDisplay(subTotalView, taxView, totalView);

        findViewById(R.id.closeButton).setOnClickListener(v -> navigateToMain());
        findViewById(R.id.backButton).setOnClickListener(v -> navigateToCreatePizza());
        findViewById(R.id.continueButton).setOnClickListener(v -> navigateToOrderHistory());
        findViewById(R.id.remove_button).setOnClickListener(v -> removePizza(pizzas, subTotalView, taxView, totalView));
        findViewById(R.id.checkout_button).setOnClickListener(v -> checkoutPizza());
    }

    private void checkoutPizza() {
        Order currentOrder = Pizzeria.getCurrentOrder();
        if (currentOrder == null || currentOrder.getPizzas().isEmpty()) {
            noPizzaAlert();
            return;
        }

        Pizzeria.addCompleteOrders(currentOrder);
        Pizzeria.resetCurrentOrder();

        confirmationAlert();
    }

    private void removePizza(ArrayList<Pizza> pizzas, TextView subTotalView, TextView taxView, TextView totalView) {
        if (selectedPizza != null) {
            int selectedPizzaPosition = pizzas.indexOf(selectedPizza);

            pizzas.remove(selectedPizza);
            pizzaAdapter.notifyDataSetChanged();
            updatePriceDisplay(subTotalView, taxView, totalView);

            // Handle the case when there are still pizzas left
            if (!pizzas.isEmpty()) {
                if (selectedPizzaPosition == 0) {
                    // If the first pizza was removed, select the new first pizza
                    selectedPizza = pizzas.get(0);
                } else {
                    // Otherwise, select the next pizza in the list
                    selectedPizza = pizzas.get(Math.min(selectedPizzaPosition, pizzas.size() - 1));
                }
                pizzaAdapter.notifyDataSetChanged(); // Ensure the adapter is updated
            } else {
                selectedPizza = null;  // Reset if no pizzas are left
            }

            Toast.makeText(this, "Pizza removed", Toast.LENGTH_SHORT).show();
        } else {
            noPizzaAlert();
        }
    }

    private void confirmationAlert() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Order Added")
                .setMessage("Your order has been successfully added! Continue to checkout?")
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    navigateToOrderHistory();
                })
                .show();
    }

    private void noPizzaAlert() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("No Pizza in Current Order")
                .setMessage("There is no pizza currently in this order")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void updatePriceDisplay(TextView subtotal, TextView tax, TextView total) {
        subtotal.setText(String.format("Subtotal: $%.2f", Pizzeria.calculateSubtotal()));
        tax.setText(String.format("Tax: $%.2f", Pizzeria.calculateTax()));
        total.setText(String.format("Total: $%.2f", Pizzeria.calculateTotal()));
    }

    private void navigateToMain() {
        Intent intent = new Intent(CurrentOrderActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void navigateToCreatePizza() {
        Intent intent = new Intent(CurrentOrderActivity.this, CreatePizzaActivity.class);
        startActivity(intent);
    }

    private void navigateToOrderHistory() {
        Intent intent = new Intent(CurrentOrderActivity.this, OrderHistoryActivity.class);
        startActivity(intent);
    }
}
