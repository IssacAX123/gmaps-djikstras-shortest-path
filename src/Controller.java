import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    public Button addressSubmitBtn;
    public ImageView mapImageView;
    public TextField addressField;

    int[][] points = new int[2][2];

    public void selectPoint(MouseEvent event){

    }

    public void getAddress(ActionEvent event){

    }
}
