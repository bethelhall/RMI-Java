import messages.Message;

import java.io.ObjectInputStream;
import java.rmi.*;
import java.util.*;

public class ClientRequest {
    ServerInterface query;
    Scanner sc;


    public void manage_messages(String cmd, String word) {

        try {
            query = (ServerInterface) Naming.lookup("rmi://locahost:1900/rmidictionary");

        } catch (Exception e) {
            System.out.println("Error from message" + e);
        }

        System.out.println("add word");
        word = sc.nextLine();
        switch (cmd) {
            case "add":
                query.search(word);
                break;
            case "find":
                query.add(word);
                break;
            case "delete":
                query.delete(word);
                break;
            default:

        }
    }


    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        ClientRequest clientRequest= new ClientRequest();
        clientRequest.manage_messages("add", "new_word");




    }
}