package gui;

import src.pizzeria.Order;
import src.pizzeria.Pizza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PizzeriaController {
    private Stage primaryStage; // the main window
    private Scene primaryScene; // the main screen

    private ArrayList<Order> orders; // the placed orders
    private Order order; // the current order
    private int id = 1; // the running serial number starting from 1

    private Image pizzaImage = new Image(getClass().getResourceAsStream("pizza.png"), 150, 160, false, false);
    private Image currentOrderImage = new Image(getClass().getResourceAsStream("order.png"), 150, 160, false, false);
    private Image placedOrdersImage = new Image(getClass().getResourceAsStream("checkout.png"), 150, 160, false, false);

    @FXML
    private ImageView pizzaViewImage;
    @FXML
    private ImageView currentOrderViewImage;
    @FXML
    private ImageView placedOrdersViewImage;

    public PizzeriaController() {
        orders = new ArrayList<>();
    }

    public void placeOrder() {
        orders.add(order);
        id += 1;
        order = new Order(id);
    }

    public void addPizzaToOrder(Pizza pizza) {
        order.addPizza(pizza);
    }

    public Order getFirst() {
        return orders.get(0);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public Order getOrder() {
        return order;
    }

    public ArrayList<Order> setOrders(ArrayList<Order> orders) {
        return this.orders = orders;
    }


    public int getID() {
        return id;
    }

    public void removePizza(Pizza removedPizza) {
        order.removePizza(removedPizza);
    }

    public void removeOrder(Order removedOrder) {
        orders.remove(removedOrder);
    }

    public boolean hasPizzas() {
        return !order.getPizzas().isEmpty();
    }

    public boolean hasOrders() {
        return !orders.isEmpty();
    }

    public void clearOrder() {
        order = new Order(id);
    }

    public double priceOfPizzas(ArrayList<Pizza> pizzas) {
        double subtotal = 0.0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        return subtotal;
    }

    @FXML
    private Button ordersPlaced;
    @FXML
    private Button createPizza;
    @FXML
    private Button currentOrder;

    //1) create a method that when event called, opens new class
    //2) make sure all the fx:id and fx:controller matches up for the fxml file (each view gets their own controller)
    //3) make sure that the "onAction" and fx:id matches for the button press on the view

    /**
     * Set the reference of the stage and scene.
     *
     * @param stage the window used to display the scene.
     * @param scene the scene set to be on screen.
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    @FXML
    private void initialize() {
        order = new Order(id);
        pizzaViewImage.setImage(pizzaImage);
        currentOrderViewImage.setImage(currentOrderImage);
        placedOrdersViewImage.setImage(placedOrdersImage);
        ordersPlaced.setOnAction(event -> {
            openPlacedOrdersView();
        });
        createPizza.setOnAction(event -> {
            openPizzaView();
        });
        currentOrder.setOnAction(event -> {
            openCurrentOrderView();
        });
    }

    private void openPlacedOrdersView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("placed-orders-view.fxml"));
            Parent root = loader.load();          //made parent instead of own class (OrdersController)
            Scene placedOrdersViewScene = new Scene(root);
            primaryStage.setScene(placedOrdersViewScene);
            primaryStage.setTitle("Total Orders Placed");
            PlacedOrdersViewController placedOrdersViewController = loader.getController();
            placedOrdersViewController.setMainController(this, primaryStage, primaryStage, primaryScene);
            placedOrdersViewController.setOrders(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openPizzaView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
            Parent root = loader.load();
            Scene pizzaViewScene = new Scene(root);
            primaryStage.setScene(pizzaViewScene);
            primaryStage.setTitle("Create Pizza");
            PizzaViewController pizzaViewController = loader.getController();
            pizzaViewController.setMainController(this, primaryStage, primaryStage, primaryScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openCurrentOrderView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("current-order-view.fxml"));
            Parent root = loader.load();
            Scene currentOrderViewScene = new Scene(root);
            primaryStage.setScene(currentOrderViewScene);
            primaryStage.setTitle("Current Order");
            CurrentOrderViewController currentOrderViewController = loader.getController();
            currentOrderViewController.setMainController(this, primaryStage, primaryStage, primaryScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}