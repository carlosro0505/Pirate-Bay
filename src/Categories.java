import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Categories extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Carlos Rodriguez");
        label.setLayoutX(238);
        label.setLayoutY(200);

        Pane pane = new Pane(label);
        Scene scene = new Scene(pane, 1540, 785);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
