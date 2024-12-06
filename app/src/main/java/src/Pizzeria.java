package src;

import java.util.ArrayList;

import src.pizzeria.*;

public class Pizzeria {
    private static Pizzeria instance = new Pizzeria();
    private ArrayList<Order> orders = new ArrayList<>();
    private Order order;
    private int id = 1;
    private static Order currentOrder;
    private static int orderNum = 1;
    private static final double TAX_RATE = .06625;
    private Pizzeria() {
        order = new Order(id); // Initialize a new order when the singleton is first created
        currentOrder = new Order(orderNum++);
    }

    public static Pizzeria getInstance(){
        return instance;
    }

    public void addPizzaToOrder(Pizza pizza) {
        order.addPizza(pizza);
    }

    //gets current order to be updated by pizzas from CreatePizzaActivity and accessed from CurrentOrderActivity
    public static Order getCurrentOrder() {
        if (currentOrder == null) {
            currentOrder = new Order(orderNum++);
        }
        return currentOrder;
    }
    private static ArrayList<Order> completedOrders = new ArrayList<>();

    public static void addCompleteOrders(Order order) {
        completedOrders.add(order);
    }
    public static void resetCurrentOrder() {
        currentOrder = new Order(orderNum++);
    }

    public static ArrayList<Order> getCompletedOrders(){
        return completedOrders;
    }

    public static double calculateSubtotal(){
        double subTotal = 0.0;
        for(Pizza pizza: getCurrentOrder().getPizzas()){
            subTotal += pizza.price();
        }
        return subTotal;
    }

    public static double calculateTax(){
        return calculateSubtotal() * TAX_RATE;
    }

    public static double calculateTotal(){
        return calculateSubtotal() + calculateTax();
    }
}
