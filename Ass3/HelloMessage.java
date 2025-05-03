import mpi.*;

public class HelloMessage {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int myRank = MPI.COMM_WORLD.Rank();
        int numProcs = MPI.COMM_WORLD.Size();
        int tag = 100;

        String message = "Hello-Participants";
        byte[] sendBuffer = message.getBytes();
        byte[] recvBuffer = new byte[20];

        if (myRank == 0) {
            for (int i = 1; i < numProcs; i++) {
                MPI.COMM_WORLD.Recv(recvBuffer, 0, 20, MPI.BYTE, i, tag);
                String received = new String(recvBuffer).trim();
                System.out.println("Node " + i + " : " + received);
            }
        } else {
            MPI.COMM_WORLD.Send(sendBuffer, 0, 20, MPI.BYTE, 0, tag);
        }

        MPI.Finalize();
    }
}
