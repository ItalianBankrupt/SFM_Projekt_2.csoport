package com.spa.demo.frontend.Restaurant;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Identification;
import com.spa.demo.backend.IdentificationRepository;
import com.spa.demo.backend.Restaurant;
import com.spa.demo.backend.RestaurantRepository;
import com.spa.demo.frontend.Cassa.Models.PersonId;
import com.spa.demo.frontend.Cassa.Utils.PopUpWindows;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.*;


@Component
public class restMainScreenController {


    private ConfigurableApplicationContext context;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private IdentificationRepository identificationRepository;

    @FXML
    private TextField noteBox;

    Set<String> bandIDs = new HashSet<>();

    @FXML
    private Label bandID1;

    @FXML
    private Label bandID2;

    @FXML
    private Label bandID3;

    @FXML
    private Label bandID4;

    @FXML
    private Label bandID5;

    @FXML
    private Label bandValue1;

    @FXML
    private Label bandValue2;

    @FXML
    private Label bandValue3;

    @FXML
    private Label bandValue4;

    @FXML
    private Label bandValue5;

    @FXML
    private GridPane panelGridPane;

    @FXML
    private Spinner<Integer> removeSpinner;

    @FXML
    private TextField idBox;

    @FXML
    private ComboBox<String> dropDownID;


    @FXML
    private TableColumn<CheckOutFinal, Integer> checkoutAmount;

    @FXML
    private TableColumn<CheckOutFinal, String> checkoutFood;

    @FXML
    private TableColumn<CheckOutFinal, String> checkoutID;

    @FXML
    private TableColumn<CheckOutFinal, Integer> checkoutPrice;

    @FXML
    private Label checkoutSum;

    @FXML
    private TableView<CheckOutFinal> checkoutTable;

    @FXML
    private TableView<Checkout> smallBasket;

    @FXML
    private TableColumn<Checkout, String> smallBasketFood;

    @FXML
    private TableColumn<Checkout, Integer> smallBasketAmount;

    @FXML
    private TableColumn<Checkout, Integer> smallBasketPrice;

    void loadFoodGrid(String itemType) throws IOException {

        panelGridPane.getChildren().clear();
        panelGridPane.setPrefWidth(ScrollPane.USE_COMPUTED_SIZE);
        context = SpringManager.getApplicationContext();
        restaurantRepository = context.getBean(RestaurantRepository.class);
        List<Restaurant> items = restaurantRepository.findByType(itemType);

        int col = 0;
        int row = 0;

        for (Restaurant item : items) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RestGUI/ItemBox.fxml"));
            AnchorPane pane = loader.load();
            ItemBoxController controller = loader.getController();
            String name = item.getName();
            int price = item.getPrice();
            Label foodName = controller.getProdName();
            foodName.setText(name);
            ImageView imageView = controller.getProdImage();
            imageView.fitWidthProperty().bind(controller.getCardForm().widthProperty());
            imageView.setImage(new Image(getClass().getResourceAsStream("/fxml/RestGUI/sampleImages/" + name + ".jpg")));
            Label foodPrice = controller.getProdPrice();
            foodPrice.setText(price+"Ft");
            pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            panelGridPane.add(pane, col, row);
            col++;
            if (col == 2){
                col = 0;
                row++;
            }
        }
    }

    @FXML
    void loadCoffe(ActionEvent event) throws IOException{
        loadFoodGrid("Kave");
    }

    @FXML
    void loadDessert(ActionEvent event) throws IOException{
        loadFoodGrid("Desszert");
    }

    @FXML
    void loadDrinks(ActionEvent event) throws IOException{
        loadFoodGrid("Udito");
    }

    @FXML
    void loadMainCourse(ActionEvent event) throws IOException{
        loadFoodGrid("Foetel");
    }

    @FXML
    void loadPreFood(ActionEvent event) throws IOException{
        loadFoodGrid("Eloetel");
    }

    @FXML
    void loadSoup(ActionEvent event) throws IOException{
        loadFoodGrid("Leves");
    }

    @FXML
    void loadFish(ActionEvent event) throws IOException{
        loadFoodGrid("Hal_etel");
    }

    @FXML
    void loadPasta(ActionEvent event) throws IOException{
        loadFoodGrid("Teszta");
    }

    @FXML
    void loadAlcohol(ActionEvent event) throws IOException{
        loadFoodGrid("Sor");
    }

    @FXML
    void idCheck(ActionEvent event) {

        ConfigurableApplicationContext context = SpringManager.getApplicationContext();
        identificationRepository = context.getBean(IdentificationRepository.class);
        List<Identification> IDs = identificationRepository.findAll();

        PersonId personId = null;

        for (Identification id : IDs) {
            if(Objects.equals(id.getPersonId(), idBox.getText())) {
                personId = new PersonId(id);
            }
        }

        if(personId != null) {


            List<Label> bandIDLabels = List.of(bandID1, bandID2, bandID3, bandID4, bandID5);
            List<Label> bandValueLabels = List.of(bandValue1, bandValue2, bandValue3, bandValue4, bandValue5);


            if (!bandIDs.contains(idBox.getText()) && bandIDs.size() < bandIDLabels.size()) {
                bandIDs.add(idBox.getText());
                int index = bandIDs.size()-1;
                bandIDLabels.get(index).setText(idBox.getText());
                bandValueLabels.get(index).setText(personId.getBalance().getValue().toString());
                dropDownID.getItems().add(idBox.getText());

            }

        }

        if(idBox.getText().isEmpty() || personId == null) {
            String contentText = "Hibás azonosító";
            String headerText = "Azonosító hiba";
            String title = "Hiba";
            PopUpWindows.AlertWindow(contentText, headerText, title);
        }


        idBox.setText("");
    }

    @FXML
    void sendNoteToCart(ActionEvent event) {
        String poz = noteBox.getText();
        CheckOutFinal item = CartManager.getInstance().getCheckOutItems().get(Integer.parseInt(poz));
        item.setID(dropDownID.getValue());
        CartManager.getInstance().getCheckOutItems().set(Integer.parseInt(poz), item);
    }

    public void initialize() {

        CartManager.getInstance().getCartItems().addListener((ListChangeListener<? super Checkout>) (change) -> {
            updateBasketTable();
        });
        CartManager.getInstance().getCheckOutItems().addListener((ListChangeListener<? super CheckOutFinal>) (change) -> {
            updateBasketTable();
            checkoutSum();
        });

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
        valueFactory.setValue(1);
        removeSpinner.setValueFactory(valueFactory);
    }

    public void updateBasketTable() {
        ObservableList<Checkout> cartItems = CartManager.getInstance().getCartItems();
        ObservableList<CheckOutFinal> checkOutItems = CartManager.getInstance().getCheckOutItems();

        checkoutTable.setItems(checkOutItems);
        smallBasket.setItems(cartItems);

        checkoutFood.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        checkoutAmount.setCellValueFactory(new PropertyValueFactory<>("foodAmount"));
        checkoutPrice.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));
        checkoutID.setCellValueFactory(new PropertyValueFactory<>("ID"));

        smallBasketFood.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        smallBasketAmount.setCellValueFactory(new PropertyValueFactory<>("foodAmount"));
        smallBasketPrice.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));
    }

    public void checkoutSum(){
        int finalCost = 0;
        ObservableList<Checkout> cartItems = CartManager.getInstance().getCartItems();
        for (Checkout item : cartItems) {
            finalCost = finalCost + item.getFoodPrice();
        }
        checkoutSum.setText(finalCost+"Ft");
    }

    @FXML
    void removeItem(ActionEvent event){
        ObservableList<Checkout> cartItems = CartManager.getInstance().getCartItems();
        if (!cartItems.isEmpty() && removeSpinner.getValue() <= cartItems.size()) {
            int index = cartItems.size() - (cartItems.size() - removeSpinner.getValue()+1);
            cartItems.remove(index);
            CartManager.getInstance().getCheckOutItems().remove(index);
        } else {
            System.out.println("The cart is empty. No item to remove.");
        }
    }

    @FXML
    void clearScene(ActionEvent event) {
        CartManager.getInstance().getCartItems().clear();
        CartManager.getInstance().getCheckOutItems().clear();
        List<Label> bandIDLabels = List.of(bandID1, bandID2, bandID3, bandID4, bandID5);
        List<Label> bandValueLabels = List.of(bandValue1, bandValue2, bandValue3, bandValue4, bandValue5);
        for(int iteration = 0; iteration < 5; iteration++) {
            bandIDLabels.get(iteration).setText("");
            bandValueLabels.get(iteration).setText("");
        }
        dropDownID.getItems().clear();
        bandIDs.clear();
    }

    @FXML
    void checkoutPay(ActionEvent event) {
        ConfigurableApplicationContext context = SpringManager.getApplicationContext();
        identificationRepository = context.getBean(IdentificationRepository.class);
        List<Identification> IDs = identificationRepository.findAll();
        ObservableList<CheckOutFinal> items = CartManager.getInstance().getCheckOutItems();
        Map<String,Integer> idBalance = new HashMap<>();
        for(CheckOutFinal item : items) {
            String id = item.getID();
            Integer price = item.getFoodPrice();
            idBalance.put(id, idBalance.getOrDefault(id, 0) + price);
        }

        for(Identification ID : IDs) {
            if(idBalance.containsKey(ID.getPersonId())){
                if(idBalance.get(ID.getPersonId()) < ID.getMoney())
                    identificationRepository.updateByPersonId(ID.getPersonId(),ID.getMoney()-idBalance.get(ID.getPersonId()));
            }
            else{
                System.out.println("Error");
            }
            System.out.println(ID.getMoney());
        }
        clearScene(event);
    }
}
