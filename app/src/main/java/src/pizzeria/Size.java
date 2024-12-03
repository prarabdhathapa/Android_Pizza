package src.pizzeria;

public enum Size {

    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");

    private String size;

    Size(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public static Size fromString(String sizeString) {
        for (Size size : Size.values()) {
            if (size.getSize().equalsIgnoreCase(sizeString)) {
                return size;
            }
        }
        return null;
    }
}