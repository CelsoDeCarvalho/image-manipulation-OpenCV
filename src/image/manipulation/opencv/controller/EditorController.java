/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.manipulation.opencv.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author decarvalho
 */
public class EditorController implements Initializable {

    public static File file;

    @FXML
    private ImageView image;

    public static void setFile(File file) {
        EditorController.file = file;
    }

    @FXML
    private AnchorPane imagePanel;

    @FXML
    private TextField angleValue;

    void testar(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (file != null) {
            try {
                url = file.toURI().toURL();
                image.setImage(new Image(url.toString()));
//                image.fitHeightProperty().bind(imagePanel.heightProperty());
//                image.fitWidthProperty().bind(imagePanel.widthProperty());
            } catch (MalformedURLException ex) {
                Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private int getAngleValue() {
        if (!angleValue.getText().isEmpty()) {
            return Integer.parseInt(angleValue.getText());
        }
        return 20;
    }

    @FXML
    private void onRigthRotate(ActionEvent event) {
        System.out.println(angleValue.getText());
        double value = image.getRotate();
        image.setRotate(value + getAngleValue());
    }

    @FXML
    private void onLeftRotate(ActionEvent event) {
        double value = image.getRotate();
        image.setRotate(value - getAngleValue());
    }

    @FXML
    private void onAngleChange(ActionEvent event) {
    }

}
