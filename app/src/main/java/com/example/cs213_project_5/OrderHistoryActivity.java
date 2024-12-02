package com.example.cs213_project_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history); // your XML layout file

        // Set onClick listener for close button
        findViewById(R.id.closeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast and navigate to Main activity
                Toast.makeText(OrderHistoryActivity.this, "Navigating to Main Menu", Toast.LENGTH_SHORT).show();
                navigateToMain();
            }
        });

        // Set onClick listener for back button
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast and navigate to Current Order activity
                Toast.makeText(OrderHistoryActivity.this, "Navigating to Current Order", Toast.LENGTH_SHORT).show();
                navigateToCurrentOrder();
            }
        });

        // Set onClick listener for continue button
        findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast and navigate to Create Pizza activity
                Toast.makeText(OrderHistoryActivity.this, "Navigating to Create Pizza", Toast.LENGTH_SHORT).show();
                navigateToCreatePizza();
            }
        });
    }

    // Method to navigate to Main activity
    private void navigateToMain() {
        Intent intent = new Intent(OrderHistoryActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // Method to navigate to Create Pizza activity
    private void navigateToCreatePizza() {
        Intent intent = new Intent(OrderHistoryActivity.this, CreatePizzaActivity.class);
        startActivity(intent);
    }

    // Method to navigate to Current Order activity
    private void navigateToCurrentOrder() {
        Intent intent = new Intent(OrderHistoryActivity.this, CurrentOrderActivity.class);
        startActivity(intent);
    }
}
