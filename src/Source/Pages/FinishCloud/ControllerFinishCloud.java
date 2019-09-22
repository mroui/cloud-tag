package Source.Pages.FinishCloud;

import Source.Interfaces.InitializeScene;
import Source.Interfaces.LoadScene;
import Source.Others.Cloud;
import Source.Others.FadeLoadNextScene;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerFinishCloud extends FadeLoadNextScene implements Initializable, InitializeScene, LoadScene {

    @FXML
    private Button exitButton;
    @FXML
    private Pane rootPane;
    @FXML
    private ImageView imageView;
    @FXML
    private ProgressIndicator progressIndicator;

    private Cloud cloud;

//====================================================

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init(this.exitButton, this.rootPane, "src/Assets/Images/x.png");
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
        makeFadeOut(this.rootPane, this, "../Home/home.fxml");
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


    private void makeFadeIn() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(800));
        fadeTransition.setNode(this.rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished((ActionEvent event) -> createCloud());
        fadeTransition.play();
    }
}
