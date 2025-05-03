## Ass 1 : RMI Client-server assignment
1. javac *.java
2. start rmiregistry      ||          rmiregistry &
3. java Server
4. java Client
5. On Server give "localhost"

## Ass 2 : RMI Client-server assignment
1. java -version
2. idlj -version
3. idlj -fall Calc.idl
// this will give me the Calculator folder
4.  javac -cp "C:\Program Files\Java\jdk1.8.0_202\jre\lib\rt.jar" Calculator/*.java *.java
5. tnameserv -ORBInitialPort 1050
6. java StartServer -ORBInitialPort 1050 -ORBInitialHost localhost
7. java StartClient -ORBInitialPort 1050 -ORBInitialHost localhost
