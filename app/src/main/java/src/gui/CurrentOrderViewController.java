package gui;

import src.pizzeria.Pizza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CurrentOrderViewController {

    private PizzeriaController mainController;
    private Stage stage;
    private Stage primaryStage;
    private Scene primaryScene;
    private boolean infoFilled;

    @FXML
    private Button fillButton; // Fill screen with info
    @FXML
    private Button clearButton; // Clear order
    @FXML
    private Button removeButton; // Remove selected pizza
    @FXML
    private Button completeButton; // Complete order
    @FXML
    private Button backButton;  // Button to go back to the main menu
    @FXML
    private TextField idField;
    @FXML
    private TextField subtotalField;
    @FXML
    private TextField taxField;
    @FXML
    private TextField totalField;
    @FXML
    private ListView<Pizza> pizzasView;

    @FXML
    private void initialize() {
        infoFilled = false;
        completeButton.setOnAction(event -> {
            placeOrder();
        });
        removeButton.setOnAction(event -> {
            removePizza();
        });
        fillButton.setOnAction(event -> {
            fillPage();
        });
        clearButton.setOnAction(event -> {
            clearPage();
        });
        backButton.setOnAction(event -> {
            goBack();
        });
    }

    private void placeOrder() {
        if (infoFilled) {
            mainController.placeOrder();
            clearPage();
        }
    }

    private void removePizza() {
        Pizza selectedPizza = pizzasView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            pizzasView.getItems().remove(selectedPizza);
            mainController.removePizza(selectedPizza);
            if (mainController.getOrder().getPizzas().isEmpty()) {
                clearPage();
            } else {
                fillFields();
            }
        }
    }

    private void fillPage() {
        if (mainController.hasPizzas()) { // not a fresh controller, so there is data to put
            idField.setText(String.valueOf(mainController.getID())); // fill order #
            fillFields();
            pizzasView.getItems().addAll(mainController.getOrder().getPizzas()); // fill list
            infoFilled = true;
        }
    }

    private void fillFields() {
        double subtotal = mainController.priceOfPizzas(mainController.getOrder().getPizzas());
        double tax = subtotal * 0.06625;
        double total = subtotal + tax;
        subtotalField.setText("$" + String.format("%.2f", subtotal)); // fill subtotal
        taxField.setText("$" + String.format("%.2f", tax)); // fill tax
        totalField.setText("$" + String.format("%.2f", total)); // fill total
    }

    private void clearPage() {
        subtotalField.setText("$0.00"); // set subtotal to $0.00
        taxField.setText("$0.00"); // set tax to $0.00
        totalField.setText("$0.00"); // set total to $0.00
        pizzasView.getItems().clear(); // clear list
        mainController.clearOrder();
        infoFilled = false;
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
