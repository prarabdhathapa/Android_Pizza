package src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs213_project_5.R;

import java.util.ArrayList;

import src.pizzeria.Order;
import src.pizzeria.Pizza;

public class CurrentOrderActivity extends AppCompatActivity {

    private RecyclerView orderRecyclerView;
    private PizzaAdapter pizzaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order); // your XML layout ficurrent

        orderRecyclerView = findViewById(R.id.order_list_view);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Order currentOrder = Pizzeria.getCurrentOrder();
        ArrayList<Pizza> pizzas = currentOrder.getPizzas();

        pizzaAdapter = new PizzaAdapter(pizzas, pizza ->
                Toast.makeText(this, "Selected: " + pizza.getName(), Toast.LENGTH_SHORT).show()
                );

        orderRecyclerView.setAdapter(pizzaAdapter);

        findViewById(R.id.closeButton).setOnClickListener(v -> navigateToMain());
        findViewById(R.id.backButton).setOnClickListener(v -> navigateToCreatePizza());
        findViewById(R.id.continueButton).setOnClickListener(v -> navigateToOrderHistory());
    }

    // Method to navigate to Main activity
    private void navigateToMain() {
        Intent intent = new Intent(CurrentOrderActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // Method to navigate to Create Pizza activity
    private void navigateToCreatePizza() {
        Intent intent = new Intent(CurrentOrderActivity.this, CreatePizzaActivity.class);
        startActivity(intent);
    }

    // Method to navigate to Order History activity
    private void navigateToOrderHistory() {
        Intent intent = new Intent(CurrentOrderActivity.this, OrderHistoryActivity.class);
        startActivity(intent);
    }
}
