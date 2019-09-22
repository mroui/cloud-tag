package Source;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;


public class FadeLoadNextScene {

    private double x;
    private double y;

//====================================================

    protected void makeFadeIn(Pane pane) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(800));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }


    protected void makeFadeOut(Pane pane, LoadSceneInterface loadSceneInterface, String pathFxml) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(800));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((ActionEvent event) -> loadSceneInterface.loadNextScene(pathFxml, pane));
        fadeTransition.play();
    }


    public void loadNextScene(String pathFxml, Pane pane) {
        try {
            Parent newView = (StackPane) FXMLLoader.load(getClass().getResource(pathFxml));
            Scene newScene = new Scene(newView);
            Stage stage = (Stage) pane.getScene().getWindow();
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
