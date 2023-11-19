import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.scene.control.Label;

//class that helps create scale transitions for the buttons when hovering over them
public class ScaleTransitionHelper {

    public static ScaleTransition createScaleTransition(Button button, Label label) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);

        // Reverse ScaleTransition
        ScaleTransition reverseTransition = new ScaleTransition(Duration.millis(200), button);
        reverseTransition.setFromX(1.2);
        reverseTransition.setFromY(1.2);
        reverseTransition.setToX(1.0);
        reverseTransition.setToY(1.0);

        // Set up the event handlers
        button.setOnMouseEntered(event -> {
            label.getStyleClass().addAll("btn-pop-color");
            scaleTransition.playFromStart();
        });

        button.setOnMouseExited(event -> {
            // Manually reverse the animation on mouse exit
            label.getStyleClass().setAll("portals");
            reverseTransition.playFromStart();
        });

        return scaleTransition;
    }
}
