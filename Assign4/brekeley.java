import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class brekeley {

    // Berkeley clock synchronization algorithm
    public static void berkeley(String mastertime, String time1, String time2, String time3) throws ParseException {
        System.out.println("---------- Berkeley Algorithm ----------");
        System.out.println("Master Computer Clock Time :  " + mastertime);
        System.out.println("1st Slave Computer Time :   " + time1);
        System.out.println("2nd Slave Computer Time :  " + time2);
        System.out.println("3rd Slave Computer Time :  " + time3);
        System.out.println("");

        // Format time as minutes and seconds
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

        // Convert all times to milliseconds
        long org_master = sdf.parse(mastertime).getTime();
        long org_slave1 = sdf.parse(time1).getTime();
        long org_slave2 = sdf.parse(time2).getTime();
        long org_slave3 = sdf.parse(time3).getTime();

        // Calculate time differences from master
        long diff_slave1 = org_slave1 - org_master;
        long diff_slave2 = org_slave2 - org_master;
        long diff_slave3 = org_slave3 - org_master;

        System.out.println("Difference between Master & Slave 1: " + diff_slave1 / 1000);
        System.out.println("Difference between Master & Slave 2: " + diff_slave2 / 1000);
        System.out.println("Difference between Master & Slave 3: " + diff_slave3 / 1000);
        System.out.println("");

        // Calculate average difference
        long avg_diff = (diff_slave1 + diff_slave2 + diff_slave3 + 0) / 4;
        System.out.println("Avg. difference: " + avg_diff / 1000);
        System.out.println("");

        // Compute adjustment values
        long adjust_master = avg_diff;
        long adjust_slave1 = avg_diff - diff_slave1;
        long adjust_slave2 = avg_diff - diff_slave2;
        long adjust_slave3 = avg_diff - diff_slave3;

        System.out.println("Adjustment for Slave 1: " + adjust_slave1 / 1000);
        System.out.println("Adjustment for Slave 2: " + adjust_slave2 / 1000);
        System.out.println("Adjustment for Slave 3: " + adjust_slave3 / 1000);
        System.out.println("");

        // Display synchronized times
        System.out.println("Synchronized time for Master: " + sdf.format(new Date(org_master + adjust_master)));
        System.out.println("Synchronized time for Slave 1: " + sdf.format(new Date(org_slave1 + adjust_slave1)));
        System.out.println("Synchronized time for Slave 2: " + sdf.format(new Date(org_slave2 + adjust_slave2)));
        System.out.println("Synchronized time for Slave 3: " + sdf.format(new Date(org_slave3 + adjust_slave3)));
    }

    public static void main(String[] args) throws ParseException {
        // Test the Berkeley algorithm with sample inputs
        // You can uncomment below to take user input instead
        /*
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter master clock time : ");
        String mastertime = sc.next();
        System.out.print("Enter slave1 clock time : ");
        String slave1 = sc.next();
        System.out.print("Enter slave2 clock time : ");
        String slave2 = sc.next();
        System.out.print("Enter slave3 clock time : ");
        String slave3 = sc.next();
        berkeley(mastertime, slave1, slave2, slave3);
        */

        // Hardcoded input for demonstration
        berkeley("3:00", "3:10", "2:50", "3:20");
    }
}
