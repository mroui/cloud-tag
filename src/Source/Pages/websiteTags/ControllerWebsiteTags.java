package Source.Pages.websiteTags;


import Source.Pages.websiteTags.Others.SaveTagsToFile;
import Source.Pages.websiteTags.Others.Website;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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


public class ControllerWebsiteTags implements Initializable {

    @FXML
    private Pane rootPane;
    @FXML
    private Button exitButton;
    @FXML
    private TextField urlTextField;
    @FXML
    private TextArea textAreaTags;
    @FXML
    private ProgressIndicator progressIndicator;

    private double x, y;
    private String textTags;

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

    @FXML
    public void onEnter(){
        CheckActionButton();
    }


    @FXML
    private void ExitActionButton(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private void CheckActionButton() {
        Thread websiteTags = new Thread(() -> {
            progressIndicator.setVisible(true);
            this.rootPane.setDisable(true);

            Website website = new Website(this.urlTextField.getText());
            this.textTags = website.isValid();
            this.textAreaTags.setScrollTop(0);
            this.textAreaTags.setScrollLeft(0);
            this.textAreaTags.clear();
            this.textAreaTags.appendText(this.textTags);

            progressIndicator.setVisible(false);
            this.rootPane.setDisable(false);
        });
        websiteTags.start();
    }


    @FXML
    private void NextActionButton() {
        if (!this.textTags.contains("#BŁĄD: ")) {
            if(this.textTags.length()!=0) {
                SaveTagsToFile saveTagsToFile = new SaveTagsToFile(this.textTags);
                saveTagsToFile.save();
                makeFadeOut();
            }
        }
    }


    private void loadNextScene() {
        Parent newView;
        try {
            newView = (StackPane) FXMLLoader.load(getClass().getResource("../Pages/settings/settings.fxml"));
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
}
