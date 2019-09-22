package Source.Pages.uploadImage;


import javafx.animation.FadeTransition;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import Source.Others.Cloud;
import Source.Pages.finishCloud.ControllerFinishCloud;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerUploadImage implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Pane rootPane;
    @FXML
    private ImageView imageView;

    private boolean isLoadedImg;
    private Image image;
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
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.imageView.setImage(image);
            this.cloud.setImageName(file.getName());
            this.cloud.setPath(file.toString());
            this.isLoadedImg = true;
        } catch (Exception e) { }
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
            loader.setLocation(getClass().getResource("../finishCloud/finishCloud.fxml"));
            loader.load();

            ControllerFinishCloud controllerFinishCloud = loader.getController();
            this.cloud.setImage(this.image);
            controllerFinishCloud.set(this.cloud);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
