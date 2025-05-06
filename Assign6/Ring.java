import java.io.*;
import java.util.Scanner;

public class Ring {
	static int[] sta = new int[100];  // Status of each process (1 = active, 0 = inactive)
	static int[] pro = new int[100];  // Priority of each process
	static int[] pro2 = new int[100]; // Temporary array for active processes' priorities
	static int n, ele, co;           // n = number of processes, ele = initiator, co = coordinator

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		// Input number of processes and their status and priority
		System.out.print("Enter no of processes: ");
		n = sc.nextInt();
		for (int i = 1; i <= n; i++) {
			System.out.println("\n--> PROCESS " + i);
			System.out.print("Status: ");
			sta[i - 1] = sc.nextInt(); // 1 = active, 0 = down
			System.out.print("Priority: ");
			pro[i - 1] = sc.nextInt();
		}

		// Display initial states
		System.out.println(" ");
		for (int i = 0; i < n; i++) System.out.print(i + 1 + " ");
		System.out.println(" : Processes");
		for (int i = 0; i < n; i++) System.out.print(sta[i] + " ");
		System.out.println(" : Status");
		for (int i = 0; i < n; i++) System.out.print(pro[i] + " ");
		System.out.println(" : Priority");

		// Display ring formation
		System.out.print("\nRing Formed: ");
		for (int i = 1; i <= n; i++) {
			System.out.print(i + "-->");
		}
		System.out.println(1);

		// Input initiator for election
		System.out.print("\nElection carried out by Process: ");
		ele = sc.nextInt();

		// Perform election
		elect(ele);
		System.out.println("---------------------------");
		System.out.println("   Final coordinator: " + co);
		System.out.println("---------------------------");
	}

	// Ring election algorithm
	static void elect(int ele) {
		int index = 0, max = -1;
		ele = ele - 1; // Convert to 0-based index

		// Collect priorities of active processes in ring order
		for (int i = ele; i < n; i++) {
			if (sta[i] == 1) {
				pro2[index++] = pro[i];
			}
		}
		for (int i = 0; i <= ele; i++) {
			if (sta[i] == 1) {
				pro2[index++] = pro[i];
			}
		}

		// Find highest priority
		for (int i = 0; i < index; i++) {
			if (pro2[i] > max) {
				max = pro2[i];
			}
		}

		// Identify coordinator based on highest priority
		for (int i = 0; i < n; i++) {
			if (pro[i] == max) {
				co = i + 1; // Store 1-based process ID
			}
		}
	}
}
