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

// File Name: DistributedSum.java
import mpi.*; // Import MPJ Express MPI package

public class DistributedSum {
    public static void main(String[] args) {
        try {
            MPI.Init(args); // Initialize MPI environment

            int rank = MPI.COMM_WORLD.Rank(); // Get process ID (rank)
            int size = MPI.COMM_WORLD.Size(); // Get total number of processes

            int[] array = {1, 2, 3, 4, 5, 6, 7, 8}; // Input array
            int n = array.length;

            // Ensure array can be evenly divided among processes
            if (n % size != 0) {
                if (rank == 0)
                    System.out.println("Array size must be divisible by number of processors.");
                MPI.Finalize();
                return;
            }

            int localN = n / size; // Elements per process
            int[] localArray = new int[localN]; // Buffer for scattered data
            int localSum = 0; // Sum computed by each process
            int[] globalSum = new int[1]; // Final sum (used by root process)

            // Distribute parts of array to all processes
            MPI.COMM_WORLD.Scatter(array, 0, localN, MPI.INT, localArray, 0, localN, MPI.INT, 0);

            // Calculate local sum
            for (int i = 0; i < localN; i++) {
                localSum += localArray[i];
            }

            System.out.println("Processor " + rank + " calculated local sum = " + localSum);

            // Reduce all local sums to global sum at root (rank 0)
            MPI.COMM_WORLD.Reduce(new int[]{localSum}, 0, globalSum, 0, 1, MPI.INT, MPI.SUM, 0);

            // Root process prints total sum
            if (rank == 0) {
                System.out.println("Total Sum = " + globalSum[0]);
            }

            MPI.Finalize(); // Finalize MPI environment
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
