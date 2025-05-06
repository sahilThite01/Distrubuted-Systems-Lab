import java.rmi.*; // Importing Java RMI package for remote method invocation

// Defining a remote interface named 'circleInterface'
// It extends the Remote interface, which makes the methods eligible for remote invocation
public interface circleInterface extends Remote {

    // Method to calculate the perimeter of a circle
    // This method may throw a RemoteException if a communication error occurs during the remote call
    public double perimeter(int r) throws RemoteException;

    // Method to calculate the area of a circle
    // This method may throw a RemoteException
    public double area(int r) throws RemoteException;
}
