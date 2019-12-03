import messages.Message;
import messages.MessageType;
import messages.Status;

import java.io.File;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SearchQuery extends UnicastRemoteObject implements ServerInterface {

    private Dictionary dictionary;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private OutputStream os;
    private InputStream is;

    String filePath = System.getProperty("user.dir+") + File.separator + "dictionary.json";

    protected SearchQuery() throws RemoteException {
    }

    @Override
    public void add(Message msg) throws Exception {
        dictionary.readJSONFile(filePath);
        String word = msg.getMsg();
        String definition = msg.getDef();
        if (dictionary.hasWord(msg.getMsg())) {
            msg.setStatus(Status.EXIST);
            msg.setType(MessageType.ADD);
            write(msg);
        } else {
            msg.setType(MessageType.ADD);
            List<String> def = new ArrayList<>();
            def.add(definition);
            dictionary.addWord(word, def);
            write(msg);
        }
    }


    @Override
    public void search(Message msg) throws Exception {
        List<String> def;
        dictionary.readJSONFile(filePath);
        if (dictionary.hasWord(msg.getMsg())) {
            msg.setType(MessageType.SEARCH);
            def = dictionary.getDefinitions(msg.getMsg());
            String definition = String.join(", ", def);
            msg.setDef(definition);
            write(msg);
        } else {
            msg.setType(MessageType.SEARCH);
            msg.setStatus(Status.DONT_EXIST);
            write(msg);
        }

    }

    public void write(Message msg) {
        try {

            output.writeObject(msg);
            output.flush();
            output.reset();
            System.out.println("Response to client");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Message msg) {
        dictionary.readJSONFile(filePath);
        String word = msg.getMsg();
        if (dictionary.hasWord(msg.getMsg())) {
            msg.setType(MessageType.DELETE);
            dictionary.removeWord(word);
            write(msg);
        } else {
            msg.setType(MessageType.DELETE);
            msg.setStatus(Status.DONT_EXIST);
            write(msg);
        }
    }
}


