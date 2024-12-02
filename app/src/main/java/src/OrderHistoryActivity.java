package src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs213_project_5.R;

public class OrderHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history); // your XML layout file

        findViewById(R.id.closeButton).setOnClickListener(v -> navigateToMain());
        findViewById(R.id.backButton).setOnClickListener(v -> navigateToCurrentOrder());
        findViewById(R.id.continueButton).setOnClickListener(v -> navigateToCreatePizza());
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
