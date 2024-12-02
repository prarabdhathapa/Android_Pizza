package src.pizzeria;

import android.media.Image;

import java.util.ArrayList;

public abstract class Pizza {

    private ArrayList<Toppings> toppings; // Topping is an Enum class
    private Crust crust;  // Crust is an Enum class
    private Size size;   // Size is an Enum class
    private ImageResID imageResID; // ImageResID is an Enum class

    public abstract double price();

    public Pizza() {
        this.crust = Crust.PAN;
        size = Size.MEDIUM;
        imageResID = ImageResID.CHI_CUSTOM;
    }

    public Pizza(Crust crust, Size size, ImageResID imageResID) {
        this.crust = crust;
        this.size = size;
        this.imageResID = imageResID;
    }

    public ArrayList<Toppings> getToppings() {
        for (int i = 0; i < toppings.size(); i++) {
        }
        return toppings;
    }

    public Crust getCrust() {
        return crust;
    }

    public Size getSize() {
        return size;
    }

    public int getImageResID() {
        return imageResID.getImageResID();
    }

    public void setToppings(ArrayList<Toppings> toppings) {
        this.toppings = toppings;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void addTopping(Toppings topping) {
        toppings.add(topping);
    }

    public void removeTopping(Toppings topping) {
        toppings.remove(topping);
    }

    public String getName() {
        return imageResID.getPizzaName();
    }


    public String printToppings() {
        String strToppings = "";
        for (int i = 0; i < toppings.size(); i++) {
            strToppings = strToppings + toppings.get(i).toString() + " ";
        }
        return strToppings;
    }

    @Override
    public String toString() {
        return ("Crust: " + crust.toString() + ", Size: " + size.toString() + ", Toppings: " + printToppings());
    }
}