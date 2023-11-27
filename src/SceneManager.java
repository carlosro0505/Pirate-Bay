import java.io.FileNotFoundException;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void showCategoriesScene() throws FileNotFoundException {
        Categories categories = new Categories(primaryStage);
        categories.start(primaryStage);
    }

    public static void showItemsScene(Data data) throws FileNotFoundException {
        Item item = new Item(primaryStage, data);
        item.start(primaryStage);
    }

    public static void showSearchScene(String searchString, String classString) throws FileNotFoundException {
        Search search = new Search(primaryStage, searchString, classString);
        search.start(primaryStage);
    }

    public static void showTrendingScene() throws FileNotFoundException {
        Trending trending = new Trending(primaryStage);
        trending.start(primaryStage);
    }

    public static void showMainScene() throws FileNotFoundException {
        App app = new App();
        try {
            app.start(primaryStage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
