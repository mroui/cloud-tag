package Source.Pages.UploadImage;

import Source.Interfaces.InitializeScene;
import Source.Interfaces.LoadScene;
import Source.Others.Cloud;
import Source.Others.FadeLoadNextScene;
import Source.Pages.FinishCloud.ControllerFinishCloud;
import javafx.animation.FadeTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;


public class ControllerUploadImage extends FadeLoadNextScene implements Initializable, InitializeScene, LoadScene {

    @FXML
    private Button exitButton;
    @FXML
    private Pane rootPane;
    @FXML
    private ImageView imageView;

    private boolean isLoadedImg;
    private Image image;
    private Cloud cloud;

//====================================================

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init(this.exitButton, this.rootPane, "src/Assets/Images/x.png");
        makeFadeIn(this.rootPane);
    }


    public ControllerUploadImage() {
        this.cloud = new Cloud();
        this.isLoadedImg = false;
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
    private void loadImageActionButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load image...");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        String currentPath = Paths.get("src/Input").toAbsolutePath().normalize().toString();
        fileChooser.setInitialDirectory(new File(currentPath));
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.imageView.setImage(image);
            this.cloud.setImageName(file.getName());
            this.cloud.setPath(file.toString());
            this.isLoadedImg = true;
        } catch (Exception e) {}
    }


    public void createImageActionButton() {
        if (this.isLoadedImg) {
            this.image = this.imageView.getImage();
            makeFadeOut();
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


    private void loadNextScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../finishCloud/finishCloud.fxml"));
            loader.load();

            ControllerFinishCloud controllerFinishCloud = loader.getController();
            this.cloud.setImage(this.image);
            controllerFinishCloud.set(this.cloud);

            this.partialLoadNextScene(loader, this.rootPane);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
