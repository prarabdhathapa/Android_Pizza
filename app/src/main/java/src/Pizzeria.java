package src;

import java.util.ArrayList;

import src.pizzeria.*;

public class Pizzeria {
    private static final double TAX_RATE = .06625;
    private static Pizzeria instance = new Pizzeria();
    private static ArrayList<Order> completedOrders = new ArrayList<>();
    private static Order currentOrder;
    private static int orderNum = 1;

    Pizzeria() {
        currentOrder = new Order(orderNum++);
    }

    public static Pizzeria getInstance() {
        return instance;
    }

    public static Order getCurrentOrder() {
        if (currentOrder == null) {
            currentOrder = new Order(orderNum++);
        }
        return currentOrder;
    }

    public static void addCompleteOrders(Order order) {
        completedOrders.add(order);
    }

    public static void resetCurrentOrder() {
        currentOrder = new Order(orderNum++);
    }

    public static ArrayList<Order> getCompletedOrders() {
        return completedOrders;
    }

    public static double calculateSubtotal() {
        double subTotal = 0.0;
        for (Pizza pizza : getCurrentOrder().getPizzas()) {
            subTotal += pizza.price();
        }
        return subTotal;
    }

    public static double calculateTax() {
        return calculateSubtotal() * TAX_RATE;
    }

    public static double calculateTotal() {
        return calculateSubtotal() + calculateTax();
    }
}
