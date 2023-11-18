import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        //image logo pirate
        Image image = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\pirateLogo3.png"));  
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);  
        imageView.setFitHeight(260); 
        //imageView.setX(678);
        imageView.setX(550);
        imageView.setY(60);

        //background image
        Image image2 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\orangeBack.jpg"));  
        ImageView backImage = new ImageView(image2);
        backImage.setFitWidth(1540);
        backImage.setFitHeight(785);

        //portal: buttons that move you on the webpage to four functions 
        Image image3 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\magnGlass.png"));  
        ImageView magniGlass = new ImageView(image3);
        magniGlass.setFitWidth(30);
        magniGlass.setFitHeight(37);
        Button magnBtn = new Button();
        magnBtn.setGraphic(magniGlass);
        magnBtn.getStyleClass().addAll("portals", "sBtn");
        //magnBtn.setMinSize(25,25);
        Image image4 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\recents.png"));  
        ImageView recents = new ImageView(image4);
        recents.setFitWidth(30);
        recents.setFitHeight(37);
        Button recBtn = new Button();
        recBtn.setGraphic(recents);
        recBtn.getStyleClass().addAll("portals", "recBtn");
        Image image5 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\categories.png"));  
        ImageView categories = new ImageView(image5);
        categories.setFitWidth(30);
        categories.setFitHeight(37);
        Button catBtn = new Button();
        catBtn.setGraphic(categories);
        catBtn.getStyleClass().addAll("portals", "browseBtn");
        Image image6 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\fire.png"));  
        ImageView fire = new ImageView(image6);
        fire.setFitWidth(30);
        fire.setFitHeight(37);
        Button fireBtn = new Button();
        fireBtn.setGraphic(fire);
        fireBtn.getStyleClass().addAll("portals", "fireBtn");

        //4 vboxes to add labels under them
        Label magnLabel = new Label("Search Torrents"); magnLabel.getStyleClass().add("portals");
        Label catLabel = new Label("Browse Torrents"); catLabel.getStyleClass().add("portals");
        Label recLabel= new Label("Recent Torrents"); recLabel.getStyleClass().add("portals");
        Label fireLabel = new Label("Top 100"); fireLabel.getStyleClass().add("portals");
        VBox magn = new VBox(magnBtn, magnLabel);
        VBox cat = new VBox(catBtn, catLabel);
        VBox rec = new VBox(recBtn, recLabel);
        VBox fireV = new VBox(fireBtn, fireLabel);
        magn.setSpacing(10); magn.setAlignment(Pos.CENTER);
        cat.setSpacing(10); cat.setAlignment(Pos.CENTER);
        rec.setSpacing(10); rec.setAlignment(Pos.CENTER);
        fireV.setSpacing(10); fireV.setAlignment(Pos.CENTER);

        //hbox for button portals
        HBox portal = new HBox(magn, cat, rec, fireV);
        portal.setSpacing(50);
        portal.setAlignment(Pos.CENTER);

        // Top: Search Bar
        TextField searchBar = new TextField();
        Button searchButton = new Button("Search");
        HBox h = new HBox(searchBar, searchButton);
        searchBar.setPromptText("Pirate Search");
        searchBar.setPrefWidth(775);
        searchBar.setPrefHeight(58);
        searchButton.setPrefHeight(58);
        h.setAlignment(Pos.CENTER);
        searchBar.getStyleClass().add("search-bar");
        searchButton.getStyleClass().add("search-btn");
        //searchBar.getStyleClass().add("search-bar-text");

        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("anchor-pane");

        VBox searchAndBtns = new VBox(portal, h);
        searchAndBtns.setSpacing(40.0);


        // Wrap the HBox and then the portal hbox with AnchorPane
    
        AnchorPane.setTopAnchor(searchAndBtns, 360.0);
        AnchorPane.setLeftAnchor(searchAndBtns, 0.0);
        AnchorPane.setRightAnchor(searchAndBtns, 0.0);

    
        // Add the AnchorPane to the center region of the BorderPane
        root.getChildren().addAll(backImage, imageView, searchAndBtns);

        Scene scene = new Scene(root, 1540, 785);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Center HBox with Offset");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


        

    