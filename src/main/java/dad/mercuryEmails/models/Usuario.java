package dad.mercuryEmails.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    private StringProperty usuario = new SimpleStringProperty();
    private StringProperty contraseña = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty nombreUsuario = new SimpleStringProperty();

    public Usuario() {
    }

    public Usuario(StringProperty usuario, StringProperty contraseña, StringProperty email, StringProperty nombreUsuario) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
    }

    public String getUsuario() {
        return usuario.get();
    }

    public StringProperty usuarioProperty() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public String getContraseña() {
        return contraseña.get();
    }

    public StringProperty contraseñaProperty() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña.set(contraseña);
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

    public String getNombreUsuario() {
        return nombreUsuario.get();
    }

    public StringProperty nombreUsuarioProperty() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario.set(nombreUsuario);
    }
}
