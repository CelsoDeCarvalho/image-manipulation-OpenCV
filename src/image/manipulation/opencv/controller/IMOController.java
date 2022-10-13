/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.manipulation.opencv.controller;

import image.manipulation.opencv.service.Utility;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.FileChooser;

/**
 *
 * @author decarvalho
 */
public class IMOController implements Initializable {

    private FileChooser imageChooser;
    private Parent root;

    @FXML
    void loadImage(ActionEvent event) throws IOException {
        imageChooser = new FileChooser();
        imageChooser.setTitle("Selecione a imagem para editar");
        imageChooser.getExtensionFilters().clear();
        imageChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Foto", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.webp"));
        File file = imageChooser.showOpenDialog(null);
        if (file != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/image/manipulation/opencv/view/Editor.fxml"));
            root = loader.load();
            EditorController.setFile(file);
            Utility.mostrarJanela("src/image/manipulation/opencv/view/Editor.fxml", "Editor", event);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
