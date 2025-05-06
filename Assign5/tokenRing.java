import java.io.IOException;
import java.util.Scanner;

public class tokenRing {
	public static void main(String[] args) throws IOException {
		int token = 0, i, j, ch;
		Scanner sc = new Scanner(System.in);

		// Input number of processes in the ring
		System.out.print("Enter number of processes: ");
		int n = sc.nextInt();

		// Display the ring formation
		System.out.print("Ring formed: ");
		for (i = 0; i < n; i++) {
			System.out.print(i + "-->");
		}
		System.out.println("0\n");

		do {
			// Input sender, receiver, and data
			System.out.print("Enter sender no: ");
			int sender = sc.nextInt();
			System.out.print("Enter receiver no: ");
			int receiver = sc.nextInt();
			System.out.print("Enter data: ");
			int data = sc.nextInt();
			System.out.println();

			// Pass token to the sender
			System.out.println("Token is with process: " + token);
			System.out.print("Token passing to sender: ");
			for (i = token, j = token; (i % n) != sender; i++, j = (j + 1) % n) {
				System.out.print(i + "-->");
			}
			System.out.println(sender);
			token = j; // Token now with sender
			System.out.println("Token is with process: " + token);

			// Simulate data sending from sender to receiver
			System.out.println("\n--> Sender " + sender + " sending data " + data);
			for (i = sender; i != receiver; i = (i + 1) % n) {
				System.out.println("Data " + data + " is forwarded by process " + i);
			}
			System.out.println("--> Receiver " + receiver + " received data " + data);

			System.out.println("\nToken is with process: " + token);
			System.out.println("-----------------------------------------");

			// Ask user if they want to send data again
			System.out.println("Send again?\n1=YES\tOther=NO");
			ch = sc.nextInt();
			System.out.println("-----------------------------------------\n");
		} while (ch == 1);
	}
}
