package dad.mercuryEmails.controllers.email;

import dad.mercuryEmails.clases.EmailSender;
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

public class SendEmailDialog extends Dialog<EmailSender> implements Initializable {

    // Model

    private ObjectProperty<EmailSender> email = new SimpleObjectProperty<>(new EmailSender());
    private String emailEmisor = "";
    private String asunto = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Dialog

        setTitle("Enviar email");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(
                new ButtonType("Enviar", ButtonBar.ButtonData.OK_DONE),
                ButtonType.CANCEL
        );
        setResultConverter(this::onResult);

        // Set

        emailTextField.setText(emailEmisor);
        asuntoTextField.setText(asunto);

        // Bindings

        email.get().emailProperty().bind(emailTextField.textProperty());
        email.get().asuntoProperty().bind(asuntoTextField.textProperty());
        email.get().contenidoProperty().bind(contenidoTextField.textProperty());

    }

    private EmailSender onResult(ButtonType buttonType) {
        if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE){
            return email.get();
        }
        return null;
    }

    public SendEmailDialog() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/enviarEmailDialog.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendEmailDialog(String emailEmisor, String asunto) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/enviarEmailDialog.fxml"));
            loader.setController(this);
            loader.load();
            this.emailEmisor = emailEmisor;
            this.asunto = asunto;
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public GridPane getRoot() {
        return root;
    }
}