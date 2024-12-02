package gui;

import gui.pizzeria.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class PizzaViewController {

    private PizzeriaController mainController;
    private Stage stage;
    private Stage primaryStage;
    private Scene primaryScene;
    private Pizza currentPizza;
    private Image chiDeluxe = new Image(getClass().getResourceAsStream("chicago-deluxe.png"), 200, 200, false, false);
    private Image nyDeluxe = new Image(getClass().getResourceAsStream("newyork-deluxe.png"), 200, 200, false, false);
    private Image chiBBQCHK = new Image(getClass().getResourceAsStream("chicago-bbqchicken.png"), 200, 200, false, false);
    private Image nyBBQCHK = new Image(getClass().getResourceAsStream("newyork-bbqchicken.png"), 200, 200, false, false);
    private Image chiMeatzza = new Image(getClass().getResourceAsStream("chicago-meatzza.png"), 200, 200, false, false);
    private Image nyMeatzza = new Image(getClass().getResourceAsStream("newyork-meatzza.png"), 200, 200, false, false);
    private Image chiCustom = new Image(getClass().getResourceAsStream("chicago-custom.png"), 200, 200, false, false);
    private Image nyCustom = new Image(getClass().getResourceAsStream("newyork-custom.png"), 200, 200, false, false);
    private final int MAX_TOPPINGS = 7;

    @FXML
    private Button addTopping;
    @FXML
    private Button removeTopping;
    @FXML
    private Button backButton;  // Button to go back to the main menu
    @FXML
    private ListView<Toppings> addToppingsView;
    @FXML
    private ListView<Toppings> selectedToppingsView;
    @FXML
    private RadioButton chicago;
    @FXML
    private RadioButton newYork;
    @FXML
    private RadioButton smallButton;
    @FXML
    private RadioButton medButton;
    @FXML
    private RadioButton largeButton;
    @FXML
    private TextField crustDisplay;
    @FXML
    private TextField priceDisplay;
    @FXML
    private ComboBox<String> pizzaType;
    @FXML
    private ToggleGroup styleToggleGroup;
    @FXML
    private ToggleGroup sizeToggleGroup;
    @FXML
    private ImageView pizzaImage;
    @FXML
    private Button orderButton;

    @FXML
    private void initialize() {
        createStyleButtonGroup();
        setSizeToggleGroup();
        fillPizzaType();
        fillToppingsView();
        removeTopping.setDisable(true);
        handlePizza("styleChange");
        sizeToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> handlePizza("sizeChange")); // different pizza size -> new price
        styleToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> handlePizza("styleChange")); // different pizza style -> new image
        pizzaType.valueProperty().addListener((observable, oldValue, newValue) -> handlePizza("typeChange")); // different pizza type -> new price + image
        orderButton.setOnAction(event -> {
            addToOrder();
        });
        backButton.setOnAction(event -> {
            goBack();
        });
    }

    private void addToOrder() {
        mainController.addPizzaToOrder(currentPizza);
        showPopup("Order Added", "The pizza has been added to your order.");
    }

    private void handlePizza(String change) {
        updateCrustDisplay();
        change = change.trim();
        if (change.equals("typeChange")) {
            switch (pizzaType.getValue().trim()) {
                case "Build Your Own":
                    addTopping.setDisable(false);
                    removeTopping.setDisable(false);
                    break;
                default:
                    addTopping.setDisable(true);
                    removeTopping.setDisable(true);
                    break;
            }
        }
        updateScreen();
    }

    private void updateCrustDisplay() {
        String style = ((RadioButton) styleToggleGroup.getSelectedToggle()).getText();
        String type = pizzaType.getValue();
        Crust crust = getCrust(style, type);
        crustDisplay.setText(crust.toString());
    }

    private Crust getCrust(String style, String type) {
        if (style.equals("Chicago")) {
            switch (type) {
                case "Deluxe":
                    return Crust.DEEPDISH;
                case "BBQ Chicken", "Build Your Own":
                    return Crust.PAN;
                case "Meatzza":
                    return Crust.STUFFED;
            }
        } else if (style.equals("New York")) {
            switch (type) {
                case "Deluxe":
                    return Crust.BROOKLYN;
                case "BBQ Chicken":
                    return Crust.THIN;
                case "Meatzza", "Build Your Own":
                    return Crust.HANDTOSSED;
            }
        }
        return null;
    }

    private void updateScreen() {
        selectedToppingsView.getItems().clear();
        currentPizza = createChicagoOrNewYork(); // creates the pizza based on selected type and size
        currentPizza.setSize(getSelectedSize()); // sets the size of the pizza
        switch (pizzaType.getValue().trim()) {
            case "Deluxe":
                selectedToppingsView.getItems().addAll(currentPizza.getToppings());
                if (chicago.isSelected()) {
                    pizzaImage.setImage(chiDeluxe);
                } else if (newYork.isSelected()) {
                    pizzaImage.setImage(nyDeluxe);
                }
                break;
            case "BBQ Chicken":
                selectedToppingsView.getItems().addAll(currentPizza.getToppings());
                if (chicago.isSelected()) {
                    pizzaImage.setImage(chiBBQCHK);
                } else if (newYork.isSelected()) {
                    pizzaImage.setImage(nyBBQCHK);
                }
                break;
            case "Meatzza":
                selectedToppingsView.getItems().addAll(currentPizza.getToppings());
                if (chicago.isSelected()) {
                    pizzaImage.setImage(chiMeatzza);
                } else if (newYork.isSelected()) {
                    pizzaImage.setImage(nyMeatzza);
                }
                break;
            case "Build Your Own":
                if (chicago.isSelected()) {
                    pizzaImage.setImage(chiCustom);
                } else if (newYork.isSelected()) {
                    pizzaImage.setImage(nyCustom);
                }
                break;
        }
        priceDisplay.setText(String.format("%.2f", currentPizza.price())); // sets the price on screen
    }

    //this helper grabs the radio button for size that is currently selected
    private Size getSelectedSize() {
        Toggle selectToggle = sizeToggleGroup.getSelectedToggle();

        if (selectToggle.equals(smallButton)) {
            return Size.SMALL;
        }
        if (selectToggle.equals(medButton)) {
            return Size.MEDIUM;
        }
        if (selectToggle.equals(largeButton)) {
            return Size.LARGE;
        }
        return null;
    }

    private Pizza createChicagoOrNewYork() {
        if (chicago.isSelected()) {
            PizzaFactory pizzaFactory = new ChicagoPizza();
            return createPizza(pizzaFactory);
        } else if (newYork.isSelected()) {
            PizzaFactory pizzaFactory = new NewYorkPizza();
            return createPizza(pizzaFactory);
        }
        return null;
    }

    private Pizza createPizza(PizzaFactory pizzaFactory) {
        return switch (pizzaType.getValue().trim()) {
            case "Deluxe" -> pizzaFactory.createDeluxe();
            case "BBQ Chicken" -> pizzaFactory.createBBQChicken();
            case "Meatzza" -> pizzaFactory.createMeatzza();
            case "Build Your Own" -> pizzaFactory.createBuildYourOwn();
            default -> null;
        };
    }

    private void showPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);

        alert.showAndWait();
    }


    @FXML
    private void handleAddTopping() {
        Toppings selectedTopping = addToppingsView.getSelectionModel().getSelectedItem();       //highlighted section of the list view for toppings after click

        if (selectedTopping != null) {
            selectedToppingsView.getItems().addAll(selectedTopping);        //adds Topping to selected list view
            removeTopping.setDisable(false);
            addToppingsView.getItems().remove(selectedTopping);             //remove Topping from topping list view
            if (selectedToppingsView.getItems().size() >= MAX_TOPPINGS) {       //disables button once 7 toppings are added
                addTopping.setDisable(true);
            }
            currentPizza.addTopping(selectedTopping);
            priceDisplay.setText(String.format("%.2f", currentPizza.price())); // sets the price on screen
        }
    }

    @FXML
    private void handleRemoveTopping() {
        Toppings selectedTopping = selectedToppingsView.getSelectionModel().getSelectedItem();       //highlighted section of the list view for toppings after click

        if (selectedTopping != null) {
            addToppingsView.getItems().addAll(selectedTopping);        //adds Topping to selected list view
            selectedToppingsView.getItems().remove(selectedTopping);             //remove Topping from topping list view
            if (selectedToppingsView.getItems().size() < MAX_TOPPINGS) {
                addTopping.setDisable(false);
            }
            currentPizza.removeTopping(selectedTopping);
            priceDisplay.setText(String.format("%.2f", currentPizza.price())); // sets the price on screen
        }
        removeTopping.setDisable(selectedToppingsView.getItems().isEmpty());        //sets the button to disable of the selected toppins list view is empty
    }

    private void createStyleButtonGroup() {
        styleToggleGroup = new ToggleGroup();
        chicago.setToggleGroup(styleToggleGroup);
        newYork.setToggleGroup(styleToggleGroup);
    }

    private void setSizeToggleGroup() {
        sizeToggleGroup = new ToggleGroup();
        smallButton.setToggleGroup(sizeToggleGroup);
        medButton.setToggleGroup(sizeToggleGroup);
        largeButton.setToggleGroup(sizeToggleGroup);
    }

    private void fillPizzaType() {
        String[] pizzaTypes = {
                "Deluxe",
                "BBQ Chicken",
                "Meatzza",
                "Build Your Own"
        };
        pizzaType.getItems().addAll(pizzaTypes);
        pizzaType.setValue("Build Your Own");
    }

    private void fillToppingsView() {
        addToppingsView.getItems().addAll(Toppings.values());
    }

    private void goBack() {
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("RU Pizzeria");
        primaryStage.show();
    }

    public void setMainController(PizzeriaController controller,
                                  Stage stage,
                                  Stage primaryStage,
                                  Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }
}