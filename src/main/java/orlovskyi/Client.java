package orlovskyi;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 3000);
             OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
             InputStream inputStream = new BufferedInputStream(socket.getInputStream());
             InputStream inputConsole = new BufferedInputStream(System.in)
             ) {
            int countConsole;
            byte[] bytesConsole = new byte[100];
            byte[] bytesAnswerServer = new byte[100];
            while (true) {
                countConsole = inputConsole.read(bytesConsole);
                String messageToServer = new String(bytesConsole, 0, countConsole-1);
                outputStream.write(messageToServer.getBytes());
                outputStream.flush();
                int count = inputStream.read(bytesAnswerServer);
                String answerFromServer = new String(bytesAnswerServer, 0, count);
                System.out.println("server response: " + answerFromServer);
                if (answerFromServer.equals("echo stop")){
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
