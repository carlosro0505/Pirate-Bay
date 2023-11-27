import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.print.DocFlavor.URL;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Categories extends Application{
    private Stage primaryStage;

    public Categories(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        //background image
        Image image2 = new Image("file:res/pbay.png");
        //Image image2 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\shipcool.png"));  
        ImageView backImage = new ImageView(image2);
        backImage.setFitWidth(1540);
        backImage.setFitHeight(785); 

        SearchBarAndButtonsHelper sb = new SearchBarAndButtonsHelper();
        VBox searchAndBtns = sb.createBar("", "");
        searchAndBtns.getStyleClass().add("search-page-contrast");
        searchAndBtns.setPrefHeight(250);
        // searchAndBtns.setSpacing(5.0);
        searchAndBtns.setAlignment(Pos.CENTER);
         
        // Create a horizontal line (Separator)
        Separator separator = new Separator();
        separator.setPrefWidth(2600); // Set the preferred width of the line
        separator.getStyleClass().add("separator");
        // Horizontal lane
        HBox horizontalLane = new HBox(separator);
        horizontalLane.getStyleClass().add("horizontal-lane");
        horizontalLane.setMinHeight(12); // Set your preferred height
        // Adjusted position for the table view
        AnchorPane.setTopAnchor(horizontalLane, 245.0);
       
        Image image6 = new Image("file:res/fire.png");  
        ImageView fire = new ImageView(image6);
        fire.setFitWidth(30);
        fire.setFitHeight(37);
        Button fireBtn = new Button();
        fireBtn.setGraphic(fire);
        fireBtn.getStyleClass().addAll("portals", "fireBtn");

        VBox vbox1 = createCategoryBox("res/audioo.gif", "Audio", "Music", "Audio books", "Sound clips", "Podcast", "Other");
        VBox vbox2 = createCategoryBox("res/games.png", "Games", "PC", "Mac", "PlayStation, Xbox, Nintendo", "IOS, Android", "Other");
        VBox vbox3 = createCategoryBox("res/videos.gif", "Videos", "Movies", "Music Videos", "Movie Clips", "TV Shows", "Other");
        VBox vbox4 = createCategoryBox("res/appbox.gif", "Applications", "Windows", "Mac", "UNIX", "IOS, Android", "Other");
        VBox vbox5 = createCategoryBox("res/books.gif", "Books", "Comics", "Fiction", "Fantasy", "Biography", "Other");
        VBox vbox6 = createCategoryBox("res/otherb.gif", "Other", "Radio", "SkillShare", "Udemy", "Pearson", "Other");

   // Create HBoxes for the first three categories and the last three categories
   HBox hboxTop = new HBox(vbox1, vbox2, vbox3);
   HBox hboxBottom = new HBox(vbox4, vbox5, vbox6);
   hboxTop.setAlignment(Pos.CENTER);
   hboxBottom.setAlignment(Pos.CENTER);
   hboxTop.setSpacing(20);
   hboxBottom.setSpacing(20);

   // Create a parent VBox and add HBoxes to it
   VBox vBoxesContainer = new VBox(horizontalLane, hboxTop, hboxBottom);
   vBoxesContainer.setAlignment(Pos.CENTER);
   vBoxesContainer.setSpacing(20);

   // VBox for center content
   VBox centerContent = new VBox(vBoxesContainer);
   centerContent.setAlignment(Pos.CENTER);
      
      
   // Create a ScrollPane and add the content to it
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(backImage); // Assuming backImage is the root content
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

       // AnchorPane for the entire content
       AnchorPane root = new AnchorPane(scrollPane, centerContent, searchAndBtns, horizontalLane);
       root.getStyleClass().add("main-pane");

       // Set layout constraints for centerContent
        AnchorPane.setTopAnchor(centerContent, 250.0); // Adjust the top anchor based on your needs
        AnchorPane.setLeftAnchor(centerContent, 0.0);
        AnchorPane.setRightAnchor(centerContent, 0.0);

       AnchorPane.setTopAnchor(searchAndBtns, 0.0);
       AnchorPane.setLeftAnchor(searchAndBtns, 0.0);
       AnchorPane.setRightAnchor(searchAndBtns, 0.0);

       Scene scene = new Scene(root, 1540, 785);
       scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
       primaryStage.setScene(scene);
       primaryStage.setTitle("DIMEEEEELOOOOOOOOOOOOOO");
       primaryStage.show();
    }

    private VBox createCategoryBox(String iconPath, String title, String... options) {
        // Load the icon
        File file = new File(iconPath);
        try {
            String imageUrl = file.toURI().toURL().toString();
            Image image = new Image(imageUrl);
            ImageView iconView = new ImageView(image);
            iconView.setFitWidth(30);
            iconView.setFitHeight(30);
    
            // Create title label
            Label titleLabel = new Label(title);
            titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #a4a4ff; -fx-font-weight: bold;");
    
            // Create VBox for options
            VBox optionsVBox = new VBox();
            Hyperlink[] optionLinks = new Hyperlink[options.length];
            for (int i = 0; i < options.length; i++) {
                optionLinks[i] = new Hyperlink(options[i]);
                final int index = i; // Needed for lambda expression
                optionLinks[i].setOnAction(event -> handleLinkClick(options[index]));
                optionsVBox.getChildren().add(optionLinks[i]);
            }
    
            // VBox for the entire category box
            VBox categoryBox = new VBox(new HBox(iconView, titleLabel), optionsVBox);
            categoryBox.setAlignment(Pos.CENTER);
            categoryBox.setSpacing(5);
            categoryBox.setPadding(new Insets(10));
    
            return categoryBox;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new VBox(); // Return an empty VBox or handle appropriately
        }
    }
    
    private void handleLinkClick(String category) {
        // Handle the link click event (e.g., open a new scene or perform some action)
        System.out.println("Link clicked: " + category);
    }

    public void closePrimaryStage() {
        primaryStage.close();
    }
}
