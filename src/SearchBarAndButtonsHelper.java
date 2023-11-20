import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

//this class should be called whenever the search bar and buttons needs to be used 
//returns vbox object

public class SearchBarAndButtonsHelper {
    public static VBox createBar() throws FileNotFoundException{
         Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        Image image2 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\orangeBack.jpg"));  
        ImageView backImage = new ImageView(image2);
        backImage.setFitWidth(1540);
        backImage.setFitHeight(785);
        
        // Top: Search Bar
        //made the height and other elements scale by 0.75 compared to App.java 
        TextField searchBar = new TextField();
        Button searchButton = new Button(" Search ");
        searchBar.setPromptText("Pirate Search");
        //searchBar.setPrefWidth(775);
                searchBar.setPrefWidth(1530);
        searchBar.setPrefHeight(43.5);
        //searchBar.setMaxWidth(775);
        searchBar.getStyleClass().add("search-bar");
        searchButton.getStyleClass().add("search-btn");
        searchButton.setPrefHeight(40);
        searchButton.setPickOnBounds(true);
         //x icon in searchBar
        Image image7 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\xIcon.png"));  
        ImageView x = new ImageView(image7);
        x.setFitWidth(30);
        x.setFitHeight(30);
        Button xBtn = new Button();
        xBtn.setGraphic(x);
        xBtn.getStyleClass().addAll("transparent-background-btn");
        xBtn.setPickOnBounds(true);
        HBox h = new HBox(xBtn, searchButton); 
        h.setSpacing(0);
        //h.setAlignment(Pos.BASELINE_RIGHT);
        h.setMaxWidth(400); 
        h.setAlignment(Pos.CENTER);
        //searchBar.setAlignment(Pos.CENTER_LEFT);
        // StackPane to stack the searchButton on top of the searchBar
        StackPane searchStack = new StackPane(searchBar, h);
        StackPane.setAlignment(h, Pos.CENTER_RIGHT);
      
        //searchStack.getStyleClass().add("anchor-pane");
        StackPane.setMargin(h, new Insets(0,-(0.08 * bounds.getWidth()),0,0));
        //searchStack.setMaxWidth(775); // Set a maximum width

        //portal: buttons that move you on the webpage to four functions 
        Image image3 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\magnGlass.png"));  
        ImageView magniGlass = new ImageView(image3);
        magniGlass.setFitWidth(22.5);
        magniGlass.setFitHeight(27.75);
        Button magnBtn = new Button();
        magnBtn.setGraphic(magniGlass);
        magnBtn.getStyleClass().addAll("portals", "sBtn");
        //magnBtn.setMinSize(25,25);
        Image image4 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\recents.png"));  
        ImageView recents = new ImageView(image4);
        recents.setFitWidth(22.5);
        recents.setFitHeight(27.75);
        Button recBtn = new Button();
        recBtn.setGraphic(recents);
        recBtn.getStyleClass().addAll("portals", "recBtn");
        Image image5 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\categories.png"));  
        ImageView categories = new ImageView(image5);
        categories.setFitWidth(22.5);
        categories.setFitHeight(27.75);
        Button catBtn = new Button();
        catBtn.setGraphic(categories);
        catBtn.getStyleClass().addAll("portals", "browseBtn");
        Image image6 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\fire.png"));  
        ImageView fire = new ImageView(image6);
        fire.setFitWidth(22.5);
        fire.setFitHeight(27.75);
        Button fireBtn = new Button();
        fireBtn.setGraphic(fire);
        fireBtn.getStyleClass().addAll("portals", "fireBtn");

        //4 vboxes to add labels under them
        Label magnLabel = new Label(" Search \nTorrents"); magnLabel.getStyleClass().add("portals");
        Label catLabel = new Label(" Browse \nTorrents"); catLabel.getStyleClass().add("portals");
        Label recLabel= new Label(" Recent \nTorrents"); recLabel.getStyleClass().add("portals");
        Label fireLabel = new Label("Top \n100"); fireLabel.getStyleClass().add("portals");
        VBox magn = new VBox(magnBtn, magnLabel);
        VBox cat = new VBox(catBtn, catLabel);
        VBox rec = new VBox(recBtn, recLabel);
        VBox fireV = new VBox(fireBtn, fireLabel);
        magn.setSpacing(8.5); magn.setAlignment(Pos.CENTER);
        cat.setSpacing(8.5); cat.setAlignment(Pos.CENTER);
        rec.setSpacing(8.5); rec.setAlignment(Pos.CENTER);
        fireV.setSpacing(8.5); fireV.setAlignment(Pos.CENTER);

         // Event listener to toggle visibility based on text in the search bar
         searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                h.setVisible(false);
            } else {
                h.setVisible(true);
            }
        });

        //event handler for x button
         xBtn.setOnAction(event -> {
            searchBar.setText("");
        });     
        searchButton.setOnAction(event -> {
            if(!searchBar.getText().equals("")){
                try {
                    SceneManager.showSearchScene();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } 
        }); 
        //event handler for "recent torrents" button
         recBtn.setOnAction(event -> {
            try {
                    SceneManager.showRecentsScene();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        //event handler for "top 100" button
         fireBtn.setOnAction(event -> {
            try {
                    SceneManager.showTrendingScene();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        //event handler for "browse torrents" button
         catBtn.setOnAction(event -> {
            try {
            SceneManager.showCategoriesScene();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        });
        //event handler for "Search Torrents" buttons
        magnBtn.setOnAction(event -> {
            try {
            SceneManager.showItemsScene();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        });
     
        //make the buttons pop when hovering over them
        ScaleTransitionHelper.createScaleTransition(magnBtn, magnLabel);
        ScaleTransitionHelper.createScaleTransition(catBtn, catLabel);
        ScaleTransitionHelper.createScaleTransition(fireBtn, fireLabel);
        ScaleTransitionHelper.createScaleTransition(recBtn, recLabel);

        // Initial state: hide the search button
        h.setVisible(false); 

        //hbox for button portals
        HBox portal = new HBox(magn, cat, rec, fireV);
        portal.setSpacing(50);
        portal.setAlignment(Pos.CENTER);

        VBox searchAndBtns = new VBox(portal, searchStack);
        searchAndBtns.setSpacing(30.0);
        searchAndBtns.setAlignment(Pos.CENTER); 

        return searchAndBtns;
    }
}