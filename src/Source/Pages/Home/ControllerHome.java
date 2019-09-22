package Source.Pages.Home;

import Source.Others.FadeLoadNextScene;
import Source.Interfaces.InitializeScene;
import Source.Interfaces.LoadSceneInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerHome extends FadeLoadNextScene implements Initializable, LoadSceneInterface, InitializeScene {

    @FXML
    private Button exitButton;
    @FXML
    private Pane homePane;

//====================================================

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init(this.exitButton, this.homePane, "src/Assets/Images/x.png");
        makeFadeIn(this.homePane);
    }


    @FXML
    private void ExitActionButton(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private void startActionButton() {
        makeFadeOut(this.homePane, this, "../websiteTags/websiteTags.fxml");
    }
}
