// javac *.java || javac -target 1.8 -source 1.8 *.java
// start rmiregistry || rmiregistry & 
// java Server
// java Client
// On Server give "localhost"

// ServerInterface.java
import java.rmi.*;

public interface ServerInterface extends Remote {
    String concat(String a, String b) throws RemoteException;
}