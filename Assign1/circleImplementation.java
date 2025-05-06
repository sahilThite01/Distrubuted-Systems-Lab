import java.rmi.*; // RMI classes
import java.rmi.server.*; // Server-side RMI classes

// Implements the remote interface and exports the object
public class circleImplementation extends UnicastRemoteObject implements circleInterface {
    double pi = 3.14; // Constant value of pi

    // Constructor that exports the object
    public circleImplementation() throws RemoteException {
        super();
    }

    // Returns perimeter of the circle
    public double perimeter(int r) throws RemoteException {
        return (2 * pi * r);
    }

    // Returns area of the circle
    public double area(int r) throws RemoteException {
        return (pi * r * r);
    }
}
