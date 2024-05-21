import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkingServer {

    public static void main(String [] args) {

        // Skapa server och client variabel
        ServerSocket server = null;
        Socket client;

        // Välj portnummer. 8080 hard kodat och annars så förs porten in som en inparameter av jvm
        int portnumber = 8080;
        if (args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }

        // Skapa en server socket. Avsluta programmet med status kod 1 om något skulle gå fel och printa fel medelande
        try {
            server = new ServerSocket(portnumber);
        } catch (IOException ie) {
            System.out.println("Cannot open socket." + ie);
            System.exit(1);
        }
        
        System.out.println("ServerSocket is created " + server);
    }
}