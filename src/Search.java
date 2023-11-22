import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;


public class Search extends Application {
    private Stage primaryStage;

    public Search(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // background image
        Image image2 = new Image(
                new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\shipcool.png"));
        ImageView backImage = new ImageView(image2);
        backImage.setFitWidth(1540);
        backImage.setFitHeight(785);

        VBox searchAndBtns = new SearchBarAndButtonsHelper().createBar();
        // searchAndBtns.setSpacing(5.0);
        searchAndBtns.setAlignment(Pos.CENTER);

        TableView<Data> tableView = new TableView<>();
        tableView.setPrefWidth(0.82 * bounds.getWidth()); // Set the preferred width
        tableView.getStyleClass().add("table-view");
        tableView.setPrefHeight(0.77 * bounds.getHeight());
        // gets rid of the extra space in the table horizontally (extra column)
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //label for filters
        Label filter = new Label("Filter: movies");
        filter.getStyleClass().addAll("filter-label");

        // tableView.setStyle("-fx-background-color: #22bad9; -fx-text-fill: #d6d4d4;");
        // the second value here sets what time the column takes in as input
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
                new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"),
                new Data("song", "Star ", "11/19/20df23", "12 dGb", "21d", "d42", "dJohnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"),
                new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"),
                new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"),
                new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"),
                new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21",  "42", "greg"),
                new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"),
                new Data("song", "Star ", "11/19/20df23", "12 dGb", "21d", "d42", "dJohnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"),
                new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"),
                new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"),
                new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"),
                new Data("Movies", "Star Wars", "11/19/2023", "12 Gb", "21", "42", "Johnny"),
                new Data("Games", "Halo", "11/18/2023", "112 Gb", "21", "42", "greg"));
        tableView.setItems(items);

        //x icon in filter button
        Image image7 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\xIcon.png"));  
        ImageView x = new ImageView(image7);
        x.setFitWidth(15);
        x.setFitHeight(15);
        Button xBtn = new Button();
        xBtn.setGraphic(x);
        xBtn.getStyleClass().addAll("transparent-background");
        xBtn.setPickOnBounds(true);

        filter.setMinWidth(150);

        // StackPane to stack the searchButton on top of the searchBar
        StackPane filterStack = new StackPane(filter, xBtn);
        StackPane.setAlignment(xBtn, Pos.CENTER_RIGHT);
        StackPane.setMargin(xBtn, new Insets(0,0,0,filter.getWidth()));
        filterStack.setMaxWidth(filter.getWidth()); // Set a maximum width

        VBox filterAndTable = new VBox(filterStack, tableView);
        filterAndTable.setSpacing(20);

        filterStack.setVisible(false);

        
        ComboBox<String> audioBox = new ComboBox<>();
        audioBox.setItems(FXCollections.observableArrayList("All Audio", "Music", "Audio Books", "Sound Clips", "Flac", "Other"));
        audioBox.setValue("Audio");  // Optional: Set a default value
        audioBox.setPrefWidth(250);
        audioBox.setPrefHeight(75);
        ComboBox<String> videoBox = new ComboBox<>();
        videoBox.setItems(FXCollections.observableArrayList("All Video", "Movies", "Movies DVDR", "Music Videos", 
        "Movie Clips", "TV Shows", "Handheld", "HD - Movies", "HD - TV Shows", "3D", "CAM/TS", "UHD/4K - Movies", "UHD/4K - TV Shows"));
        videoBox.setValue("Video");  // Optional: Set a default value
        videoBox.setPrefWidth(250);
        videoBox.setPrefHeight(75);
        ComboBox<String> appsBox = new ComboBox<>();
        appsBox.setItems(FXCollections.observableArrayList("All Apps", "Windows", "Mac", "UNIX", "Handheld", "IOS", "Android", "Other OS"));
        appsBox.setValue("Applications");  // Optional: Set a default value
        appsBox.setPrefWidth(250);
        appsBox.setPrefHeight(75);
        ComboBox<String> gamesBox = new ComboBox<>();
        gamesBox.setItems(FXCollections.observableArrayList("All Games", "PC", "Mac", "PSx", "XBOX360", "Wii", "Handheld", "IOS (iPad/iPhone)", "Android", "Other"));
        gamesBox.setValue("Games");  // Optional: Set a default value
        gamesBox.setPrefWidth(250);
        gamesBox.setPrefHeight(75);
        ComboBox<String> otherBox = new ComboBox<>();
        otherBox.setItems(FXCollections.observableArrayList("All Other", "E-Books", "Comics", "Pictures", "Covers", "Physibles", "Other"));
        otherBox.setValue("Others");  // Optional: Set a default value
        otherBox.setPrefWidth(250);
        otherBox.setPrefHeight(75);
        VBox filterBox = new VBox(audioBox, videoBox, appsBox, gamesBox, otherBox);
        filterBox.setSpacing(0);
        filterBox.setPrefWidth(270);

        //listener for combo boxes
        audioBox.setOnAction(event -> {
            String selectedValue = audioBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
        });
        //listener for combo boxes
        videoBox.setOnAction(event -> {
            String selectedValue = videoBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
        });
        //listener for combo boxes
        appsBox.setOnAction(event -> {
            String selectedValue = appsBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
        });
        //listener for combo boxes
        gamesBox.setOnAction(event -> {
            String selectedValue = gamesBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
        });
        //listener for combo boxes
        otherBox.setOnAction(event -> {
            String selectedValue = otherBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
        });
        //xBtn listener so the filter is cleared
        xBtn.setOnAction(event -> {
            filterStack.setVisible(false);
        });     

        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("search-background");
        AnchorPane.setTopAnchor(filterAndTable, 200.0);
        AnchorPane.setLeftAnchor(filterAndTable, 270.0);
        AnchorPane.setTopAnchor(searchAndBtns, 0.025 * bounds.getHeight());
        AnchorPane.setLeftAnchor(searchAndBtns, 0.0);
        AnchorPane.setRightAnchor(searchAndBtns, 0.0);
        AnchorPane.setTopAnchor(filterBox, 200.0);
        // System.out.println(bounds.getHeight());
        // System.out.println(bounds.getWidth());

        // add tableView after
        root.getChildren().addAll(searchAndBtns, filterAndTable, filterBox);
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        System.out.println(getClass().getResource("styles.css").toExternalForm());

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        audioBox.getStyleClass().addAll("combo-box-top");
        otherBox.getStyleClass().addAll( "combo-box-bottom");
        stage.setScene(scene);
        stage.show();
    }

    public void closePrimaryStage() {
        primaryStage.close();
    }
}

