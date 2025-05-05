// java -version // "1.8.0_202"
// java -version // 1.8.0_202
// idlj -version // IDL-to-Java compiler (portable), version "3.2"
// idlj -fall Calc.idl
// javac -cp "C:\Program Files\Java\jdk1.8.0_202\jre\lib\rt.jar" Calculator/*.java *.java
// tnameserv -ORBInitialPort 1050
// java StartServer -ORBInitialPort 1050 -ORBInitialHost localhost
// java StartClient -ORBInitialPort 1050 -ORBInitialHost localhost

// CalcObject.java

import org.omg.CORBA.ORB;

public class CalcObject extends Calculator.CalcPOA {
    private ORB orb;

    public void setORB(ORB orb) {
        this.orb = orb;
    }

    /**
     * Calculate
     * 
     * @param type the type of the operation, 1 -> +, 2 -> -, 3 -> *, 4 -> /
     * @param a    first number
     * @param b    second number
     * @return calculation result
     */
    @Override
    public int calculate(int type, int a, int b) {
        long result;

        if (type == 1) {
            result = (long) a + b;
        } else if (type == 2) {
            result = (long) a - b;
        } else if (type == 3) {
            result = (long) a * b;
        } else {
            result = (long) a / b;
        }

        if (result >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) result;
        }
    }

    @Override
    public void exit() {
        orb.shutdown(false);
    }
}