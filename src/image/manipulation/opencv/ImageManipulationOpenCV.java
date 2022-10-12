package image.manipulation.opencv;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author decarvalho
 */
public class ImageManipulationOpenCV extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/image/manipulation/opencv/view/IMO.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setTitle("IMO-CV");
        stage.setScene(scene);
        stage.centerOnScreen();
        this.stage = stage;
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }
}
