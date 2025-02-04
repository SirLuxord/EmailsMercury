package dad.mercuryEmails.clases;

import dad.mercuryEmails.models.EmailRecibido;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

import static dad.mercuryEmails.consultas.ConsultasEmailRecibidos.insertarEmailRecibido;

public class EmailReceiver {
    private String username;
    private String password;

    public EmailReceiver(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public EmailReceiver() {
    }

    public void receiveEmails(String email) {

        // Configuración del servidor Mercury SMTP
        String host = "127.0.0.1"; // O localhost, es lo mismo
        int port = 110;

        Properties props = new Properties();
        props.put("mail.pop3.host", host);
        props.put("mail.pop3.port", port);  // Puerto POP3 de Mercury
        props.put("mail.pop3.auth", "true");

        try {
            Session session = Session.getInstance(props);
            Store store = session.getStore("pop3");
            store.connect("localhost", username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            for (Message message : messages) {

                EmailRecibido emailRecibido = new EmailRecibido();

                emailRecibido.setEmailRemitente(message.getFrom()[0] + "");
                emailRecibido.setAsunto(message.getSubject());
                emailRecibido.setContenido(message.getContent().toString());
                emailRecibido.setFecha(message.getSentDate() + "");
                emailRecibido.setEmail(email);

                insertarEmailRecibido(emailRecibido);

            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}