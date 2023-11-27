import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item extends Application {
    private Stage primaryStage;
    private Scene itemScene;
    private Data data;

    public Item(Stage primaryStage, Data data) {
        this.primaryStage = primaryStage;
        this.data = data;
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        VBox searchAndBtns = new SearchBarAndButtonsHelper().createBar("");
        // searchAndBtns.setSpacing(5.0);
        searchAndBtns.setAlignment(Pos.CENTER);
        //putting each field of data object into a ready to display string
        String fileCat = "File Type: "+data.getCategory()+"\n\n";
        String fileSize = "File Size: "+data.getSize()+"\n\n";
        String seeders = "Seeders: "+data.getSE()+"\n\n";
        String leechers = "Leechers: "+data.getLE()+"\n\n";
        String uploader = "Uploaded By: "+data.getUploadBy()+"\n\n";
        String finalDetails = fileCat+fileSize+seeders+leechers+uploader;

        String description = data.getDescription();

        //Labels serving as headers
        Label title = new Label(data.getName());
        title.setStyle("-fx-font: 24 verdana; -fx-text-fill: #a4a4ff; -fx-font-weight: bold");
        Label fileDesc = new Label("   File Description");
        fileDesc.setStyle("-fx-font: 16 verdana; -fx-text-fill: #a4a4ff; -fx-font-weight: bold");
        Label fileDet = new Label("   Torrent Details");
        fileDet.setStyle("-fx-font: 16 verdana; -fx-text-fill: #a4a4ff; -fx-font-weight: bold");
        Label downDesc = new Label("   Download Options");
        downDesc.setStyle("-fx-font: 16 verdana; -fx-text-fill: #a4a4ff; -fx-font-weight: bold");

        //rectangle containing the uppermost header
        Rectangle titleRect = new Rectangle(1540.0, 0.05*785);
        titleRect.setFill(Color.web("#181c24"));
        //stackpane putting header text on rectangle;
        StackPane titleFrame = new StackPane();
        titleFrame.getChildren().addAll(titleRect,title);
        StackPane.setAlignment(title,Pos.CENTER);

        //rectangle containing the header for file details
        Rectangle infoRect = new Rectangle(0.3*1540.0, 0.05*785);
        infoRect.setFill(Color.web("#181c24"));
        //TextArea containing file details
        TextArea details = new TextArea(finalDetails);
        details.setMinHeight(0.45*785);
        details.setMaxWidth(0.3*1540.0);
        details.setStyle("-fx-control-inner-background: #2d3545; -fx-font: 16 verdana; -fx-text-fill: #c7c7ff; -fx-text-box-border: transparent; -fx-focus-color: transparent;");
        details.setEditable(false);
        //details.setAlignment(Pos.TOP_LEFT);
        //stackpane putting header text on rectangle
        StackPane infoBar = new StackPane();
        infoBar.getChildren().addAll(infoRect,fileDet);
        StackPane.setAlignment(fileDet, Pos.CENTER);
        //vbox combining both objects
        VBox infoFrame = new VBox(0);
        infoFrame.getChildren().addAll(infoBar, details);

        //rectangle containing the header for file description
        Rectangle textRect = new Rectangle(0.3*1540.0, 0.05*785);
        textRect.setFill(Color.web("#181c24"));
        //TextArea containing the file description
        TextArea descript = new TextArea(description);
        descript.setMaxHeight(0.2*785);
        descript.setMaxWidth(0.3*1540.0);
        descript.setStyle("-fx-control-inner-background: #2d3545; -fx-font: 14 verdana; -fx-text-fill: #c7c7ff; -fx-text-box-border: transparent; -fx-focus-color: transparent;");
        descript.setEditable(false);
        //descript.setAlignment(Pos.TOP_LEFT);
        //stackpane putting header text on the rectangle
        StackPane textBar = new StackPane();
        textBar.getChildren().addAll(textRect,fileDesc);
        StackPane.setAlignment(fileDesc, Pos.CENTER);
        //vbox combining header stackpane and TextArea
        VBox textFrame = new VBox(0);
        textFrame.getChildren().addAll(textBar, descript);

        Rectangle buttonRect1 = new Rectangle(0.3*1540.0,0.05*785);
        StackPane buttonRect1Pane = new StackPane();
        buttonRect1Pane.getChildren().addAll(buttonRect1, downDesc);
        StackPane.setAlignment(downDesc, Pos.CENTER);

        Rectangle buttonRect2 = new Rectangle(0.3*1540.0, 0.15*785);
        buttonRect1.setFill(Color.web("#181c24"));
        buttonRect2.setFill(Color.web("#2d3545"));
        buttonRect2.setArcWidth(5);
        buttonRect2.setArcHeight(5);


        Image m = new Image(new FileInputStream(new File("res/magnet.png").getAbsolutePath()));
        ImageView magnetView = new ImageView(m);
        magnetView.setFitWidth(50);
        magnetView.setFitHeight(50);
        Button magnet = new Button();
        magnet.setGraphic(magnetView);
        Label magnetLabel = new Label(" Magnet Download"); 
        magnetLabel.setStyle("-fx-font: 14 verdana; -fx-text-fill: #c7c7ff");
        VBox magnetBox = new VBox(10);
        magnetBox.getChildren().addAll(magnet,magnetLabel);
        
        Image d = new Image(new FileInputStream(new File("res/download.png").getAbsolutePath()));
        ImageView downloadView = new ImageView(d);
        downloadView.setFitWidth(50);
        downloadView.setFitHeight(50);
        Button download = new Button();
        download.setGraphic(downloadView);
        Label downloadLabel = new Label(" Torrent Download"); 
        downloadLabel.setStyle("-fx-font: 14 verdana; -fx-text-fill: #c7c7ff");
        VBox downloadBox = new VBox(10);
        downloadBox.getChildren().addAll(download,downloadLabel);
        
        ScaleTransitionHelper.createScaleTransition(download, downloadLabel);
        ScaleTransitionHelper.createScaleTransition(magnet, magnetLabel);
        
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(magnetBox,downloadBox);
        magnetBox.setAlignment(Pos.CENTER);
        downloadBox.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);

        StackPane buttonPane = new StackPane();
        buttonPane.getChildren().addAll(buttonRect2,buttons);

        VBox buttonFrame = new VBox(0);
        buttonFrame.getChildren().addAll(buttonRect1Pane,buttonPane);


        AnchorPane.setTopAnchor(searchAndBtns, 0.025 * 785);
        AnchorPane.setLeftAnchor(searchAndBtns, 0.0);
        AnchorPane.setRightAnchor(searchAndBtns, 0.0);

        AnchorPane.setTopAnchor(titleFrame, 0.3*785);

        AnchorPane.setTopAnchor(infoFrame, 0.4*785);
        AnchorPane.setLeftAnchor(infoFrame, 0.15*1540.0);

        AnchorPane.setTopAnchor(textFrame, 0.4*785);
        AnchorPane.setLeftAnchor(textFrame, 0.5*1540.0);

        AnchorPane.setTopAnchor(buttonFrame, 0.7*785);
        AnchorPane.setLeftAnchor(buttonFrame,0.5*1540.0);

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(searchAndBtns, titleFrame,infoFrame, textFrame, buttonFrame);
        root.getStyleClass().add("search-page-contrast");
        Scene scene = new Scene(root, 1540.0, 785);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void closePrimaryStage() {
        primaryStage.close();
    }
}
