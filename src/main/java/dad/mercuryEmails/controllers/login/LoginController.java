package dad.mercuryEmails.controllers.login;

import dad.mercuryEmails.EmailApp;
import dad.mercuryEmails.models.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static dad.mercuryEmails.clases.CreacionUsuarios.*;
import static dad.mercuryEmails.consultas.ConsultasUsuario.*;
import static dad.mercuryEmails.consultas.Errores.mostrarError;

public class LoginController implements Initializable {



    public LoginController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loginView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e){
            throw  new RuntimeException();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private PasswordField passwordField;

    @FXML
    private BorderPane root;

    @FXML
    private TextField usuarioField;

    @FXML
    void onCreateUserAction(ActionEvent event) {

        CreateUsuarioDialog createDialog = new CreateUsuarioDialog();
        Optional<Usuario> result = createDialog.showAndWait();
        result.ifPresent(usuario -> {

            if (!buscarUsuario(usuario.getUsuario())){

                if (usuario.getUsuario().trim().isEmpty() || usuario.getContraseña().trim().isEmpty() || usuario.getNombreUsuario().trim().isEmpty()){
                    mostrarError("Los campos de usuario, nombre usario y contraseña no pueden estar vacíos.");
                    throw new IllegalArgumentException("Los campos de usuario, nombre usario y contraseña no pueden estar vacíos.");
                } else {
                    try {
                        crearContraseña(usuario.getUsuario(), usuario.getContraseña());
                        agregarUsuarioAlArchivo(usuario.getUsuario(), usuario.getNombreUsuario());
                        insetarUsuario(usuario);
                        reiniciarMercury();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            } else {
                mostrarError("El usuario ya existe.");
                throw new IllegalArgumentException("El usuario ya exsite en la base de datos.");
            }

        });

    }

    @FXML
    void onLoginAction(ActionEvent event) {
        if (!iniciarSesion(usuarioField.textProperty().get(), passwordField.textProperty().get())) {
            mostrarError("Usuario o contraseña incorrectos");
        } else {
            EmailApp.getRootController().setUsuarioYContraseña(usuarioField.getText(), passwordField.getText());
            EmailApp.getRootController().actualizarEmails();
            EmailApp.mostrarRoot();
            EmailApp.ocultarLogin();
            passwordField.setText("");
        }
    }

    public BorderPane getRoot() {
        return root;
    }
}
