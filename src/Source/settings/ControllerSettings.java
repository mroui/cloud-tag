package Source.settings;


import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Source.Cloud;
import Source.uploadImage.ControllerUploadImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerSettings  implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Pane rootPane;
    @FXML
    private Slider tagsCount, minLengthTag, paddingTags, fontSizeFrom, fontSizeTo;
    @FXML
    private ColorPicker color1, color2, color3, color4, color5;

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
        this.rootPane.setOpacity(0);
        makeFadeIn();

        this.fontSizeFrom.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.fontSizeTo.setMin(this.fontSizeFrom.getValue());
            this.fontSizeTo.setValue(this.fontSizeFrom.getValue());
        });
    }


    @FXML
    private void ExitActionButton(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private void NextActionButton() {
        makeFadeOut();
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
        fadeTransition.play();
    }


    private void loadNextScene() {
        Parent newView;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../uploadImage/uploadImage.fxml"));
            loader.load();

            ControllerUploadImage controllerUploadImage = loader.getController();
            Cloud cloud = new Cloud((int)tagsCount.getValue(), (int)minLengthTag.getValue(), (int)paddingTags.getValue(),
                    (int)fontSizeFrom.getValue(), (int)fontSizeTo.getValue(), color1.getValue(),
                    color2.getValue(), color3.getValue(), color4.getValue(), color5.getValue(), null);
            controllerUploadImage.set(cloud);

            newView = loader.getRoot();
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
