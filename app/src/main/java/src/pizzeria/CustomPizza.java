package src.pizzeria;

import java.util.ArrayList;

public class CustomPizza extends Pizza {

    private ArrayList<Toppings> toppings = new ArrayList<>();
    private final double smallCustomPrice = 8.99;
    private final double medCustomPrice = 10.99;
    private final double largeCustomPrice = 12.99;
    private final double costOfTopping = 1.69;

    public CustomPizza(Crust crust, Size size, ImageResID imageResID) {
        super(crust, size, imageResID);
        setToppings(toppings);
    }

    @Override
    public double price() {
        double costOfToppings = toppings.size() * costOfTopping;
        return switch (getSize()) {
            case SMALL -> (smallCustomPrice + costOfToppings);
            case MEDIUM -> (medCustomPrice + costOfToppings);
            case LARGE -> (largeCustomPrice + costOfToppings);
        };
    }
}
