package com.spa.demo.frontend.Restaurant;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Restaurant;
import com.spa.demo.backend.RestaurantRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.List;



@Component
public class identificationController {


    private ConfigurableApplicationContext context;

    @Autowired
    private RestaurantRepository restaurantRepository;

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
    private TextField idBox;

    @FXML
    private TableView<?> smallBasket;

    @FXML
    private ScrollPane scrollPane;


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
            String type = item.getType();
            int price = item.getPrice();

            Label foodName = controller.getProdName();
            foodName.setText(name);
            System.out.println(name);
            ImageView imageView = controller.getProdImage();
            imageView.fitWidthProperty().bind(controller.getCardForm().widthProperty());
            imageView.setImage(new Image(getClass().getResourceAsStream("/fxml/RestGUI/backg.png")));
            Label foodPrice = controller.getProdPrice();
            foodPrice.setText(String.valueOf(price));
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
    }

    @FXML
    void loadSoup(ActionEvent event) throws IOException{
    }

    @FXML
    void idCheck(ActionEvent event) {
        //ID ellenőrzés
    }

}
