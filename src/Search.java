import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Search extends Application {
    private Stage primaryStage;
    private String searchString;
    private String filterString;


    public Search(Stage primaryStage, String searchString, String filterString) {
        this.primaryStage = primaryStage;
        this.searchString = searchString;
        this.filterString=filterString;        
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        //background image
        Image image2 = new Image("file:res/shipcool.png");
        //Image image2 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\shipcool.png"));  
        ImageView backImage = new ImageView(image2);
        backImage.setFitWidth(1540);
        backImage.setFitHeight(785); 

        SearchBarAndButtonsHelper sb = new SearchBarAndButtonsHelper();
        VBox searchAndBtns = sb.createBar(searchString, filterString);
        searchAndBtns.getStyleClass().add("search-page-contrast");
        searchAndBtns.setPrefHeight(250);
        // searchAndBtns.setSpacing(5.0);
        searchAndBtns.setAlignment(Pos.CENTER);

        TableView<Data> tableView = new TableView<>();
        tableView.setPrefWidth(0.83 * bounds.getWidth()); // Set the preferred width
        tableView.getStyleClass().add("table-view");
        tableView.setPrefHeight(0.66 * bounds.getHeight());
        // gets rid of the extra space in the table horizontally (extra column)
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //label for filters
        Label filter = new Label(filterString);
        filter.getStyleClass().addAll("filter-label");

        // tableView.setStyle("-fx-background-color: #22bad9; -fx-text-fill: #d6d4d4;");
        // the second value here sets what time the column takes in as input
        TableColumn<Data, Hyperlink> catColumn = new TableColumn<>("Categories");
        TableColumn<Data, Hyperlink> nameColumn = new TableColumn<>("Name");
        TableColumn<Data, String> dateColumn = new TableColumn<>("Uploaded On");
        TableColumn<Data, String> sizeColumn = new TableColumn<>("Size");
        TableColumn<Data, String> SEColumn = new TableColumn<>("SE");
        TableColumn<Data, String> LEColumn = new TableColumn<>("LE");
        TableColumn<Data, Hyperlink> uplColumn = new TableColumn<>("Uploaded By");

        catColumn.setCellValueFactory(cellData -> {
        Data data = cellData.getValue();
        Hyperlink link = new Hyperlink(data.getCategory());
        link.getStyleClass().add("hyperlink-carlos");
        link.setOnAction(event -> {
            try {
                handleLinkClick(data.getID());
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        return new javafx.beans.property.ReadOnlyObjectWrapper<>(link);
    });

    nameColumn.setCellValueFactory(cellData -> {
        Data data = cellData.getValue();
        Hyperlink link = new Hyperlink(data.getName());
        link.getStyleClass().add("hyperlink-carlos");
        link.setOnAction(event -> {
            try {
                handleLinkClick(data.getID());
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        return new javafx.beans.property.ReadOnlyObjectWrapper<>(link);
    });
     uplColumn.setCellValueFactory(cellData -> {
        Data data = cellData.getValue();
        Hyperlink link = new Hyperlink(data.getUploadBy());
        link.getStyleClass().add("hyperlink-carlos");
        link.setOnAction(event -> {
            try {
                handleLinkClick(data.getID());
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        return new javafx.beans.property.ReadOnlyObjectWrapper<>(link);
    });
       
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        SEColumn.setCellValueFactory(new PropertyValueFactory<>("SE"));
        LEColumn.setCellValueFactory(new PropertyValueFactory<>("LE"));

        catColumn.getStyleClass().add("table-view");
        nameColumn.getStyleClass().add("table-view");
        dateColumn.getStyleClass().add("table-view");
        sizeColumn.getStyleClass().add("table-view");
        SEColumn.getStyleClass().add("table-view");
        LEColumn.getStyleClass().add("table-view");
        uplColumn.getStyleClass().add("table-view");

        tableView.getColumns().addAll(catColumn, nameColumn, dateColumn, sizeColumn, SEColumn, LEColumn, uplColumn);
        //if search is not empty
        if(!searchString.equals("")){
            //if there are not filters
            if(filter.getText().equals("")){
                ObservableList<Data> items = FXCollections.observableArrayList(SearchEngine.search(FileParser.parseData(), searchString));
                tableView.setItems(items);
            }
            //if there are filters
            else{
                ObservableList<Data> items = FXCollections.observableArrayList(SearchEngine.classFilter(FileParser.parseData(), searchString, filter.getText()));
                tableView.setItems(items);
            }
        }
        //if it's empty just display all files I guess
        else{
        ObservableList<Data> items = FXCollections.observableArrayList(FileParser.parseData());
        tableView.setItems(items);
        }
        tableView.setPadding(new Insets(10, 10, 0, 10)); // Set padding for the VBox


        //x icon in filter tags
        Image image7 = new Image("file:res/xIcon.png");
        //Image image7 = new Image(new FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate Bay\\res\\xIcon.png"));  
        ImageView x = new ImageView(image7);
        x.setFitWidth(20);
        x.setFitHeight(20);
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
        if(filterString.equals(""))
        filterStack.setVisible(false);
        //filterStack.setPadding(new Insets(2, 0, 0, 0)); // Set padding for the VBox

        VBox filterAndTable = new VBox(filterStack, tableView);
        filterAndTable.setSpacing(2);
        
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
        filterBox.setPrefHeight(1000);
        filterBox.setPadding(new Insets(0, 10, 0, 10)); // Set padding for the VBox
        filterBox.getStyleClass().add("search-page-contrast");


        //listener for combo boxes
        audioBox.setOnAction(event -> {
            String selectedValue = audioBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
            filterString=selectedValue;
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(SearchEngine.classFilter(FileParser.parseData(), searchString, selectedValue));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        //listener for combo boxes
        videoBox.setOnAction(event -> {
            String selectedValue = videoBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
            filterString=selectedValue;
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(SearchEngine.classFilter(FileParser.parseData(), searchString, selectedValue));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        //listener for combo boxes
        appsBox.setOnAction(event -> {
            String selectedValue = appsBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
            filterString=selectedValue;
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(SearchEngine.classFilter(FileParser.parseData(), searchString, selectedValue));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        //listener for combo boxes
        gamesBox.setOnAction(event -> {
            String selectedValue = gamesBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
            filterString=selectedValue;
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(SearchEngine.classFilter(FileParser.parseData(), searchString, selectedValue));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        //listener for combo boxes
        otherBox.setOnAction(event -> {
            String selectedValue = otherBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
            filterString=selectedValue;
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(SearchEngine.classFilter(FileParser.parseData(), searchString, selectedValue));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        //xBtn listener so the filter is cleared
        xBtn.setOnAction(event -> {
            filterStack.setVisible(false);
            filterString="";
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(SearchEngine.search(FileParser.parseData(), searchString));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });     

        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("search-background");
        AnchorPane.setTopAnchor(filterAndTable, 225.0);
        AnchorPane.setLeftAnchor(filterAndTable, 270.0);
        //        AnchorPane.setTopAnchor(searchAndBtns, 0.025 * bounds.getHeight());
        AnchorPane.setTopAnchor(searchAndBtns, 0.0);
        AnchorPane.setLeftAnchor(searchAndBtns, 0.0);
        AnchorPane.setRightAnchor(searchAndBtns, 0.0);
        AnchorPane.setTopAnchor(filterBox, 250.0);
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

    private void handleLinkClick(int clickedID) throws FileNotFoundException {
        List<Data> dataList = FileParser.parseData();
    
        for (Data data : dataList) {
            if (data.getID() == clickedID) {
                // Found the associated ID, do something with the data
                SceneManager.showItemsScene(data);
                break;  // Exit the loop since we found the data
            }
        }
    }

}

