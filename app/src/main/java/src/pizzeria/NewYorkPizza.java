package src.pizzeria;

public class NewYorkPizza implements PizzaFactory {

    @Override
    public Pizza createDeluxe() {
        Deluxe deluxe = new Deluxe(Crust.BROOKLYN, Size.MEDIUM, ImageResID.NY_DELUXE.getImageResID());
        return deluxe;
    }

    @Override
    public Pizza createMeatzza() {
        Meatzza meatzza = new Meatzza(Crust.HANDTOSSED, Size.MEDIUM, ImageResID.NY_MEATZZA.getImageResID());
        return meatzza;
    }

    @Override
    public Pizza createBBQChicken() {
        BBQChicken bbqChicken = new BBQChicken(Crust.THIN, Size.MEDIUM, ImageResID.NY_BBQCHICKEN.getImageResID());
        return bbqChicken;
    }

    @Override
    public Pizza createBuildYourOwn() {
        CustomPizza custom = new CustomPizza(Crust.HANDTOSSED, Size.MEDIUM, ImageResID.NY_CUSTOM.getImageResID());
        return custom;
    }
}
