package dad.mercuryEmails.consultas;

import dad.mercuryEmails.HikariConnection;
import dad.mercuryEmails.models.Usuario;

import java.sql.*;

import static dad.mercuryEmails.consultas.Errores.mostrarError;

public class ConsultasUsuario {

    public static Boolean buscarUsuario(String usuario) {

        String query = "SELECT usuario FROM usuarios WHERE usuario = ?";

        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usuario);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Devuelve true si hay al menos un resultado

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void insetarUsuario(Usuario usuario){
        String query = "Insert into usuarios (usuario, contraseña, email, nombreUsuario) VALUES (?,?,?,?)";
        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1 , usuario.getUsuario());
            statement.setString(2 , usuario.getContraseña());
            statement.setString(3 , usuario.getEmail());
            statement.setString(4 , usuario.getNombreUsuario());
            statement.execute();
        }  catch (SQLException e) {
            mostrarError(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public static Boolean iniciarSesion(String usuario, String contraseña){
        String query = "SELECT usuario FROM usuarios WHERE usuario = ? AND contraseña = ?";
        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contraseña);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Devuelve true si hay al menos un resultado

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
