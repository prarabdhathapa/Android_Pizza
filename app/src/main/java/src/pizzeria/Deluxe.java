package src.pizzeria;

import java.util.ArrayList;
import java.util.Arrays;

public class Deluxe extends Pizza {

    private ArrayList<Toppings> toppings = new ArrayList<>(Arrays.asList(Toppings.SAUSAGE, Toppings.PEPPERONI, Toppings.MUSHROOM, Toppings.ONION));
    private final double smallDeluxePrice = 16.99;
    private final double medDeluxePrice = 18.99;
    private final double largeDeluxePrice = 20.99;

    public Deluxe(Crust crust, Size size, ImageResID imageResID) {
        super(crust, size, imageResID);
        setToppings(toppings);
    }

    @Override
    public double price() {
        switch (getSize()) {
            case SMALL:
                return smallDeluxePrice;
            case MEDIUM:
                return medDeluxePrice;
            case LARGE:
                return largeDeluxePrice;
        }
        return medDeluxePrice;          //setting default prices to medium
    }
}