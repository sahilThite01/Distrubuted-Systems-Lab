import java.rmi.Naming; // RMI classes
import java.util.Scanner; // For remote object lookup

public class client {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------------------");
		System.out.print("Enter radius: ");
		int r = sc.nextInt(); 

		// Lookup the remote object from RMI registry
		circleInterface c = (circleInterface) Naming.lookup("rmi://localhost:2000/circle");

		// Display results
		System.out.println("-----------------------------------------");
		System.out.println("	Perimeter of circle: " + (float) c.perimeter(r));
		System.out.println("	Area of circle: " + c.area(r));
		System.out.println("-----------------------------------------");
	}
}
