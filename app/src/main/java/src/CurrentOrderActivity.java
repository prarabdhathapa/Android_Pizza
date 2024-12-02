package src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs213_project_5.R;

public class CurrentOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order); // your XML layout file

        // Set onClick listener for close button
        findViewById(R.id.closeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast and navigate to Main activity
                Toast.makeText(CurrentOrderActivity.this, "Navigating to Main Menu", Toast.LENGTH_SHORT).show();
                navigateToMain();
            }
        });

        // Set onClick listener for back button
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast and navigate to Create Pizza activity
                Toast.makeText(CurrentOrderActivity.this, "Navigating to Create Pizza", Toast.LENGTH_SHORT).show();
                navigateToCreatePizza();
            }
        });

        // Set onClick listener for continue button
        findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast and navigate to Order History activity
                Toast.makeText(CurrentOrderActivity.this, "Navigating to Order History", Toast.LENGTH_SHORT).show();
                navigateToOrderHistory();
            }
        });
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
