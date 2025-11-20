package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class InputDialog {

    public static String text(String titulo, String msj){ //input dialog para pedir datos
        String resu;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(titulo);
        dialog.setHeaderText(null);
        dialog.setContentText(msj);
        Optional<String> result = dialog.showAndWait();
        resu = (result.isPresent()) ? result.get() : ""; // en caso de que tenga algo devuelbe eso de lo contrario de vuelve comillas vacias
        return resu;

    }
    public static void warning(String titulo, String msj){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.show();
    }
    public static void error(String titulo, String msj){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.show();
    }
    public static void information(String titulo, String msj){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.show();
    }


}
