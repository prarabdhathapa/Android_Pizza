package gui;

import src.pizzeria.Order;
import src.pizzeria.Pizza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlacedOrdersViewController {

    private PizzeriaController mainController;
    private Stage stage;
    private Stage primaryStage;
    private Scene primaryScene;

    private ArrayList<Order> orders;
    private ArrayList<Integer> ids = new ArrayList<>();
    private static int exportCount = 1;
    private static final double TAX_INCLUDED = 1.06625;

    @FXML
    private Button exportButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button fillButton; // Fill screen with info
    @FXML
    private Button backButton;  // Button to go back to the main menu
    @FXML
    private ComboBox<Integer> idComboBox;
    @FXML
    private TextField totalField;
    @FXML
    private ListView<Pizza> pizzasView;

    @FXML
    private void initialize() {
        exportButton.setOnAction(event -> {
            exportOrders();
        });
        cancelButton.setOnAction(event -> {
            cancelOrder();
        });
        fillButton.setOnAction(event -> {
            fillPage(0);
        });
        backButton.setOnAction(event -> {
            goBack();
        });
    }

    @FXML
    private void handleListChange() {
        Integer selectedID = idComboBox.getValue();
        if (selectedID != null) {
            fillPage(selectedID);
        }
    }

    private void exportOrders() {
        if (!orders.isEmpty()) {
            String path = "orders-" + exportCount + ".txt"; // Starts from "orders-1.txt"
            exportCount++;
            File export = new File(path);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(export))) {
                for (int i = 0; i < orders.size(); i++) {
                    int orderNum = orders.get(i).getNumber();
                    ArrayList<Pizza> pizzas = orders.get(i).getPizzas();
                    double total = mainController.priceOfPizzas(pizzas) * TAX_INCLUDED;

                    writer.write("Order Number: " + orderNum);
                    writer.newLine();
                    writer.write("Total Price: $" + String.format("%.2f", total));
                    writer.newLine();
                    for (int j = 0; j < pizzas.size(); j++) {
                        writer.write("Pizza #" + j + 1 + ": " + pizzas.get(j).toString());
                        writer.newLine();
                    }
                    writer.write("----------------------------------------------------------");
                    writer.newLine();

                    clearPage();
                    orders.clear();         //clears all the orders after exporting
                    mainController.setOrders(orders);
                    ids.clear();
                    idComboBox.getItems().clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void cancelOrder() {
        Integer selectedID = idComboBox.getValue();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getNumber() == selectedID) {
                orders.remove(i);
                ids.remove(selectedID);
                clearPage();
                fillIDs();
                mainController.setOrders(orders);
                return;
            }
        }
        fillIDs();
    }

    private void clearPage() {
        idComboBox.getItems().clear();
        totalField.setText("$0.00"); // set total to $0.00
        pizzasView.getItems().clear(); // clear list

    }

    private void fillPage(Integer selectedID) {
        if (mainController.hasOrders()) {
            fillIDs();
            ArrayList<Pizza> pizzas;
            if (selectedID == 0) { // just use the first order
                pizzas = new ArrayList<>(orders.get(0).getPizzas());
                idComboBox.setValue(orders.get(0).getNumber());
            } else { // find the order specified by the id
                Order order = findOrder(selectedID);
                pizzas = new ArrayList<>(order.getPizzas());
            }
            double total = mainController.priceOfPizzas(pizzas) * TAX_INCLUDED;
            totalField.setText("$" + String.format("%.2f", total)); // fill total
            pizzasView.getItems().clear();
            if (!pizzas.isEmpty()) { // Only update if pizzas is not empty
                pizzasView.getItems().addAll(pizzas);
            }
        }
    }

    private Order findOrder(Integer selectedID) {
        for (Order order : orders) {
            if (order.getNumber() == selectedID.intValue()) {
                return order;
            }
        }
        return null;
    }

    private void fillIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Order order : orders) {
            ids.add(order.getNumber());
        }
        for (Integer id : ids) {
            if (!idComboBox.getItems().contains(id)) {
                idComboBox.getItems().add(id);
            }
        }
    }


    public void setOrders(ArrayList<Order> orders) {
        this.orders = new ArrayList<>(orders);
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