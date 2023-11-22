import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Categories extends Application{
    private Stage primaryStage;

    public Categories(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException{
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        VBox searchAndBtns = new SearchBarAndButtonsHelper().createBar();
        // searchAndBtns.setSpacing(5.0);
        searchAndBtns.setAlignment(Pos.CENTER);

        AnchorPane.setTopAnchor(searchAndBtns, 0.025 * bounds.getHeight());
        AnchorPane.setLeftAnchor(searchAndBtns, 0.0);
        AnchorPane.setRightAnchor(searchAndBtns, 0.0);

        AnchorPane root = new AnchorPane(searchAndBtns);
        root.getStyleClass().add("search-background");
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void closePrimaryStage() {
        primaryStage.close();
    }
}
