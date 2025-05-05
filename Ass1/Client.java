// javac *.java || javac -target 1.8 -source 1.8 *.java
// start rmiregistry || rmiregistry & 
// java Server
// java Client
// On Server give "localhost"

// Client.java
import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Enter the Server address : ");
            String server = s.nextLine();
            ServerInterface si = (ServerInterface) Naming.lookup("rmi://" + server + "/Server");
            System.out.println("Enter first string : ");
            String first = s.nextLine();
            System.out.println("Enter second string : ");
            String second = s.nextLine();
            System.out.println("Concatenated String : " + si.concat(first, second));
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}