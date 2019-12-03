//import messages.Message;
//import messages.Status;
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Cursor;
//import javafx.scene.control.*;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//
//import java.io.IOException;
//import java.net.URL;
//import java.rmi.Naming;
//import java.util.*;
//
//public class Controller implements Initializable {
//    @FXML
//    private TextField wordTextfield;
//    @FXML
//    private TextField addTextfield;
//    @FXML
//    private TextField definitionField;
//    @FXML
//    private BorderPane borderPane;
//    @FXML
//    private GridPane addingArea;
//
//    private double xOffset;
//    private double yOffset;
//    private static final int Dic_Width = 700;
//    private static final int Dic_Height = 600;
//    private static Controller instance;
//    ThreadHandler handler;
//    ServerInterface query;
//
//
//    public Controller() {
//        instance = this;
//    }
//
//    public static Controller getInstance() {
//        return instance;
//    }
//
//    public void SearchAction() throws IOException {
//        try {
//            query = (ServerInterface) Naming.lookup("rmi://locahost:1900/rmidictionary");
//        } catch (Exception e) {
//            System.out.println("Error from RMI" + e);
//        }
//        handler = new ThreadHandler();
//        addingArea.setVisible(false);
//        definitionField.setVisible(true);
//
//        String word = wordTextfield.getText().trim();
//        if (wordTextfield.getText().trim().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//
//            alert.setContentText("Please enter a word !");
//            alert.showAndWait();
//            definitionField.clear();
//        } else {
//            query.search(word);
//        }
//    }
//
//    public void AddAction() {
//        addingArea.setVisible(true);
//        addTextfield.clear();
//        definitionField.setVisible(false);
//
//
//    }
//
//    //add word to the dictionary
//    public void add() throws IOException {
//        String word = wordTextfield.getText().trim();
//        String definition = addTextfield.getText().trim();
//        List<String> def = new ArrayList<>();
//        def.add(definition);
//        if (word.isEmpty() || definition.isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            //alert.setHeaderText("ERROR");
//            alert.setContentText("Incorrect Word");
//            alert.showAndWait();
//        } else
//            handler.add(word, def);
//        }
//    }
//
//    // Add definition to the definition textField
//    public void addToDef(Message msg) {
//        if (msg.getStatus() == Status.DONT_EXIST) {
//            Platform.runLater(() -> {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                // alert.setHeaderText("ERROR");
//                alert.setContentText("Word \'" + msg.getMsg() + "\' doesn't exist.");
//                alert.showAndWait();
//                definitionField.clear();
//            });
//        } else {
//            String definition = String.join(", ", msg.getDef());
//            definitionField.setText(definition);
//        }
//    }
//
//    // Added new word successful
//    public void addWord(Message msg) {
//        if (msg.getStatus() == Status.EXIST) {
//            Platform.runLater(() -> {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                //alert.setHeaderText("ERROR");
//                alert.setContentText("Word \'" + msg.getMsg() + "\' already exist, adding failure...");
//                alert.showAndWait();
//                addTextfield.clear();
//            });
//        } else {
//            Platform.runLater(() -> {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Success");
//                //alert.setHeaderText("SUCCESS!");
//                alert.setContentText("Adding word \'" + msg.getMsg() + "\' success.");
//                alert.showAndWait();
//                wordTextfield.clear();
//                addTextfield.clear();
//            });
//        }
//    }
//
//    public void DelAction() throws IOException {
//        String word = wordTextfield.getText().trim();
//        addingArea.setVisible(false);
//        definitionField.setVisible(true);
//        definitionField.clear();
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation");
//        //alert.setHeaderText("CONFIRMATION");
//        alert.setContentText("Do you want to delete the word \'" + word + "' ?");
//        Optional result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//            if (wordTextfield.getText().trim().isEmpty()) {
//                Alert alert1 = new Alert(Alert.AlertType.ERROR);
//                alert1.setTitle("Error");
//
//                alert1.setContentText("Error in deleting ");
//                alert1.showAndWait();
//
//            } else {
//                handler.delete(word);
//            }
//        }
//    }
//
//
//    public void deleteWord(Message msg) {
//        if (msg.getStatus() == Status.DONT_EXIST) {
//            Platform.runLater(() -> {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                // alert.setHeaderText("ERROR");
//                alert.setContentText("Word \'" + msg.getMsg() + "\' doesn't exist, deleting failed");
//                alert.showAndWait();
//                definitionField.clear();
//            });
//        } else {
//            Platform.runLater(() -> {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Success");
//                //  alert.setHeaderText("SUCCESS!");
//                alert.setContentText("Deleting word \'" + msg.getMsg() + "\' success!");
//                alert.showAndWait();
//                definitionField.clear();
//            });
//        }
//    }
//    //</editor-fold>
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        //Socket per Connection
//        ThreadHandler threadHandler = new ThreadHandler();
//        Thread x = new Thread((Runnable) threadHandler);
//        x.start();
//        borderPane.setOnMousePressed(event -> {
//            xOffset = Client.getPrimaryStage().getX() - event.getScreenX();
//            yOffset = Client.getPrimaryStage().getY() - event.getScreenY();
//            borderPane.setCursor(Cursor.CLOSED_HAND);
//        });
//
//        borderPane.setOnMouseDragged(event -> {
//            Client.getPrimaryStage().setX(event.getScreenX() + xOffset);
//            Client.getPrimaryStage().setY(event.getScreenY() + yOffset);
//
//        });
//
//        borderPane.setOnMouseReleased(event -> {
//            borderPane.setCursor(Cursor.DEFAULT);
//        });
//    }
//
//
//    // Terminates Application
//    public void closeSystem() {
//        System.out.println("Client GUI closed by the user.");
//        Platform.exit();
//        System.exit(0);
//    }
//
//    // Minimize Window
//    public void minimizeWindow() {
//        System.out.println("Client GUI minimized by the user");
//        Client.getPrimaryStage().setIconified(true);
//    }
//
//    public static void main(String args[]) {
//
//    }
//}