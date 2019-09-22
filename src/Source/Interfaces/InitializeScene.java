package Source.Interfaces;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface InitializeScene {

    default void init(Button exitButton, Pane pane, String xImagePath) {
        try {
            Image xImage = new Image(new FileInputStream(xImagePath));
            exitButton.setGraphic(new ImageView(xImage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pane.setOpacity(0);
    }
}
