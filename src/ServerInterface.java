import messages.Message;

import java.rmi.Remote;

public interface ServerInterface extends Remote {
    public void add(String msg);
    public void search(String msg);
    public void delete(String msg);

}
