import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class TCPChatClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;
        System.out.println("Connecting to TCP Server at " + host + ":" + port + "...");
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to the server!");
            Thread receiveThread = new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("\nServer: " + serverMessage);
                        System.out.print("Client (You): ");
                    }
                } catch (Exception e) {
                    System.out.println("\nReceive connection closed.");
                }
            });            
            Thread sendThread = new Thread(() -> {
                try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
                    String clientMessage;
                    System.out.print("Client (You): ");
                    while ((clientMessage = console.readLine()) != null) {
                        out.println(clientMessage);
                        System.out.print("Client (You): ");
                    }
                } catch (Exception e) {
                    System.out.println("\nSend connection closed.");
                }
            });
            receiveThread.start();
            sendThread.start();
            
            receiveThread.join();
            sendThread.join();
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
        }
    }
}
