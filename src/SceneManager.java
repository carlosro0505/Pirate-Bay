import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void showCategoriesScene() {
        Categories categories = new Categories(primaryStage);
        categories.start(new Stage());
    }

    public static void showItemsScene() {
        Item item = new Item(primaryStage);
        item.start(new Stage());
    }

    public static void showMainScene() {
        App app = new App();
        try {
            app.start(primaryStage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
