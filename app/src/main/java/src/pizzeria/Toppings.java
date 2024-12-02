package src.pizzeria;

public enum Toppings {

    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    GREENPEPPER("Green Pepper"),
    ONION("Onion"),
    MUSHROOM("Mushroom"),
    BBQCHICKEN("BBQ Chicken"),
    PROVOLONE("Provolone"),
    CHEDDAR("Cheddar"),
    BEEF("Beef"),
    HAM("Ham"),
    PINEAPPLE("Pineapple"),
    ANCHOVIES("Anchovies"),
    BLUECHEESE("Blue Cheese"),
    RANCH("Ranch"),
    OLIVES("Olives");

    private String name;

    Toppings(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}