package src.pizzeria;

import com.example.cs213_project_5.R;

public enum ImageResID {
    NY_DELUXE(R.drawable.newyork_deluxe),
    NY_MEATZZA(R.drawable.newyork_meatzza),
    NY_BBQCHICKEN(R.drawable.newyork_bbqchicken),
    NY_CUSTOM(R.drawable.newyork_custom),
    CHI_DELUXE(R.drawable.chicago_deluxe),
    CHI_MEATZZA(R.drawable.chicago_meatzza),
    CHI_BBQCHICKEN(R.drawable.chicago_bbqchicken),
    CHI_CUSTOM(R.drawable.chicago_custom);

    private final int imageResID;

    // Constructor
    ImageResID(int imageResID) {
        this.imageResID = imageResID;
    }

    // Getter for image resource ID
    public int getImageResID() {
        return imageResID;
    }
}
