/* 1.Download & extract jar file in home directory from below link

https://sourceforge.net/projects/mpjexpress/

2. Open terminal in home directory & type below command 

sudo gedit ~/.bashrc

3. Add below 2 lines in opened bash rc 

export MPJ_HOME="/home/pvg/mpj-v0_44"

export PATH=$MPJ_HOME/bin:$PATH


4. Compile and run assignment 3 using below commands 

javac -cp "/home/pvg/mpj-v0_44/lib/mpj.jar" DistributedSum.java 

mpjrun.sh -np 6 DistributedSum 

*/

// -------------------------------------------------------------------------------------------------------------------------------

// File Name DistributedSum.java
import mpi.*;

public class DistributedSum {
    public static void main(String[] args) {
        try {
            MPI.Init(args);

            int rank = MPI.COMM_WORLD.Rank();
            int size = MPI.COMM_WORLD.Size();

            int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
            int n = array.length;

            if (n % size != 0) {
                if (rank == 0)
                    System.out.println("Array size must be divisible by number of processors.");
                MPI.Finalize();
                return;
            }

            int localN = n / size;
            int[] localArray = new int[localN];
            int localSum = 0;
            int[] globalSum = new int[1];

            MPI.COMM_WORLD.Scatter(array, 0, localN, MPI.INT, localArray, 0, localN, MPI.INT, 0);

            for (int i = 0; i < localN; i++) {
                localSum += localArray[i];
            }

            System.out.println("Processor " + rank + " calculated local sum = " + localSum);

            MPI.COMM_WORLD.Reduce(new int[]{localSum}, 0, globalSum, 0, 1, MPI.INT, MPI.SUM, 0);

            if (rank == 0) {
                System.out.println("Total Sum = " + globalSum[0]);
            }

            MPI.Finalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
