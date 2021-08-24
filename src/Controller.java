import Algorithm.Djikstra;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Algorithm.ConvertToArray;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import org.opencv.core.Mat;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML
    public Button addressSubmitBtn;
    public ImageView mapImageView;
    public TextField addressField;
    public AnchorPane pane;

    int[][] points = new int[2][2];
    ArrayList<Circle> drawnPoints = new ArrayList<Circle>();

    MapRetriever mapRetriever = new MapRetriever();
    String fileName;



    public void selectPoint(MouseEvent event) throws IOException {
        System.out.println(Arrays.deepEquals(points, new int[][]{{0, 0}, {0, 0}}));
        if(Arrays.deepEquals(points, new int[][]{{0, 0}, {0, 0}})){
            points[0][0] = (int)event.getX()+5;
            points[0][1] = (int)event.getY()+100;
            drawPoints();
        }else if(Arrays.equals(points[1], new int[]{0, 0})){
            points[1][0] = (int)event.getX()+5;
            points[1][1] = (int)event.getY()+100;
            drawPoints();
            calculate();
        }
    }

    public void drawPoints(){
        pane.getChildren().removeAll(drawnPoints);
        for (int[] point: points){
            drawPoint(point);
        }
    }

    public void drawPoint(int[] point){
        if (!Arrays.equals(point, new int[]{0, 0})) {
            Circle circle = new Circle(point[0], point[1], 4.0f);
            circle.setFill(Color.RED);
            drawnPoints.add(circle);
            pane.getChildren().add(circle);
        }
    }

    public void getAddress(ActionEvent event) throws InterruptedException {
        pane.getChildren().removeAll(drawnPoints);
        points = new int[][]{{0, 0}, {0, 0}};
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
                Thread.sleep(1000);
            }
        }

        String tmpAddress = addressField.getText();
        address = tmpAddress.replaceAll("\\s", "+");
        fileName = address+".png";
        mapRetriever.getMap(address);
        java.io.File needed = new java.io.File(System.getProperty("user.dir") + "\\src\\images\\" + address+".png");
        Thread.sleep(2000);
        while( !needed.exists() ){
            Thread.sleep(2000);
            needed = new java.io.File(System.getProperty("user.dir") + "\\src\\images\\" + address+".png");
        }
        java.io.File finalNeeded = needed;
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                System.out.println(finalNeeded.toURI().toString());
                mapImageView.setImage(new Image(finalNeeded.toURI().toString()));
                Image img = mapImageView.getImage();
            }
        });
    }

    public void calculate() throws IOException {
       ConvertToArray img_grid = new ConvertToArray(fileName);
       Mat img = img_grid.getMatrix();
        Djikstra solution = new Djikstra(img, points[0], points[1]);
        ArrayList<int[]> path = solution.solve();
        System.out.println(path);
    }
}
