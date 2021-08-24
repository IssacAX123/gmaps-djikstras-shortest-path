package Algorithm;

import javafx.scene.paint.Color;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ConvertToArray {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    Mat img;

    public ConvertToArray(String filepath) throws IOException {
        img = Imgcodecs.imread(System.getProperty("user.dir") + "\\src\\images\\" +filepath);
    }
    public Mat getMatrix(){
        return img;
    }

}
