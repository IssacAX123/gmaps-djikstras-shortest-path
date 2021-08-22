import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    public TextArea addressField;
    public Button addressSubmitBtn;
    public ImageView mapImageView;

    int[][] points = new int[2][2];

    public void selectPoint(MouseEvent event){

    }

    public void getAddress(ActionEvent event){

    }
}
