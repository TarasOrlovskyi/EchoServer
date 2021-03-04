package orlovskyi;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //in what order should we close the streams? Does the order of closing streams matter?
        try (ServerSocket serverSocket = new ServerSocket(3000);
             Socket socket = serverSocket.accept();
             BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());
             BufferedInputStream input = new BufferedInputStream(socket.getInputStream())
        ) {
            int count;
            byte[] bytes = new byte[100];
            while (true) {
                count = input.read(bytes);
                String message = new String(bytes, 0, count);
                message = "echo " + message;
                output.write(message.getBytes());
                output.flush();
                if (message.equals("echo stop")){
                    break;
                }
            }
        }
    }
}
