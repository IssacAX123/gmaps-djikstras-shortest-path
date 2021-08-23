package Algorithm;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ConvertToArray {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public ConvertToArray(String filepath){
        Mat img = Imgcodecs.imread(filepath);
        System.out.println(img);
    }
}
