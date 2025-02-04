package dad.mercuryEmails.consultas;

import dad.mercuryEmails.HikariConnection;
import dad.mercuryEmails.models.EmailRecibido;
import dad.mercuryEmails.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dad.mercuryEmails.consultas.Errores.mostrarError;

public class ConsultasEmailRecibidos {

    public static List<EmailRecibido> updateEmails(String email){
        ArrayList<EmailRecibido> listaEmails = new ArrayList<>();

        String query = "Select * FROM emailRecibido WHERE email = ?";
        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                EmailRecibido emailRecibido = new EmailRecibido();

                emailRecibido.setIdEmailRecibido(resultSet.getInt("idEmailRecibido"));
                emailRecibido.setEmailRemitente(resultSet.getString("emailRemitente"));
                emailRecibido.setEmail(resultSet.getString("email"));
                emailRecibido.setAsunto(resultSet.getString("asunto"));
                emailRecibido.setContenido(resultSet.getString("contenido"));
                emailRecibido.setFecha(resultSet.getString("fecha"));

                listaEmails.add(emailRecibido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmails;
    }


    public static void insertarEmailRecibido(EmailRecibido emailRecibido){
        String query = "Insert into emailRecibido  (emailRemitente, asunto, contenido, fecha, email) VALUES (?,?,?,?,?)";
        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1 , emailRecibido.getEmailRemitente());
            statement.setString(2 , emailRecibido.getAsunto());
            statement.setString(3 , emailRecibido.getContenido());
            statement.setString(4 , emailRecibido.getFecha());
            statement.setString(5 , emailRecibido.getEmail());
            statement.execute();
        }  catch (SQLException e) {
            mostrarError(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public static void eliminarEmailsRecibidos(){
        String query = "DELETE FROM emailRecibido WHERE 1=1";
        try (Connection connection = HikariConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.execute();

        }  catch (SQLException e) {
            mostrarError(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
