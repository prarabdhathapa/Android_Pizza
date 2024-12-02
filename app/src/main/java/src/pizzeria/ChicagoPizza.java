package src.pizzeria;

public class ChicagoPizza implements PizzaFactory {

    @Override
    public Pizza createDeluxe() {
        Deluxe deluxe = new Deluxe(Crust.DEEPDISH, Size.MEDIUM, ImageResID.CHI_DELUXE.getImageResID());
        return deluxe;
    }

    @Override
    public Pizza createMeatzza() {
        Meatzza meatzza = new Meatzza(Crust.STUFFED, Size.MEDIUM, ImageResID.CHI_MEATZZA.getImageResID());
        return meatzza;
    }

    @Override
    public Pizza createBBQChicken() {
        BBQChicken bbqChicken = new BBQChicken(Crust.PAN, Size.MEDIUM, ImageResID.CHI_BBQCHICKEN.getImageResID());
        return bbqChicken;
    }

    @Override
    public Pizza createBuildYourOwn() {
        CustomPizza custom = new CustomPizza(Crust.PAN, Size.MEDIUM, ImageResID.CHI_CUSTOM.getImageResID());
        return custom;
    }
}
