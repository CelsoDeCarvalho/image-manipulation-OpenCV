/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.manipulation.opencv.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author decarvalho
 */
public class Utility {
    
    public static void mostrarJanela(String caminho, String title, ActionEvent event) throws IOException {
        URL url = new File(caminho).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.show();
    }
}
