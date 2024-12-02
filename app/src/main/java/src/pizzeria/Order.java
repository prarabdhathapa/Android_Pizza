package src.pizzeria;

import java.util.ArrayList;

public class Order {

    private int number; //order number
    private ArrayList<Pizza> pizzas; //can use List<E> instead of ArrayList<E>

    public Order(int number) {
        this.number = number;
        pizzas = new ArrayList<>();
    }

    public Order(int number, ArrayList<Pizza> inputPizzas) {
        this.number = number;
        pizzas = new ArrayList<>(inputPizzas);
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza newPizza) {
        pizzas.add(newPizza);
    }

    public void removePizza(Pizza removedPizza) {
        pizzas.remove(removedPizza);
    }
}