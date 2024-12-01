package com.example.cs213_project_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the click listeners for each button
        findViewById(R.id.custom_button_create_pizza).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show Toast and navigate to Create Pizza activity
                Toast.makeText(MainActivity.this, "Navigating to Create Pizza", Toast.LENGTH_SHORT).show();
                navigateToCreatePizza();
            }
        });

        findViewById(R.id.custom_button_current_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show Toast and navigate to Current Order activity
                Toast.makeText(MainActivity.this, "Navigating to Current Order", Toast.LENGTH_SHORT).show();
                navigateToCurrentOrder();
            }
        });

        findViewById(R.id.custom_button_order_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show Toast and navigate to Order History activity
                Toast.makeText(MainActivity.this, "Navigating to Order History", Toast.LENGTH_SHORT).show();
                navigateToOrderHistory();
            }
        });
    }

    // Method to navigate to Create Pizza activity
    private void navigateToCreatePizza() {
        Intent intent = new Intent(MainActivity.this, CreatePizzaActivity.class);
        startActivity(intent);
    }

    // Method to navigate to Current Order activity
    private void navigateToCurrentOrder() {
        Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    // Method to navigate to Order History activity
    private void navigateToOrderHistory() {
        Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
        startActivity(intent);
    }
}
