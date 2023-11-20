import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Search extends Application {
    private Stage primaryStage;

    public Search(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();


        TableView<Data> tableView = new TableView<>();
        //the second value here sets what time the column takes in as input
        TableColumn<Data, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Data, Integer> ageColumn = new TableColumn<>("Age");
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        tableView.getColumns().addAll(nameColumn, ageColumn);
        ObservableList<Data> items = FXCollections.observableArrayList(
            new Data("John", "John", "John", "John", "John", "John", "John" ));
        tableView.setItems(items);

        
        AnchorPane root = new AnchorPane(tableView);
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public void closePrimaryStage() {
        primaryStage.close();
    }
    
}


