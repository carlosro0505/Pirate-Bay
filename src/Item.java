import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class Item extends Application {
    private Stage primaryStage;

    public Item(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        Label label = new Label("Carlos Rodriguez");
        AnchorPane root = new AnchorPane(label);
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public void closePrimaryStage() {
        primaryStage.close();
    }
}
