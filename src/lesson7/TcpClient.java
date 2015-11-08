package lesson7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TcpClient {

    private static final int DEFAULT_PORT = 9999;
    private static final String DEFAULT_HOST = "localhost";

    public static void main(String[] args) {
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            host = args[0];
        }
        try {
            if (args.length > 1) {
                port = Integer.parseInt(args[1]);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        try (Socket socket = new Socket(host, port);
             OutputStream out = socket.getOutputStream()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
                writer.flush();
            }

        } catch (IllegalArgumentException | SecurityException | IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
