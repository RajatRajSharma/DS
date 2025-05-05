// javac *.java || javac -target 1.8 -source 1.8 *.java
// start rmiregistry || rmiregistry & 
// java Server
// java Client
// On Server give "localhost"

// Server.java
import java.rmi.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            Servant s = new Servant();
            Naming.rebind("Server", s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}