package src;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class CreatePizzaActivity extends AppCompatActivity implements PizzaAdapter.OnPizzaClickListener, ToppingsAdapter.OnToppingClickListener {

    private RecyclerView pizzaRecyclerView;
    private PizzaAdapter pizzaAdapter;
    private RecyclerView toppingRecyclerView;
    private ToppingsAdapter toppingAdapter;
    private Spinner sizeSpinner;
    private TextView priceTextView;
    private Button addToOrderButton;
    private Pizza selectedPizza = null;
    private Size size = null;

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
        setupSizeSpinner();

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedSize = (String) parentView.getItemAtPosition(position);
                size = Size.fromString(selectedSize);
                selectedPizza.setSize(size);
                updatePrice();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        addToOrderButton.setOnClickListener(v -> {
            if (selectedPizza == null) {
                Toast.makeText(CreatePizzaActivity.this, "Please select a pizza.", Toast.LENGTH_SHORT).show();
                return;
            }
            createPizzaOrder();
        });

        findViewById(R.id.closeButton).setOnClickListener(v -> navigateToMain());
        findViewById(R.id.backButton).setOnClickListener(v -> navigateToOrderHistory());
        findViewById(R.id.continueButton).setOnClickListener(v -> navigateToCurrentOrder());
    }

    private void createPizzaOrder() {
        if(selectedPizza == null){
            showAlert("Please select a pizza");
            return;
        }
        if(size == null){
            showAlert("Please select size");
            return;
        }

        Pizza pizza = selectedPizza;
        Pizzeria.getCurrentOrder().addPizza(selectedPizza);     //should add pizza to global class
        Toast.makeText(this, pizza.getName() + " has been added to your order", Toast.LENGTH_SHORT).show();

        resetSelection();
    }

    private void resetSelection(){
        //Dont know if it make sense to create these methods yet
        // pizzaAdapter.setSelectedPizza(null);
        // toppingAdapter.clearSelection();
        sizeSpinner.setSelection(0);
        selectedPizza = null;
        size = null;
        updatePrice();
    }


    private void showAlert(String message){
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void toggleToppingSelection() {
        if (selectedPizza != null && (selectedPizza.getName().equals("New York Build Your Own") || selectedPizza.getName().equals("Chicago Build Your Own"))) {
            toppingAdapter.enableSelection(true);
        } else {
            toppingAdapter.enableSelection(false);
        }
        toppingAdapter.setSelectedToppings(selectedPizza.getToppings());
    }

    private void updatePrice() {
        if (selectedPizza == null) return;
        double price = selectedPizza.price();
        priceTextView.setText("Price: $" + String.format("%.2f", price));
    }

    private void setupPizzaRecyclerView() {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        PizzaFactory nyFactory = new NewYorkPizza();
        PizzaFactory chiFactory = new ChicagoPizza();

        pizzas.add(nyFactory.createBuildYourOwn());
        pizzas.add(nyFactory.createDeluxe());
        pizzas.add(nyFactory.createMeatzza());
        pizzas.add(nyFactory.createBBQChicken());
        pizzas.add(chiFactory.createBuildYourOwn());
        pizzas.add(chiFactory.createDeluxe());
        pizzas.add(chiFactory.createMeatzza());
        pizzas.add(chiFactory.createBBQChicken());

        selectedPizza = pizzas.get(0);
        size = selectedPizza.getSize();
        updatePrice();

        pizzaAdapter = new PizzaAdapter(pizzas, this);
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

        toppingAdapter = new ToppingsAdapter(toppings, this);
        toppingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toppingRecyclerView.setAdapter(toppingAdapter);
    }

    private void setupSizeSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.pizza_sizes));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);
        int position = adapter.getPosition(selectedPizza.getSize().getSize());
        sizeSpinner.setSelection(position);
    }

    @Override
    public void onPizzaClick(Pizza pizza) {
        selectedPizza = pizza;
        selectedPizza.setSize(size);
        updatePrice();
        toggleToppingSelection();
    }

    @Override
    public void onToppingClick(Toppings topping) {
        if (selectedPizza != null) {
            if (selectedPizza.getToppings().contains(topping)) {
                selectedPizza.removeTopping(topping);
            } else {
                selectedPizza.addTopping(topping);
            }
            updatePrice();
            toppingAdapter.notifyDataSetChanged();
        }
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
