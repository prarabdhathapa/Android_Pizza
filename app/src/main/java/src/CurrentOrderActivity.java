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
