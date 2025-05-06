import java.rmi.*; // RMI classes
import java.rmi.registry.*; // For RMI registry
import java.util.Scanner; // For user input

public class server {

    public static void main(String[] args) throws RemoteException, Exception {
        // Create RMI registry on port 2000
        Registry reg = LocateRegistry.createRegistry(2000);

        // Create and bind the remote object
        circleImplementation ci = new circleImplementation();
        reg.rebind("circle", ci);

        System.out.println("-------------------------------");
        System.out.println("      SERVER IS WAITING...");
        System.out.println("-------------------------------");

        // Wait for user input to stop the server
        Scanner sc = new Scanner(System.in);
        System.out.println("\nType 'exit' to stop the server.");
        String command = sc.nextLine();

        if (command.equalsIgnoreCase("exit")) {
            // Unbind and shut down the server
            System.out.println("\nShutting down server...");
            reg.unbind("circle");  
            System.out.println("Server unbound successfully.");
            System.out.println("-------------------------------");
            System.exit(0);
        }
    }
}
