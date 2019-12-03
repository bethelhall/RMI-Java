

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Vector;

public class Server {


    public static void main(String args[]){
        String filePath;
        try
        {
            // Create an object of the interface
            // implementation class
            ServerInterface searchObj = new SearchQuery();



            LocateRegistry.createRegistry(1900);

            // Binds the remote object by the name
            // geeksforgeeks
            Naming.rebind("rmi://localhost:1900"+
                    "/rmidictionary",searchObj);
        }
        catch(Exception ae)
        {
            System.out.println(ae);
        }
    }


    
}
