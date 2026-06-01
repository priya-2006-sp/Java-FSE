import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class TCPChatServer {
    public static void main(String[] args) {
        int port = 5000;
        System.out.println("Starting TCP Server on port " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Waiting for a client to connect...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());
            Thread receiveThread = new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String clientMessage;
                    while ((clientMessage = in.readLine()) != null) {
                        System.out.println("\nClient: " + clientMessage);
                        System.out.print("Server (You): ");
                    }
                } catch (Exception e) {
                    System.out.println("\nReceive connection closed.");
                }
            });
            Thread sendThread = new Thread(() -> {
                try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
                    String serverMessage;
                    System.out.print("Server (You): ");
                    while ((serverMessage = console.readLine()) != null) {
                        out.println(serverMessage);
                        System.out.print("Server (You): ");
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
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
