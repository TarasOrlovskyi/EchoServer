package orlovskyi;

import java.io.*;
import java.net.Socket;

public class ClientReader {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 3001);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader inputConsole = new BufferedReader(new InputStreamReader(System.in))){
            String message;
            while (true){
                message=inputConsole.readLine();
                output.println(message);
                String answerFromServer = input.readLine();
                System.out.println("answer from server: " + answerFromServer);
                if (answerFromServer.equals("echo stop")){
                    break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
