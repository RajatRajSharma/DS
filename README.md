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

## Ass 3 : MPI
1. Download from: http://mpj-express.org
2. Set environment variables MPJ_HOME, and add $MPJ_HOME/bin to your PATH.
3. javac -cp .:$MPJ_HOME/lib/mpj.jar SumArray.java HelloMessage.java
4. mpjrun.sh -np 4 SumArray
5. mpjrun.sh -np 4 HelloMessage

## Ass 4 : Clock Syncronization
1. javac -target 1.8 -source 1.8 ClockServer.java ClockClient.java
2. java ClockServer
3. java ClockClient

## Ass 5 : Mutual Exclusion
1. javac -target 1.8 -source 1.8 *.java
2. java TokenServer1
3. java TokenClient1

## Ass 6 : Election Algo Bully and Ring Algo
1.
