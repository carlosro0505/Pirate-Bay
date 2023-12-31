import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class Search extends Application {
    private Stage primaryStage;
    private String searchString;
    private String filterString;

    public Search(Stage primaryStage, String searchString, String filterString) {
        this.primaryStage = primaryStage;
        this.searchString = searchString;
        this.filterString = filterString;
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        // background image
        Image image2 = new Image("file:res/shipcool.png");
        ImageView backImage = new ImageView(image2);
        backImage.setFitWidth(1540);
        backImage.setFitHeight(785);

        SearchBarAndButtonsHelper sb = new SearchBarAndButtonsHelper();
        VBox searchAndBtns = sb.createBar(searchString, filterString);
        searchAndBtns.getStyleClass().add("search-page-contrast");
        searchAndBtns.setPrefHeight(250);
        searchAndBtns.setAlignment(Pos.CENTER);

        TableView<Data> tableView = new TableView<>();
        tableView.setPrefWidth(1274); // Set the preferred width
        tableView.getStyleClass().add("table-view");
        tableView.setPrefHeight(538.56);
        // gets rid of the extra space in the table horizontally (extra column)
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // label for filters
        Label filter = new Label(filterString);
        filter.getStyleClass().addAll("filter-label");

        // tableView.setStyle("-fx-background-color: #22bad9; -fx-text-fill: #d6d4d4;");
        // the second value here sets what time the column takes in as input
        TableColumn<Data, Hyperlink> catColumn = new TableColumn<>("Category");
        TableColumn<Data, Hyperlink> nameColumn = new TableColumn<>("Name");
        TableColumn<Data, String> dateColumn = new TableColumn<>("Uploaded On");
        TableColumn<Data, String> sizeColumn = new TableColumn<>("Size");
        TableColumn<Data, String> SEColumn = new TableColumn<>("SE");
        TableColumn<Data, String> LEColumn = new TableColumn<>("LE");
        TableColumn<Data, Hyperlink> uplColumn = new TableColumn<>("Uploaded By");

        nameColumn.setCellValueFactory(cellData -> {
            Data data = cellData.getValue();
            Hyperlink link = new Hyperlink(data.getName());
            link.getStyleClass().add("hyperlink-carlos");
            link.setOnAction(event -> {
                try {
                    handleLinkItemClick(data.getID());
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
                    handleLinkUplClick(data.getID());
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            return new javafx.beans.property.ReadOnlyObjectWrapper<>(link);
        });
        catColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
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
        // if search is not empty
        if (!searchString.equals("")) {
            // first check if a person clicked a username
            // don't forget to make function get rid of filter
            if (searchString.contains("user: ")) {
                ObservableList<Data> items = FXCollections.observableArrayList(
                        SearchEngine.browseByUser(FileParser.parseData(), searchString.replace("user: ", "")));
                tableView.setItems(items);
            }
            // if there are not filters; if there is a string and no filter;
            // (filter.getText().equals(""))
            else {
                ObservableList<Data> items = FXCollections
                        .observableArrayList(SearchEngine.search(FileParser.parseData(), searchString));
                tableView.setItems(items);
            }

        }
        // else: searchString is empty
        else {
            // no searchString but filter is *recent, e.g., user clicked recents
            if (filterString.equals("    Recents")) {
                ObservableList<Data> items = FXCollections
                        .observableArrayList(SearchEngine.recent(FileParser.parseData()));
                tableView.setItems(items);
            }
            // no searchString but filter is *trending, e.g., user clicked Trending
            else if (filterString.equals("    Trending")) {
                ObservableList<Data> items = FXCollections
                        .observableArrayList(SearchEngine.trending(FileParser.parseData()));
                tableView.setItems(items);
            }
            // if it has a filter and no searchString
            else {
                ObservableList<Data> items = FXCollections.observableArrayList(
                        SearchEngine.browseCategoriesFilter(FileParser.parseData(), filter.getText()));
                tableView.setItems(items);
            }
        }
        tableView.setPadding(new Insets(10, 10, 0, 25)); // Set padding for the tableview

        // Set row factory to handle mouse events
        tableView.setRowFactory(tv -> {
            TableRow<Data> row = new TableRow<>();

            row.setOnMouseEntered(event -> {
                if (!row.isEmpty()) {
                    // row.setStyle("-fx-background-color: yellow;");

                }
            });

            row.setOnMouseExited(event -> {
                if (!row.isEmpty()) {
                    row.setStyle("");
                }
            });

            return row;
        });

        // x icon in filter tags
        Image image7 = new Image("file:res/xIcon.png");
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
        StackPane.setMargin(xBtn, new Insets(0, 0, 0, filter.getWidth()));
        filterStack.setMaxWidth(filter.getWidth()); // Set a maximum width
        if (filterString.equals(""))
            filterStack.setVisible(false);

        VBox filterAndTable = new VBox(filterStack, tableView);
        filterAndTable.getStyleClass().addAll("vbox-table");
        filterAndTable.setSpacing(2);

        ComboBox<String> audioBox = new ComboBox<>();
        audioBox.setItems(FXCollections.observableArrayList("Music", "Audio Books", "Sound Clips", "Flac", "Other"));
        audioBox.setValue("Audio"); // Optional: Set a default value
        audioBox.setPrefWidth(250);
        audioBox.setPrefHeight(75);
        ComboBox<String> videoBox = new ComboBox<>();
        videoBox.setItems(FXCollections.observableArrayList("Movies", "Movies DVDR", "Music Videos",
                "Movie Clips", "TV Shows", "Handheld", "HD - Movies", "HD - TV Shows", "3D", "CAM/TS",
                "UHD/4K - Movies", "UHD/4K - TV Shows"));
        videoBox.setValue("Video"); // sets a default value
        videoBox.setPrefWidth(250);
        videoBox.setPrefHeight(75);
        ComboBox<String> appsBox = new ComboBox<>();
        appsBox.setItems(
                FXCollections.observableArrayList("Windows", "Mac", "UNIX", "Handheld", "IOS", "Android", "Other OS"));
        appsBox.setValue("Applications"); // sets a default value
        appsBox.setPrefWidth(250);
        appsBox.setPrefHeight(75);
        ComboBox<String> gamesBox = new ComboBox<>();
        gamesBox.setItems(FXCollections.observableArrayList("PC", "Mac", "PSx", "XBOX360", "Wii", "Handheld",
                "IOS (iPad/iPhone)", "Android", "Other"));
        gamesBox.setValue("Games"); // sets a default value
        gamesBox.setPrefWidth(250);
        gamesBox.setPrefHeight(75);
        ComboBox<String> otherBox = new ComboBox<>();
        otherBox.setItems(
                FXCollections.observableArrayList("E-Books", "Comics", "Pictures", "Covers", "Physibles", "Other"));
        otherBox.setValue("Others"); // sets a default value
        otherBox.setPrefWidth(250);
        otherBox.setPrefHeight(75);
        VBox filterBox = new VBox(audioBox, videoBox, appsBox, gamesBox, otherBox);
        filterBox.setSpacing(0);
        filterBox.setPrefWidth(270);
        filterBox.setPrefHeight(1000);
        filterBox.setPadding(new Insets(0, 10, 0, 10)); // sets a default value
        filterBox.getStyleClass().add("search-page-contrast");

        // listener for combo boxes
        audioBox.setOnAction(event -> {
            String selectedValue = audioBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
            filterString = selectedValue;
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(
                        SearchEngine.classFilter(FileParser.parseData(), searchString, selectedValue));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // listener for combo boxes
        videoBox.setOnAction(event -> {
            String selectedValue = videoBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
            filterString = selectedValue;
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(
                        SearchEngine.classFilter(FileParser.parseData(), searchString, selectedValue));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // listener for combo boxes
        appsBox.setOnAction(event -> {
            String selectedValue = appsBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
            filterString = selectedValue;
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(
                        SearchEngine.classFilter(FileParser.parseData(), searchString, selectedValue));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // listener for combo boxes
        gamesBox.setOnAction(event -> {
            String selectedValue = gamesBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
            filterString = selectedValue;
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(
                        SearchEngine.classFilter(FileParser.parseData(), searchString, selectedValue));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // listener for combo boxes
        otherBox.setOnAction(event -> {
            String selectedValue = otherBox.getValue();
            filter.setText(selectedValue);
            filterStack.setVisible(true);
            filterString = selectedValue;
            sb.setFilterString(filterString);
            ObservableList<Data> items;
            try {
                items = FXCollections.observableArrayList(
                        SearchEngine.classFilter(FileParser.parseData(), searchString, selectedValue));
                tableView.setItems(items);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // xBtn listener so the filter is cleared
        xBtn.setOnAction(event -> {
            filterStack.setVisible(false);
            filterString = "";
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
        AnchorPane.setTopAnchor(searchAndBtns, 0.0);
        AnchorPane.setLeftAnchor(searchAndBtns, 0.0);
        AnchorPane.setRightAnchor(searchAndBtns, 0.0);
        AnchorPane.setTopAnchor(filterBox, 250.0);

        // add tableView after
        root.getChildren().addAll(searchAndBtns, filterAndTable, filterBox);
        Scene scene = new Scene(root, 1540, 785);
        System.out.println(getClass().getResource("styles.css").toExternalForm());

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        audioBox.getStyleClass().addAll("combo-box-top");
        otherBox.getStyleClass().addAll("combo-box-bottom");
        stage.setScene(scene);
        stage.show();
    }

    public void closePrimaryStage() {
        primaryStage.close();
    }

    private void handleLinkItemClick(int clickedID) throws FileNotFoundException {
        List<Data> dataList = FileParser.parseData();

        for (Data data : dataList) {
            if (data.getID() == clickedID) {
                // Found the associated ID, do something with the data
                SceneManager.showItemsScene(data);
                break; // Exit the loop since we found the data
            }
        }
    }

    // if user clicks the username
    private void handleLinkUplClick(int clickedID) throws FileNotFoundException {
        List<Data> dataList = FileParser.parseData();

        for (Data data : dataList) {
            if (data.getID() == clickedID) {
                // Found the associated ID, do something with the data
                SceneManager.showSearchScene("user: " + data.getUploadBy(), "");
                break; // Exit the loop since we found the data
            }
        }
    }

}
