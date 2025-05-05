// java -version // "1.8.0_202"
// java -version // 1.8.0_202
// idlj -version // IDL-to-Java compiler (portable), version "3.2"
// idlj -fall Calc.idl
// javac -cp "C:\Program Files\Java\jdk1.8.0_202\jre\lib\rt.jar" Calculator/*.java *.java
// tnameserv -ORBInitialPort 1050
// java StartServer -ORBInitialPort 1050 -ORBInitialHost localhost
// java StartClient -ORBInitialPort 1050 -ORBInitialHost localhost

// StartServer.java

import Calculator.Calc;
import Calculator.CalcHelper;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class StartServer {

    public static void main(String args[]) {
        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // create servant and register it with the ORB
            CalcObject calcObj = new CalcObject();
            calcObj.setORB(orb);

            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(calcObj);
            Calc href = CalcHelper.narrow(ref);

            // get the root naming context
            // NameService invokes the name service
            org.omg.CORBA.Object nsRef = orb.resolve_initial_references("NameService");

            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(nsRef);

            // bind the Object Reference in Naming
            NameComponent path[] = ncRef.to_name("Calculator");
            ncRef.rebind(path, href);

            System.out.println("CalculatorServer is listening...");

            // wait for invocations from clients
            orb.run();
            System.out.println("I am out");
        } catch (Exception e) {
            System.err.println("Server Error: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}