package Source.Pages.websiteTags;

import Source.Interfaces.InitializeScene;
import Source.Interfaces.LoadSceneInterface;
import Source.Others.FadeLoadNextScene;
import Source.Pages.websiteTags.Others.SaveTagsToFile;
import Source.Pages.websiteTags.Others.Website;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerWebsiteTags extends FadeLoadNextScene implements Initializable, LoadSceneInterface, InitializeScene {

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

    private String textTags;

//====================================================

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init(this.exitButton, this.rootPane, "src/Assets/Images/x.png");
        makeFadeIn(this.rootPane);
    }


    @FXML
    public void onEnter() {
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
            if (this.textTags.length() != 0) {
                SaveTagsToFile saveTagsToFile = new SaveTagsToFile(this.textTags);
                saveTagsToFile.save();
                makeFadeOut(this.rootPane, this, "../settings/settings.fxml");
            }
        }
    }
}
