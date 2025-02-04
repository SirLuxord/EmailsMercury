package dad.mercuryEmails.controllers.login;

import dad.mercuryEmails.clases.EmailSender;
import dad.mercuryEmails.models.Usuario;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateUsuarioDialog extends Dialog<Usuario> implements Initializable {

    // Model
    private ObjectProperty<Usuario> usuario = new SimpleObjectProperty<>(new Usuario());

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        // Dialog

        setTitle("Crear");
        setHeaderText("Introduzca los datos del usuario a crear:");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(
                new ButtonType("Crear", ButtonBar.ButtonData.OK_DONE),
                ButtonType.CANCEL
        );
        setResultConverter(this::onResult);

        // Bindings

        usuario.get().usuarioProperty().bind(usuarioTextField.textProperty());
        usuario.get().contraseñaProperty().bind(contraseñaTextField.textProperty());
        usuario.get().nombreUsuarioProperty().bind(nombreUsuarioTextField.textProperty());

    }

    public CreateUsuarioDialog() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/crearUsuarioView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Usuario onResult(ButtonType buttonType) {
        if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE){
            usuario.get().setEmail(usuario.get().getUsuario() + "@localhost");
            return usuario.get();
        }
        return null;
    }

    @FXML
    private PasswordField contraseñaTextField;

    @FXML
    private TextField nombreUsuarioTextField;

    @FXML
    private GridPane root;

    @FXML
    private TextField usuarioTextField;

    public GridPane getRoot() {
        return root;
    }
}
