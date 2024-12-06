package src;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cs213_project_5.R;

import java.util.ArrayList;
import src.pizzeria.Order;
import src.pizzeria.Pizza;

public class OrderAdapter extends ArrayAdapter<Order> {

    private Context context;
    private ArrayList<Order> orders;

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        super(context, R.layout.item_order, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
            holder = new ViewHolder();
            holder.orderNumberTextView = convertView.findViewById(R.id.order_number);
            holder.orderDetailsTextView = convertView.findViewById(R.id.order_details);
            holder.subtotalTextView = convertView.findViewById(R.id.order_subtotal);
            holder.taxTextView = convertView.findViewById(R.id.order_tax);
            holder.totalTextView = convertView.findViewById(R.id.order_total);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Order order = orders.get(position);

        // Set order details
        holder.orderNumberTextView.setText("Order #" + order.getNumber());

        // Set pizza details
        StringBuilder orderDetails = new StringBuilder();
        for (Pizza pizza : order.getPizzas()) {
            orderDetails.append(pizza.getName()).append("\n");
        }
        holder.orderDetailsTextView.setText(orderDetails.toString());

        // Set pricing details
        holder.subtotalTextView.setText(String.format("Subtotal: $%.2f", order.getTotalPrice()));
        holder.taxTextView.setText(String.format("Tax: $%.2f", order.getTotalTax()));
        holder.totalTextView.setText(String.format("Total: $%.2f", order.getTotalTax() + order.getTotalPrice()));

        return convertView;
    }

    // ViewHolder class to optimize list item lookup
    static class ViewHolder {
        TextView orderNumberTextView;
        TextView orderDetailsTextView;
        TextView subtotalTextView;
        TextView taxTextView;
        TextView totalTextView;
    }
}
