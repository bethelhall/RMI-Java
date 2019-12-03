import messages.Message;

import java.rmi.Remote;

public interface ServerInterface extends Remote {
    public void add(Message msg) throws Exception;
    public void search(Message msg) throws Exception;
    public void delete(Message msg);

}
