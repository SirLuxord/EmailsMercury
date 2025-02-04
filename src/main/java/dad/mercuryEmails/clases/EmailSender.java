package dad.mercuryEmails.clases;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private StringProperty username;
    private StringProperty password;
    private StringProperty email;
    private StringProperty asunto;
    private StringProperty contenido;

    public EmailSender(StringProperty username, StringProperty password, StringProperty email, StringProperty asunto, StringProperty contenido) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.asunto = asunto;
        this.contenido = contenido;
    }

    public EmailSender() {
        this.username = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.asunto = new SimpleStringProperty("");
        this.contenido = new SimpleStringProperty("");
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAsunto() {
        return asunto.get();
    }

    public StringProperty asuntoProperty() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto.set(asunto);
    }

    public String getContenido() {
        return contenido.get();
    }

    public StringProperty contenidoProperty() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido.set(contenido);
    }

    public Boolean sendEmail() {

        // Configuración del servidor Mercury SMTP
        String host = "127.0.0.1"; // O localhost, es lo mismo
        int port = 25;

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "false"); // Habilitar autenticación

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username.get(), password.get());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username.get()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.get()));
            message.setSubject(asunto.get());
            message.setText(contenido.get());

            Transport.send(message);
            System.out.println("Se envio el correo");
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
}
