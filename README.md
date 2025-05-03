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
1. javac -target 1.8 -source 1.8 *.java
2. java Bully
3. java Ring

## Bully
PS G:\DS\Ass6> java Bully
5 active process are:
Process up  = p1 p2 p3 p4 p5
Process 5 is coordinator
.........
1) Up a process.
2) Down a process
3) Send a message
4) Exit
2
Bring down any process.
5
.........
1) Up a process.
2) Down a process
3) Send a message
4) Exit
3
Which process will send message
3
Process 3 election
Election send from process 3 to process 4
Election send from process 3 to process 5
Coordinator message send from process 4 to all
.........
1) Up a process.
2) Down a process
3) Send a message
4) Exit
1
Bring proces up
5
Process 5 is co-ordinator
.........
1) Up a process.
2) Down a process
3) Send a message
4) Exit
3
Which process will send message
3
0K
.........
1) Up a process.
2) Down a process
3) Send a message
4) Exit

## Ring
PS G:\DS\Ass6> java Ring
Enter the number of process : 
4
Enter the id of process : 
3
Enter the id of process : 
7
Enter the id of process : 
2
Enter the id of process : 
6
  [0] 2  [1] 3  [2] 6  [3] 7
 process 7 select as co-ordinator

 1.election 2.quit 
1

 Enter the Process number who initialsied election :
1

Process 3 send message to 6

Process 6 send message to 2

Process 2 send message to 3

 process 6 select as co-ordinator

 1.election 2.quit
