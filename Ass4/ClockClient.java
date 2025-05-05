// javac -target 1.8 -source 1.8 ClockServer.java ClockClient.java
// java ClockServer
// java ClockClient

// ClockClient.java

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;

public class ClockClient {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Connected to server.");

            // Send time periodically
            new Thread(() -> {
                try {
                    OutputStream out = socket.getOutputStream();
                    while (true) {
                        long currentTime = System.currentTimeMillis();
                        out.write((currentTime + "\n").getBytes());
                        System.out.println("Sent time: " + currentTime);
                        Thread.sleep(5000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            // Receive synchronized time
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    while (true) {
                        String syncTimeStr = reader.readLine();
                        long syncTime = Long.parseLong(syncTimeStr.trim());
                        System.out.println("Synchronized time received: " +
                                new SimpleDateFormat("HH:mm:ss.SSS").format(syncTime));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
