import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class Search extends Application {
    private Stage primaryStage;

    public Search(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        //background image
        Image image2 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\shipcool.png"));  
        ImageView backImage = new ImageView(image2);
        backImage.setFitWidth(1540);
        backImage.setFitHeight(785); 

        VBox searchAndBtns = new SearchBarAndButtonsHelper().createBar();
      //  searchAndBtns.setSpacing(5.0);
        searchAndBtns.setAlignment(Pos.CENTER);                   

        TableView<Data> tableView = new TableView<>();
        tableView.setPrefWidth(0.82 * bounds.getWidth());  // Set the preferred width
        tableView.getStyleClass().add("table-view");
        tableView.setPrefHeight(0.77 * bounds.getHeight());
        //gets rid of the extra space in the column
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //tableView.setStyle("-fx-background-color: #22bad9; -fx-text-fill: #d6d4d4;");
        //the second value here sets what time the column takes in as input
        TableColumn<Data, String> catColumn = new TableColumn<>("Categories");
        TableColumn<Data, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Data, String> dateColumn = new TableColumn<>("Uploaded On");
        TableColumn<Data, String> sizeColumn = new TableColumn<>("Size");
        TableColumn<Data, String> SEColumn = new TableColumn<>("SE");
        TableColumn<Data, String> LEColumn = new TableColumn<>("LE");
        TableColumn<Data, String> uplColumn = new TableColumn<>("Uploaded By");
        
        catColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        SEColumn.setCellValueFactory(new PropertyValueFactory<>("SE"));
        LEColumn.setCellValueFactory(new PropertyValueFactory<>("LE"));
        uplColumn.setCellValueFactory(new PropertyValueFactory<>("uploadBy"));

        catColumn.getStyleClass().add("table-view");
        nameColumn.getStyleClass().add("table-view");
        dateColumn.getStyleClass().add("table-view");
        sizeColumn.getStyleClass().add("table-view");
        SEColumn.getStyleClass().add("table-view");
        LEColumn.getStyleClass().add("table-view");
        uplColumn.getStyleClass().add("table-view");

        tableView.getColumns().addAll(catColumn, nameColumn, dateColumn, sizeColumn, SEColumn, LEColumn, uplColumn);
        ObservableList<Data> items = FXCollections.observableArrayList(
            new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("song", "Star ", "11/19/20df23", "12 dGb", "21d", "d42", "dJohnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("song", "Star ", "11/19/20df23", "12 dGb", "21d", "d42", "dJohnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ),
            new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny" ), 
            new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg" ));
        tableView.setItems(items);
   
        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("search-background");
        AnchorPane.setTopAnchor(tableView, 175.0);
        AnchorPane.setLeftAnchor(tableView, 270.0);
        AnchorPane.setTopAnchor(searchAndBtns, 0.025 * bounds.getHeight());
        AnchorPane.setLeftAnchor(searchAndBtns, 0.0);
        AnchorPane.setRightAnchor(searchAndBtns, 0.0);
       // System.out.println(bounds.getHeight());
       // System.out.println(bounds.getWidth());

        //add tableView after
        root.getChildren().addAll(searchAndBtns, tableView);
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        System.out.println(getClass().getResource("styles.css").toExternalForm());

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void closePrimaryStage() {
        primaryStage.close();
    }
}


