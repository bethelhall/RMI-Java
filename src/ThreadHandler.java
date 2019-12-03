//import messages.Message;
//import messages.MessageType;
//import javafx.application.Platform;
//import javafx.scene.control.Alert;
//import java.io.*;
//import java.util.List;
//
//public class ThreadHandler {
//    Controller controller;
//    ObjectOutputStream objectOutputStream;
//    InputStream is;
//    ObjectInputStream objectInputStream;
//    OutputStream outputStream;
//    Message message = null;
//    Integer port = 2000;
//
//    public ThreadHandler(){
//        menu();
//    }
//
//    public void menu() {
//
//        try {
//            message = (Message) objectInputStream.readObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (message != null) {
//            switch (message.getType()) {
//                case SEARCH:
//                    controller.addToDef(message);
//                    break;
//                case ADD:
//                    controller.addWord(message);
//                    break;
//                case DELETE:
//                    controller.deleteWord(message);
//                    break;
//            }
//        }
//    }
//
//    public void connectionCheck() {
//        Platform.runLater(() -> {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("CONNECTION LOST");
//            alert.setContentText("Could not connect to the server on port " + port+ ", please check the server...");
//            alert.showAndWait();
//            controller.closeSystem();
//        });
//    }
//
//    public void search(String word) throws IOException {
//        Message message = new Message();
//        message.setType(MessageType.SEARCH);
//        message.setMsg(word);
//        // Send the input string to the server by writing to the socket output stream
//        objectOutputStream.writeObject(message);
//        objectOutputStream.flush();
//        System.out.println("Searching for: " + word);
//    }
//
//    public  void add(String word, List<String> def) throws IOException {
//        Message message = new Message();
//        message.setType(MessageType.ADD);
//        message.setMsg(word);
//        String definition = String.join(", ", def);
//        message.setDef(definition);
//        objectOutputStream.writeObject(message);
//        objectOutputStream.flush();
//        System.out.println("Adding word: " + word + " with definition: " + definition);
//    }
//
//    public void delete(String word) throws IOException {
//        Message message = new Message();
//        message.setType(MessageType.DELETE);
//        message.setMsg(word);
//        // Send the input string to the server by writing to the socket output stream
//        objectOutputStream.writeObject(message);
//        objectOutputStream.flush();
//        System.out.println("Deleting word: " + word);
//    }
//
//
//
//}
//
