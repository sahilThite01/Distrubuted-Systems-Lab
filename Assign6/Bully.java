import java.io.*;
import java.util.Scanner;

// BULLY ALGORITHM for leader election
public class Bully {
    static int[] pro = new int[100]; // Process priorities
    static int[] sta = new int[100]; // Process status (1 = active, 0 = inactive)
    static int ele, n, co;           // ele = initiator, n = number of processes, co = coordinator

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Input number of processes and their status and priority
        System.out.print("Enter no of processes: ");
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.println("\n--> PROCESS " + i);
            System.out.print("Status: ");
            sta[i - 1] = sc.nextInt(); // 1 = active, 0 = inactive
            System.out.print("Priority: ");
            pro[i - 1] = sc.nextInt();
        }

        // Display initial states of processes
        System.out.println(" ");
        for (int i = 0; i < n; i++) System.out.print(i + 1 + " ");
        System.out.println(" : Processes");
        for (int i = 0; i < n; i++) System.out.print(sta[i] + " ");
        System.out.println(" : Status");
        for (int i = 0; i < n; i++) System.out.print(pro[i] + " ");
        System.out.println(" : Priority");

        // Input initiator for election
        System.out.print("\nElection carried out by Process: ");
        ele = sc.nextInt();

        // Perform election
        elect(ele);
        System.out.println("---------------------------");
        System.out.println("   Final coordinator: " + co);
        System.out.println("---------------------------");
    }

    // Bully algorithm for leader election
    static void elect(int ele) {
        ele = ele - 1;  // Convert to 0-based index
        co = ele + 1;   // Set initial coordinator as the initiator

        // Send election messages to higher priority processes
        for (int i = 0; i < n; i++) {
            if (pro[i] > pro[ele]) { // Check if process i has higher priority
                System.out.println("Election message sent by process " + (ele + 1) + " to process " + (i + 1));
                if (sta[i] ==
