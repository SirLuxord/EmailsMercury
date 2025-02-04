package dad.mercuryEmails.controllers.email;

import dad.mercuryEmails.EmailApp;
import dad.mercuryEmails.clases.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static dad.mercuryEmails.consultas.Errores.mostrarError;

public class EmailController implements Initializable {

    private String usuario;
    private String contraseña;
    private String email;
    private String asunto;
    private String contenido;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public EmailController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/leerEmailView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e){
            throw  new RuntimeException();
        }
    }

    @FXML
    private TextField asuntoTextField;

    @FXML
    private TextArea contenidoTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private GridPane root;

    @FXML
    void onAnswerAction(ActionEvent event) {

        EmailSender email = new EmailSender();
        email.setUsername(usuario);
        email.setPassword(contraseña);
        email.setEmail(emailTextField.getText());
        email.setAsunto(asuntoTextField.getText());
        email.setContenido(contenidoTextField.getText());
        if (!email.sendEmail()) {
            mostrarError("Error al enviar el correo.");
            throw new IllegalArgumentException("Error al enviar el correo.");
        }

    }

    @FXML
    void onBackAction(ActionEvent event) {
        EmailApp.mostrarRoot();
        EmailApp.ocultarEmail();
    }

    public void setEmailInfo(String usuario,String contraseña,String email, String asunto, String contenido) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.asunto = "Re: " + asunto;
        this.contenido = contenido;

        emailTextField.setText(email);
        asuntoTextField.setText(this.asunto);
        contenidoTextField.setText(this.contenido);
    }

    public GridPane getRoot() {
        return root;
    }

    public void setRoot(GridPane root) {
        this.root = root;
    }
}
