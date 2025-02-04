package dad.mercuryEmails;

import dad.mercuryEmails.controllers.email.EmailController;
import dad.mercuryEmails.controllers.login.LoginController;
import dad.mercuryEmails.controllers.email.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmailApp extends Application {

    // Controllers
    private static RootController rootController = new RootController();
    private static LoginController loginController = new LoginController();
    private static EmailController emailController = new EmailController(); // Nuevo controlador

    // Stages
    private static Stage loginStage;
    private static Stage rootStage = new Stage();
    private static Stage emailStage = new Stage(); // Nuevo Stage para leer email

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(loginController.getRoot()));
        primaryStage.setTitle("Mercury emails");
        primaryStage.show();
        loginStage = primaryStage;

        rootStage.setScene(new Scene(rootController.getRoot()));
        emailStage.setScene(new Scene(emailController.getRoot())); // Cargar vista de leer email
    }

    // Métodos para mostrar/ocultar vistas
    public static void mostarLogin() {
        loginStage.show();
    }

    public static void mostrarRoot() {
        rootStage.show();
    }

    public static void mostrarEmail() {
        emailStage.show();
    }

    public static void ocultarLogin() {
        loginStage.hide();
    }

    public static void ocultarRoot() {
        rootStage.hide();
    }

    public static void ocultarEmail() {
        emailStage.hide();
    }

    // Métodos para obtener controladores
    public static RootController getRootController() {
        return rootController;
    }

    public static LoginController getLoginController() {
        return loginController;
    }

    public static EmailController getEmailController() {
        return emailController;
    }
}
