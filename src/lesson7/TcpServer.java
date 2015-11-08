package lesson7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.IllegalBlockingModeException;

public class TcpServer {

    private static final int DEFAULT_PORT = 9999;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        try {
            if (args.length > 0) {
                port = Integer.parseInt(args[0]);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             InputStream in = clientSocket.getInputStream()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IllegalArgumentException | SecurityException | IllegalBlockingModeException | IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
