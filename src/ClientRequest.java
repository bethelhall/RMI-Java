import java.rmi.*;
import java.util.*;

public class ClientRequest {

    public static void main(String args[]) {


        // request metrics from clients come here

        String result;


        try {

            ServerInterface query = (ServerInterface)Naming.lookup("rmi://locahost:1900/rmidictionary");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
