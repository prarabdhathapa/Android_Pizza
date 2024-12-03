package src;

import java.util.ArrayList;

import src.pizzeria.*;

public class Pizzeria {

    private static Pizzeria instance;
    private ArrayList<Order> orders = new ArrayList<>();
    private Order order;
    private int id = 1;
    private static Order currentOrder = null;
    private static int orderNum = 1;

    private Pizzeria() {
        order = new Order(id); // Initialize a new order when the singleton is first created
    }

    public void addPizzaToOrder(Pizza pizza) {
        order.addPizza(pizza);
    }

    //gets current order to be updated by pizzas from CreatePizzaActivity and accessed from CurrentOrderActivity
    public static Order getCurrentOrder(){
        if(currentOrder == null){
            currentOrder = new Order(orderNum++);
        }
        return currentOrder;
    }
    public static void resetOrder(){
        currentOrder = new Order(orderNum++);
    }
}
