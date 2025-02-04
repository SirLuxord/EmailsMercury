package dad.mercuryEmails.clases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CreacionUsuarios {

    private static final String Usuario_Correo = "C:/XAMPP/MERCURYMAIL/MAIL/";

    // Método para crear la contraseña en el archivo PASSWD.PM
    public static void crearContraseña(String usuario, String contraseña) throws IOException {

        File correo = new File(Usuario_Correo + usuario);
        if (!correo.exists()) {
            correo.mkdirs();
            System.out.println("El usuario se ha creado correctamente.");
        }

        String Ruta_Pass = Usuario_Correo + usuario + "/PASSWD.PM";  // Cambiado a PASSWD.PM
        File userDir = new File(Usuario_Correo + usuario);

        // Crear el directorio del usuario si no existe
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        // Crear el contenido del archivo PASSWD.PM
        String passwdContent = "POP3_access: " + contraseña + "\n" + "APOP_secret: \n";

        // Escribir el contenido en el archivo PASSWD.PM
        Files.write(Paths.get(Ruta_Pass), passwdContent.getBytes(), StandardOpenOption.CREATE);
        System.out.println("Contraseña para el usuario " + usuario + " almacenada correctamente.");
    }

    public static void agregarUsuarioAlArchivo(String usuario, String nombreCompleto) throws IOException {
        String archivoUsuarios = "C:\\xampp\\MercuryMail\\MAIL\\PMAIL.USR";
        String tipoUsuario = "U";

        String contenidoUsuario = tipoUsuario + ";" + usuario + ";" + nombreCompleto + "\n";
        Files.write(Paths.get(archivoUsuarios), contenidoUsuario.getBytes(), StandardOpenOption.APPEND);
        System.out.println("Usuario " + usuario + " agregado correctamente a PMAIL.USR.");
    }

    public static void reiniciarMercury() {
        try {
            // Detener el proceso de Mercury
            System.out.println("Deteniendo el servidor Mercury...");
            ProcessBuilder stopBuilder = new ProcessBuilder("taskkill", "/IM", "Mercury.exe", "/F");
            Process stopProcess = stopBuilder.start();
            stopProcess.waitFor();
            System.out.println("Servidor Mercury detenido.");

            // Esperar un poco antes de reiniciar el servidor
            Thread.sleep(2000);  // Espera de 2 segundos para asegurarse de que el proceso se haya detenido completamente

            // Iniciar el servidor Mercury
            System.out.println("Iniciando el servidor Mercury...");
            ProcessBuilder startBuilder = new ProcessBuilder("C:\\xampp\\MercuryMail\\Mercury.exe");
            startBuilder.start();
//            startProcess.waitFor();
            System.out.println("Servidor Mercury iniciado.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error al intentar reiniciar el servidor Mercury.");
        }
    }
}
