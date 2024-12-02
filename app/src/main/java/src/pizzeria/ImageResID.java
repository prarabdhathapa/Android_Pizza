package src.pizzeria;

import com.example.cs213_project_5.R;

public enum ImageResID {
    NY_DELUXE(R.drawable.newyork_deluxe, "New York Deluxe"),
    NY_MEATZZA(R.drawable.newyork_meatzza, "New York Meatzza"),
    NY_BBQCHICKEN(R.drawable.newyork_bbqchicken, "New York Barbeque Chicken"),
    NY_CUSTOM(R.drawable.newyork_custom, "New York Build Your Own"),
    CHI_DELUXE(R.drawable.chicago_deluxe, "Chicago Deluxe"),
    CHI_MEATZZA(R.drawable.chicago_meatzza, "Chicago Meatzza"),
    CHI_BBQCHICKEN(R.drawable.chicago_bbqchicken, "Chicago Barbeque Chicken"),
    CHI_CUSTOM(R.drawable.chicago_custom, "Chicago Build Your Own");

    private final int imageResID;
    private final String pizzaName;

    ImageResID(int imageResID, String pizzaName) {
        this.imageResID = imageResID;
        this.pizzaName = pizzaName;
    }

    public int getImageResID() {
        return imageResID;
    }

    public String getPizzaName() {
        return pizzaName;
    }
}

