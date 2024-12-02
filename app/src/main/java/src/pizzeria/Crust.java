package src.pizzeria;

/**
 * Crust defines an enum containing the various crust type for the pizzas.
 *
 * @author Prarabdha Alemagarthapa
 */
public enum Crust {

    BROOKLYN("New York"),
    THIN("New York"),
    HANDTOSSED("New York"),
    DEEPDISH("Chicago"),
    PAN("Chicago"),
    STUFFED("Chicago");

    private final String style;

    /**
     * Crust is initialized with the inputted pizza style.
     *
     * @param style the style of the pizza.
     */
    Crust(String style) {
        this.style = style;
    }
}