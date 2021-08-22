import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

    public TextArea addressField;
    public Button addressSubmitBtn;
    public ImageView mapImageView;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Djikstras with Shortest Path");
        primaryStage.setScene(new Scene(root, 550, 655));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
