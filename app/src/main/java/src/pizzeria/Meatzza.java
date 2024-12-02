package src.pizzeria;

import java.util.ArrayList;
import java.util.Arrays;

public class Meatzza extends Pizza {

    private ArrayList<Toppings> toppings = new ArrayList<>(Arrays.asList(Toppings.SAUSAGE, Toppings.PEPPERONI, Toppings.BEEF, Toppings.HAM));
    private final double smallMeatzzaPrice = 17.99;
    private final double medMeatzzaPrice = 19.99;
    private final double largeMeatzzaPrice = 21.99;

    public Meatzza(Crust crust, Size size, int imageResID) {
        super(crust, size, imageResID);
        setToppings(toppings);
    }

    @Override
    public double price() {
        switch (getSize()) {
            case SMALL:
                return smallMeatzzaPrice;
            case MEDIUM:
                return medMeatzzaPrice;
            case LARGE:
                return largeMeatzzaPrice;
        }
        return medMeatzzaPrice;          //setting default prices to medium
    }
}
