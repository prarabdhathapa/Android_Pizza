package src.pizzeria;

import java.util.ArrayList;
import java.util.Arrays;

public class BBQChicken extends Pizza {

    private ArrayList<Toppings> toppings = new ArrayList<>(Arrays.asList(Toppings.BBQCHICKEN, Toppings.GREENPEPPER, Toppings.PROVOLONE, Toppings.CHEDDAR));
    private final double smallBBQPrice = 14.99;
    private final double medBBQPrice = 16.99;
    private final double largeBBQPrice = 19.99;

    public BBQChicken(Crust crust, Size size, ImageResID imageResID) {
        super(crust, size, imageResID);
        setToppings(toppings);
    }

    @Override
    public double price() {
        switch (getSize()) {
            case SMALL:
                return smallBBQPrice;
            case MEDIUM:
                return medBBQPrice;
            case LARGE:
                return largeBBQPrice;
        }
        return largeBBQPrice;          //setting default prices to medium
    }
}
