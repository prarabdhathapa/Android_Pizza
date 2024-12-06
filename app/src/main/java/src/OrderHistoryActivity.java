package src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs213_project_5.R;

import java.util.ArrayList;
import src.pizzeria.Order;

public class OrderHistoryActivity extends AppCompatActivity {

    private OrderAdapter orderAdapter;
    private ArrayList<Order> completedOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history); // your XML layout file

        ListView orderHistoryListview = findViewById(R.id.order_history_list_view);

        completedOrders = Pizzeria.getCompletedOrders();

        orderAdapter = new OrderAdapter(this, completedOrders);
        orderHistoryListview.setAdapter(orderAdapter);

        orderHistoryListview.setOnItemClickListener((parent, view, position, id) -> {
            Order selectedOrder = completedOrders.get(position);
            Toast.makeText(this, "Selected Order #" + selectedOrder.getNumber(), Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.closeButton).setOnClickListener(v -> navigateToMain());
        findViewById(R.id.backButton).setOnClickListener(v -> navigateToCurrentOrder());
        findViewById(R.id.continueButton).setOnClickListener(v -> navigateToCreatePizza());
        findViewById(R.id.cancel_order_button).setOnClickListener(v -> cancelOrder());
    }

    private void cancelOrder(){
        if (completedOrders.size() > 0) {
            Order selectedOrder = completedOrders.get(0); // For example, the first order
            completedOrders.remove(selectedOrder); // Remove the entire order

            Toast.makeText(this, "Order #" + selectedOrder.getNumber() + " has been canceled", Toast.LENGTH_SHORT).show();

            orderAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No orders available to cancel", Toast.LENGTH_SHORT).show();
        }
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
