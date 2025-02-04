package dad.mercuryEmails.controllers.email;

import dad.mercuryEmails.EmailApp;
import dad.mercuryEmails.clases.EmailReceiver;
import dad.mercuryEmails.clases.EmailSender;
import dad.mercuryEmails.controllers.login.CreateUsuarioDialog;
import dad.mercuryEmails.models.EmailRecibido;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static dad.mercuryEmails.consultas.ConsultasEmailRecibidos.eliminarEmailsRecibidos;
import static dad.mercuryEmails.consultas.ConsultasEmailRecibidos.updateEmails;
import static dad.mercuryEmails.consultas.Errores.mostrarError;

public class RootController implements Initializable {

    // Model
    private String email;
    private String usuario;
    private String contraseña;
    private ObjectProperty<EmailRecibido> selectedEmail = new SimpleObjectProperty<>();
    private ListProperty<EmailRecibido> listaEmailRecibidos = new SimpleListProperty<>(FXCollections.observableArrayList());


    @FXML
    private TableColumn<EmailRecibido, String> asuntoColumn;

    @FXML
    private TableColumn<EmailRecibido, String> dateColumn;

    @FXML
    private TableView<EmailRecibido> emailsTable;

    @FXML
    private TableColumn<EmailRecibido, String> remitenteColumn;

    @FXML
    private Button openEmailButton;

    @FXML
    private GridPane root;



    @FXML
    void onSendEmailAction(ActionEvent event) {

        SendEmailDialog sendEmailDialog = new SendEmailDialog();
        Optional<EmailSender> result = sendEmailDialog.showAndWait();
        result.ifPresent(email -> {

            email.setUsername(usuario);
            email.setPassword(contraseña);
            System.out.println(email.getEmail());
            System.out.println(email.getAsunto());
            System.out.println(email.getContenido());
            if (!email.sendEmail()){
                mostrarError("Error al enviar el correo.");
                throw new IllegalArgumentException("Error al enviar el correo.");
            }
        });
    }

    @FXML
    void onUpdateEmailsAction(ActionEvent event) {
        actualizarEmails();
    }

    @FXML
    void onLogoutAction(ActionEvent event) {
        EmailApp.mostarLogin();
        EmailApp.ocultarRoot();
    }

    @FXML
    void onOpenEmailAction(ActionEvent event) {
        EmailRecibido emailSeleccionado = selectedEmail.get();

        EmailController emailController = EmailApp.getEmailController();
        emailController.setEmailInfo(usuario, contraseña,
                emailSeleccionado.getEmailRemitente(),
                emailSeleccionado.getAsunto(),
                emailSeleccionado.getContenido());


        EmailApp.mostrarEmail();
        EmailApp.ocultarRoot();

    }

    public RootController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e){
            throw  new RuntimeException();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        openEmailButton.setDisable(true);

        selectedEmail.addListener((o, ov, nv) -> {
            if (nv != null){
                openEmailButton.setDisable(false);
            } else {
                openEmailButton.setDisable(true);
            }
        });

        // Bindings

        selectedEmail.bind(emailsTable.getSelectionModel().selectedItemProperty());
        emailsTable.itemsProperty().bind(listaEmailRecibidos);

        // Cell Bindings

        remitenteColumn.setCellValueFactory(cellData -> cellData.getValue().emailRemitenteProperty());
        asuntoColumn.setCellValueFactory(cellData -> cellData.getValue().asuntoProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());

    }

    public void actualizarEmails() {
        eliminarEmailsRecibidos();
        EmailReceiver emailReceiver = new EmailReceiver(usuario, contraseña);
        emailReceiver.receiveEmails(email);
        listaEmailRecibidos.clear();
        listaEmailRecibidos.addAll(updateEmails(email));
    }

    public void setUsuarioYContraseña(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.email = usuario + "@localhost";
    }

    public GridPane getRoot() {
        return root;
    }
}
