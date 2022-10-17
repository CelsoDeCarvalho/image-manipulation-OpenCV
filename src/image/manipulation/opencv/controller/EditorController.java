/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.manipulation.opencv.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author decarvalho
 */
public class EditorController implements Initializable {

    public static File file;
    private final Alert alert = new Alert(AlertType.NONE);

    @FXML
    private ImageView image;

    @FXML
    private ScrollPane imageWrapper;

    public static void setFile(File file) {
        EditorController.file = file;
    }

    @FXML
    private TextField angleValue;
    @FXML
    private Slider brilho;
    @FXML
    private Slider saturacao;
    @FXML
    private Slider contraste;

    private final ColorAdjust colorAdjust = new ColorAdjust();
    @FXML
    private TextField transX;
    @FXML
    private TextField transY;

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
                image.fitHeightProperty().bind(imageWrapper.heightProperty().subtract(300));
                image.fitWidthProperty().bind(imageWrapper.widthProperty().subtract(500));
                image.setTranslateX(180);
                image.setTranslateY(130);
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
        double value = image.getRotate();
        image.setRotate(value + getAngleValue());
    }

    @FXML
    private void onLeftRotate(ActionEvent event) {
        double value = image.getRotate();
        image.setRotate(value - getAngleValue());
    }

    @FXML
    private void onScaleLess(ActionEvent event) {
        double scaleXValue = image.getScaleX();
        double scaleYValue = image.getScaleX();
        image.setScaleX(scaleXValue - 0.05);
        image.setScaleY(scaleYValue - 0.05);
    }

    @FXML
    private void onScalePlus(ActionEvent event) {
        double scaleXValue = image.getScaleX();
        double scaleYValue = image.getScaleX();
        image.setScaleX(scaleXValue + 0.05);
        image.setScaleY(scaleYValue + 0.05);
    }

    @FXML
    void onSaveImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        File savedFile = fileChooser.showSaveDialog(null);
        if (savedFile != null) {
            try {
                BufferedImage bImage = SwingFXUtils.fromFXImage(image.snapshot(null, null), null);
                ImageIO.write(bImage, "png", savedFile);
                alert.setTitle("Gravar imagem");
                alert.setContentText("Imagem gravada com sucesso");
                alert.setAlertType(AlertType.INFORMATION);

            } catch (IOException ex) {
                alert.setTitle("Gravar imagem");
                alert.setContentText("Falha ao gravar a imagem");
                alert.setAlertType(AlertType.ERROR);
            } finally {
                alert.show();
            }
        }
    }

    @FXML
    private void onCropChange(ActionEvent event) {
    }

    @FXML
    private void onPB(ActionEvent event) {
        colorAdjust.setSaturation(-1);
        image.setEffect(colorAdjust);
    }

    @FXML
    private void onBrilho(MouseEvent event) {
        colorAdjust.setBrightness(brilho.getValue());
        image.setEffect(colorAdjust);
    }

    @FXML
    private void onSaturacao(MouseEvent event) {
        colorAdjust.setSaturation(saturacao.getValue());
        image.setEffect(colorAdjust);
    }

    @FXML
    private void onContraste(MouseEvent event) {
        colorAdjust.setContrast(contraste.getValue());
        image.setEffect(colorAdjust);
    }

    @FXML
    private void onSepia(ActionEvent event) {
        SepiaTone sepiaTone = new SepiaTone();
        sepiaTone.setLevel(0.7);
        image.setEffect(sepiaTone);
    }

    @FXML
    private void onNormal(ActionEvent event) {
        image.setEffect(null);
    }

    private void onDragged(MouseEvent event) {

    }

    @FXML
    private void onTopTrans(ActionEvent event) {
        double scaleYValue = image.getTranslateY();
        image.setTranslateY(scaleYValue - 10);
    }

    @FXML
    private void onLeftTrans(ActionEvent event) {
        double scaleXValue = image.getTranslateX();
        image.setTranslateX(scaleXValue - 10);
    }

    @FXML
    private void onBorderTrans(ActionEvent event) {
        image.setTranslateX(Integer.parseInt(transX.getText()));
        
        image.setTranslateY(Integer.parseInt(transY.getText()));
    }

    @FXML
    private void onRightTrans(ActionEvent event) {
        double scaleXValue = image.getTranslateX();
        image.setTranslateX(scaleXValue + 10);
    }

    @FXML
    private void onDownTrans(ActionEvent event) {
        double scaleYValue = image.getTranslateY();
        image.setTranslateY(scaleYValue + 10);
    }

}
