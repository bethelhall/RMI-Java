import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Client extends Application {


    private static Stage primaryStageObj;
    protected static int PORT = 8081;
    protected static String HOST = "127.0.0.1";

    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                HOST = args[0];
                PORT = Integer.parseInt(args[1]);
            } catch (Exception e){
                System.out.println("InCorrect format ");
            }
        } else {
            System.out.println("Didn't initialize the port address : " +HOST+ ":" + PORT);
        }
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStageObj;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStageObj = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ui.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }
}

