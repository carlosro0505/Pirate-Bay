import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Categories extends Application {
    @Override
    public void start(Stage primaryStage) {
        final double SCENE_WIDTH = 1555;
        final double SCENE_HEIGHT = 780;

        Label label = new Label("Carlos Rodriguez");
        label.setLayoutX(238);
        label.setLayoutY(200);

        Pane pane = new Pane(label);
        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
