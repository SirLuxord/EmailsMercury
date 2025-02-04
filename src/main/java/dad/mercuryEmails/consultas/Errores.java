package dad.mercuryEmails.consultas;

import javafx.scene.control.Alert;

public class Errores {
    public static void mostrarError(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Ha habido algún error");
        errorAlert.setContentText("Error: " + error);
        errorAlert.showAndWait();
    }
}
