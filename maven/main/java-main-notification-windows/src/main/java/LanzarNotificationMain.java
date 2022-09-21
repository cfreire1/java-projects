import java.awt.*;

public class LanzarNotificationMain {
    public static void main(String[] args) {
        try{
            SystemTray tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().createImage("some-icon.png");

            TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("System tray icon demo");
            tray.add(trayIcon);

            // Mostrar notificación de información:
            trayIcon.displayMessage("texto1", "texto2.", TrayIcon.MessageType.INFO);
            // Error:
            // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.ERROR);
            // Warning:
            // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.WARNING);
            System.exit(0);
        }catch(Exception ex){
            System.err.print(ex);
        }
    }
}
