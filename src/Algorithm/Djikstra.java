package Algorithm;

import org.opencv.core.Mat;
import java.util.PriorityQueue;

public class Djikstra {
    PriorityQueue<Pixel> pq = new PriorityQueue<Pixel>();
    Pixel[][] matrix;

    public Djikstra(Mat img, int[] source, int[] sink){
        int rows = img.height();
        int cols = img.width();

        matrix = new Pixel[rows][cols];

        for(int r =0; r < rows-1; r++){
            for(int c =0; c < cols-1; c++){
                matrix[r][c] = new Pixel(c, r);
                matrix[r][c].setQueueIndex(pq.size());
                pq.add(matrix[r][c]);
            }
        }
        matrix[source[1]][source[0]].setDistance(0);

    }


}
