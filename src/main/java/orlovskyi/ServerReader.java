package orlovskyi;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerReader {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3001);
             Socket socket = serverSocket.accept();
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String message;
            while (true) {
                message = input.readLine();
                output.println("echo " + message);
                if (message.equals("stop")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
