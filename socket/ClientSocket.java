import java.io.*;
import java.net.*;

public class ClientSocket {
    public static void main(String[] args) {
        String hostname = "localhost"; // as the server address
        int port = 12345; // Port number
        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to server: " + hostname + " on port " + port);

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while (true) {
                System.out.print("Enter message to send (type 'exit' to quit): ");
                userInput = consoleInput.readLine();

                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }

                output.println(userInput);

                String serverResponse = input.readLine();
                System.out.println("Server response: " + serverResponse);
            }

            // Close resources
            output.close();
            input.close();
            consoleInput.close();
            System.out.println("is disconnected from server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
