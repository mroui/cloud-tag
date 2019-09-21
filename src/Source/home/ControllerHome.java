package Source.home;


import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerHome implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Pane homePane;

    private double x, y;

//====================================================

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image xImage;
        try {
            xImage = new Image(new FileInputStream("src/Assets/Images/x.png"));
            this.exitButton.setGraphic(new ImageView(xImage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.homePane.setOpacity(0);
        makeFadeIn();
    }


    @FXML
    private void ExitActionButton(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    
    @FXML
    private void startActionButton() {
        makeFadeOut();
    }


    private void makeFadeIn() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(800));
        fadeTransition.setNode(this.homePane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }


    private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(800));
        fadeTransition.setNode(this.homePane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((ActionEvent event) -> loadNextScene());
        fadeTransition.play();
    }


    private void loadNextScene() {
        Parent newView;
        try {
            newView = (StackPane) FXMLLoader.load(getClass().getResource("../websiteTags/websiteTags.fxml"));
            Scene newScene = new Scene(newView);
            Stage stage = (Stage) this.homePane.getScene().getWindow();
            newView.setOnMousePressed(event -> {
                this.x = event.getSceneX();
                this.y = event.getSceneY();
            });
            newView.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - this.x);
                stage.setY(event.getScreenY() - this.y);
            });
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
