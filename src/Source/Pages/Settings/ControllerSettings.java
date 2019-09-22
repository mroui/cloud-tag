package Source.Pages.Settings;

import Source.Interfaces.InitializeScene;
import Source.Interfaces.LoadScene;
import Source.Others.FadeLoadNextScene;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Source.Others.Cloud;
import Source.Pages.UploadImage.ControllerUploadImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerSettings extends FadeLoadNextScene implements Initializable, InitializeScene, LoadScene {

    @FXML
    private Button exitButton;
    @FXML
    private Pane rootPane;
    @FXML
    private Slider tagsCount, minLengthTag, paddingTags, fontSizeFrom, fontSizeTo;
    @FXML
    private ColorPicker color1, color2, color3, color4, color5;

//====================================================

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init(this.exitButton, this.rootPane, "src/Assets/Images/x.png");
        makeFadeIn(this.rootPane);

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


    private void loadNextScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../uploadImage/uploadImage.fxml"));
            loader.load();

            ControllerUploadImage controllerUploadImage = loader.getController();
            Cloud cloud = new Cloud((int)tagsCount.getValue(), (int)minLengthTag.getValue(), (int)paddingTags.getValue(),
                    (int)fontSizeFrom.getValue(), (int)fontSizeTo.getValue(), color1.getValue(),
                    color2.getValue(), color3.getValue(), color4.getValue(), color5.getValue(), null);
            controllerUploadImage.set(cloud);

            this.partialLoadNextScene(loader, this.rootPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
