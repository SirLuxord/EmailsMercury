package dad.mercuryEmails;

import javafx.application.Application;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Application.launch(EmailApp.class, args);
    }
}
