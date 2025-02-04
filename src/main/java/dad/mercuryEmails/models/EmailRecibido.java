package dad.mercuryEmails.models;

import javafx.beans.property.*;

import java.sql.Date;

public class EmailRecibido {
    private IntegerProperty idEmailRecibido = new SimpleIntegerProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty emailRemitente = new SimpleStringProperty();
    private StringProperty asunto = new SimpleStringProperty();
    private StringProperty contenido = new SimpleStringProperty();
    private StringProperty fecha = new SimpleStringProperty();

    public EmailRecibido() {
    }

    public EmailRecibido(IntegerProperty idEmailRecibido, StringProperty email, StringProperty emailRemitente, StringProperty asunto, StringProperty contenido, StringProperty fecha) {
        this.idEmailRecibido = idEmailRecibido;
        this.email = email;
        this.emailRemitente = emailRemitente;
        this.asunto = asunto;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public int getIdEmailRecibido() {
        return idEmailRecibido.get();
    }

    public IntegerProperty idEmailRecibidoProperty() {
        return idEmailRecibido;
    }

    public void setIdEmailRecibido(int idEmailRecibido) {
        this.idEmailRecibido.set(idEmailRecibido);
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

    public String getEmailRemitente() {
        return emailRemitente.get();
    }

    public StringProperty emailRemitenteProperty() {
        return emailRemitente;
    }

    public void setEmailRemitente(String emailRemitente) {
        this.emailRemitente.set(emailRemitente);
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

    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }
}
