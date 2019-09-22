package Source.Pages.finishCloud;


import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Source.Others.Cloud;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerFinishCloud implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Pane rootPane;
    @FXML
    private ImageView imageView;
    @FXML
    private ProgressIndicator progressIndicator;

    private double x, y;
    private Cloud cloud;

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
        this.rootPane.setOpacity(0);
        makeFadeIn();
    }


    public ControllerFinishCloud() {
        this.cloud = new Cloud();
    }


    public void set(Cloud cloud) {
        this.cloud = cloud;
    }


    @FXML
    private void ExitActionButton(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private void RepeatActionButton() {
        makeFadeOut();
    }


    private void createCloud() {
        Thread creating = new Thread(() -> {
            progressIndicator.setVisible(true);
            this.rootPane.setDisable(true);

            this.cloud.create();

            File file = new File("src/Output/" + this.cloud.getImageName());
            Image image = new Image(file.toURI().toString());
            this.imageView.setImage(image);

            progressIndicator.setVisible(false);
            this.rootPane.setDisable(false);
        });
        creating.start();
    }


    private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(800));
        fadeTransition.setNode(this.rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((ActionEvent event) -> loadNextScene());
        fadeTransition.play();
    }


    private void makeFadeIn() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(800));
        fadeTransition.setNode(this.rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished((ActionEvent event) -> createCloud());
        fadeTransition.play();
    }


    private void loadNextScene() {
        Parent newView;
        try {
            newView = (StackPane) FXMLLoader.load(getClass().getResource("../home/home.fxml"));
            Scene newScene = new Scene(newView);
            Stage stage = (Stage) this.rootPane.getScene().getWindow();
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
