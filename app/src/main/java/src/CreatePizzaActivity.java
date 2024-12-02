package src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs213_project_5.R;

import java.util.ArrayList;

import src.pizzeria.*;

public class CreatePizzaActivity extends AppCompatActivity implements PizzaAdapter.OnPizzaClickListener {

    private RecyclerView pizzaRecyclerView;
    private RecyclerView toppingRecyclerView;
    private Button addToOrderButton;
    private Spinner sizeSpinner;
    private TextView priceTextView;

    private PizzaAdapter pizzaAdapter;
    private ToppingsAdapter toppingAdapter;

    private Pizza selectedPizza = null; // To hold the selected pizza

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pizza);

        pizzaRecyclerView = findViewById(R.id.pizzaRecyclerView);
        toppingRecyclerView = findViewById(R.id.toppingRecyclerView);
        addToOrderButton = findViewById(R.id.addToOrderButton);
        sizeSpinner = findViewById(R.id.order_spinner);
        priceTextView = findViewById(R.id.price);

        setupPizzaRecyclerView();
        setupToppingRecyclerView();

        // Set onClick listener for Add to Order button
        addToOrderButton.setOnClickListener(v -> {
            if (selectedPizza == null) {
                Toast.makeText(CreatePizzaActivity.this, "Please select a pizza.", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayList<Toppings> selectedToppings = toppingAdapter.getSelectedToppings();
            String selectedSize = sizeSpinner.getSelectedItem().toString();

            createPizzaOrder(selectedPizza, selectedToppings, selectedSize);
            navigateToCurrentOrder();
        });

        findViewById(R.id.closeButton).setOnClickListener(v -> navigateToMain());
        findViewById(R.id.backButton).setOnClickListener(v -> navigateToOrderHistory());
        findViewById(R.id.continueButton).setOnClickListener(v -> navigateToCurrentOrder());
    }

    private void setupPizzaRecyclerView() {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        PizzaFactory nyFactory = new NewYorkPizza();
        PizzaFactory chiFactory = new ChicagoPizza();

        // Add pizzas, including the "Build Your Own"
        pizzas.add(nyFactory.createDeluxe());
        pizzas.add(nyFactory.createMeatzza());
        pizzas.add(nyFactory.createBBQChicken());
        pizzas.add(nyFactory.createBuildYourOwn()); // This is the "Build Your Own" pizza
        pizzas.add(chiFactory.createDeluxe());
        pizzas.add(chiFactory.createMeatzza());
        pizzas.add(chiFactory.createBBQChicken());
        pizzas.add(chiFactory.createBuildYourOwn()); // This is also the "Build Your Own" pizza

        pizzaAdapter = new PizzaAdapter(pizzas, this); // Pass the listener to the adapter
        pizzaRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        pizzaRecyclerView.setAdapter(pizzaAdapter);
    }

    private void setupToppingRecyclerView() {
        ArrayList<Toppings> toppings = new ArrayList<>();
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.GREENPEPPER);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.MUSHROOM);
        toppings.add(Toppings.BBQCHICKEN);
        toppings.add(Toppings.PROVOLONE);
        toppings.add(Toppings.CHEDDAR);
        toppings.add(Toppings.BEEF);
        toppings.add(Toppings.HAM);
        toppings.add(Toppings.PINEAPPLE);
        toppings.add(Toppings.ANCHOVIES);
        toppings.add(Toppings.BLUECHEESE);
        toppings.add(Toppings.OLIVES);
        toppings.add(Toppings.RANCH);

        toppingAdapter = new ToppingsAdapter(toppings);
        toppingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toppingRecyclerView.setAdapter(toppingAdapter);
    }

    // Implementing the OnPizzaClickListener interface
    @Override
    public void onPizzaClick(Pizza pizza) {
        selectedPizza = pizza; // Set the selected pizza
        toggleToppingSelection(); // Enable or disable toppings based on pizza type
    }

    // Method to enable or disable toppings selection based on the selected pizza
    private void toggleToppingSelection() {
        if (selectedPizza != null && selectedPizza.getName().equals("Build Your Own")) {
            toppingRecyclerView.setVisibility(View.VISIBLE); // Show toppings
        } else {
            toppingRecyclerView.setVisibility(View.GONE); // Hide toppings if it's not a Build Your Own pizza
            toppingAdapter.clearSelectedToppings(); // Optionally clear previously selected toppings
        }
    }

    private void createPizzaOrder(Pizza selectedPizza, ArrayList<Toppings> selectedToppings, String selectedSize) {
        // Logic to create a pizza order with selected pizza, toppings, and size
    }

    private void navigateToMain() {
        Intent intent = new Intent(CreatePizzaActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void navigateToOrderHistory() {
        Intent intent = new Intent(CreatePizzaActivity.this, OrderHistoryActivity.class);
        startActivity(intent);
    }

    private void navigateToCurrentOrder() {
        Intent intent = new Intent(CreatePizzaActivity.this, CurrentOrderActivity.class);
        startActivity(intent);
    }
}
