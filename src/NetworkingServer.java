import java.io.*;
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

        // Lyssna efter klienten
        while(true) {

            try {

                // Lyssnar på anslutning från klienten och accepterar anslutningen
                System.out.println("Waiting for connect request...");
                client = server.accept();

                // Hämta host address hos klienten samt klient porten
                System.out.println("Connect request is accepted...");
                String clientHost = client.getInetAddress().getHostAddress();
                int clientPort = client.getPort();
                System.out.println("Client host = " + clientHost + " Client port = " + clientPort);

                // Läs data from klienten
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(clientIn));
                String msgFromClient = br.readLine();
                System.out.println("Message received from client = " + msgFromClient);

                // Skicka svarsmedelande till klienten
                if (msgFromClient != null && !msgFromClient.equalsIgnoreCase("bye")) {
                    OutputStream clientOut = client.getOutputStream();
                    PrintWriter pw = new PrintWriter(clientOut, true);
                    String ansMsg = "Hello, " + msgFromClient;
                    pw.println(ansMsg);
                }

                // Avsluta alla sockets
                if (msgFromClient != null && msgFromClient.equalsIgnoreCase("bye")) {
                    server.close();
                    client.close();
                    break;
                }

            } catch (IOException ie) {
            }
        }
    }
}