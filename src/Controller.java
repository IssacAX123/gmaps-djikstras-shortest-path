import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class Controller {

    @FXML
    public Button addressSubmitBtn;
    public ImageView mapImageView;
    public TextField addressField;

    int[][] points = new int[2][2];
    MapRetriever mapRetriever = new MapRetriever();



    public void selectPoint(MouseEvent event){

    }

    public void getAddress(ActionEvent event) throws InterruptedException {
        final String file;
        final String address;
        java.io.File folder = new java.io.File(System.getProperty("user.dir") + "\\src\\images");
        String[] files = folder.list();
        assert files != null;
        if (files.length > 0){
            file = files[0];
            java.io.File needed = new java.io.File(System.getProperty("user.dir") + "\\src\\images\\" + file);
            if(needed.exists()){
                needed.delete();
                Thread.sleep(2000);
            }
        }

        String tmpAddress = addressField.getText();
        address = tmpAddress.replaceAll("\\s", "+");
        mapRetriever.getMap(address);
        java.io.File needed = new java.io.File(System.getProperty("user.dir") + "\\src\\images\\" + address+".png");
        Thread.sleep(3000);
        while( !needed.exists() ){
            Thread.sleep(3000);
            needed = new java.io.File(System.getProperty("user.dir") + "\\src\\images\\" + address+".png");
            System.out.println(needed);

        }
        System.out.println(needed.exists());
        java.io.File finalNeeded = needed;
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                System.out.println(finalNeeded.toURI().toString());
                mapImageView.setImage(new Image(finalNeeded.toURI().toString()));
                System.out.println("fin 2");
                Image img = mapImageView.getImage();
            }
        });
    }
}
