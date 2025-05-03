import mpi.*;

public class SumArray {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int N = 100;
        int[] array = null;
        int localN;

        if (rank == 0) {
            array = new int[N];
            for (int i = 0; i < N; i++) {
                array[i] = i + 1;
            }
        }

        int[] nBuffer = new int[1];
        if (rank == 0) {
            nBuffer[0] = N;
        }

        MPI.COMM_WORLD.Bcast(nBuffer, 0, 1, MPI.INT, 0);
        N = nBuffer[0];

        if (N % size != 0) {
            if (rank == 0) {
                System.out.println("Error: The number of elements is not divisible by number of processes.");
            }
            MPI.Finalize();
            return;
        }

        localN = N / size;
        int[] localArray = new int[localN];

        MPI.COMM_WORLD.Scatter(array, 0, localN, MPI.INT, localArray, 0, localN, MPI.INT, 0);

        int partialSum = 0;
        for (int i = 0; i < localN; i++) {
            partialSum += localArray[i];
        }

        int[] gatheredSums = null;
        if (rank == 0) {
            gatheredSums = new int[size];
        }

        int[] partialSumBuffer = new int[]{partialSum};
        MPI.COMM_WORLD.Gather(partialSumBuffer, 0, 1, MPI.INT, gatheredSums, 0, 1, MPI.INT, 0);

        if (rank == 0) {
            int totalSum = 0;
            System.out.println("Intermediate sums from processors:");
            for (int i = 0; i < size; i++) {
                System.out.println("Processor " + i + ": " + gatheredSums[i]);
                totalSum += gatheredSums[i];
            }
            System.out.println("Total Sum: " + totalSum);
        }

        MPI.Finalize();
    }
}
