// javac -target 1.8 -source 1.8 ClockServer.java ClockClient.java
// java ClockServer
// java ClockClient

// ClockServer.java

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class ClockServer {

    private static final int PORT = 8080;
    private static final Map<String, ClientInfo> clientData = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Clock Server started at port " + PORT);

        // Thread to accept clients
        new Thread(() -> {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    String clientId = clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort();
                    System.out.println(clientId + " connected.");
                    new Thread(new ClientHandler(clientSocket, clientId)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Thread to synchronize clocks
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println("Starting synchronization cycle...");

                    if (!clientData.isEmpty()) {
                        long avgDiff = clientData.values().stream()
                                .mapToLong(ClientInfo::getTimeDiff)
                                .sum() / clientData.size();

                        for (Map.Entry<String, ClientInfo> entry : clientData.entrySet()) {
                            Socket socket = entry.getValue().getSocket();
                            OutputStream out = socket.getOutputStream();
                            long synchronizedTime = System.currentTimeMillis() + avgDiff;
                            out.write((synchronizedTime + "\n").getBytes());
                        }

                    } else {
                        System.out.println("No clients to synchronize.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;
        private final String clientId;

        ClientHandler(Socket socket, String clientId) {
            this.socket = socket;
            this.clientId = clientId;
        }

        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                while (true) {
                    String clientTimeStr = reader.readLine();
                    long clientTime = Long.parseLong(clientTimeStr.trim());
                    long serverTime = System.currentTimeMillis();
                    long timeDiff = serverTime - clientTime;

                    clientData.put(clientId, new ClientInfo(clientTime, timeDiff, socket));
                    System.out.println("Updated client " + clientId + " diff: " + timeDiff + " ms");
                    Thread.sleep(5000);
                }
            } catch (Exception e) {
                System.out.println("Client " + clientId + " disconnected.");
                clientData.remove(clientId);
            }
        }
    }

    static class ClientInfo {
        private final long clientTime;
        private final long timeDiff;
        private final Socket socket;

        ClientInfo(long clientTime, long timeDiff, Socket socket) {
            this.clientTime = clientTime;
            this.timeDiff = timeDiff;
            this.socket = socket;
        }

        public long getTimeDiff() {
            return timeDiff;
        }

        public Socket getSocket() {
            return socket;
        }
    }
}
