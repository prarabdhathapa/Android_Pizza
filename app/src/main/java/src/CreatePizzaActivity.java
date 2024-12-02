package src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs213_project_5.R;

public class CreatePizzaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pizza); // your XML layout file

        // Set onClick listener for close button
        findViewById(R.id.closeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast and navigate to Main activity
                Toast.makeText(CreatePizzaActivity.this, "Navigating to Main Menu", Toast.LENGTH_SHORT).show();
                navigateToMain();
            }
        });

        // Set onClick listener for back button
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast and navigate to Order History activity
                Toast.makeText(CreatePizzaActivity.this, "Navigating to Order History", Toast.LENGTH_SHORT).show();
                navigateToOrderHistory();
            }
        });

        // Set onClick listener for continue button
        findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast and navigate to Current Order activity
                Toast.makeText(CreatePizzaActivity.this, "Navigating to Order Details", Toast.LENGTH_SHORT).show();
                navigateToCurrentOrder();
            }
        });
    }

    // Method to navigate to Main activity
    private void navigateToMain() {
        Intent intent = new Intent(CreatePizzaActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // Method to navigate to Current Order activity
    private void navigateToCurrentOrder() {
        Intent intent = new Intent(CreatePizzaActivity.this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    // Method to navigate to Order History activity
    private void navigateToOrderHistory() {
        Intent intent = new Intent(CreatePizzaActivity.this, OrderHistoryActivity.class);
        startActivity(intent);
    }
}
