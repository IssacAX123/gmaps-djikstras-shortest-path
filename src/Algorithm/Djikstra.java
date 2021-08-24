package Algorithm;

import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Djikstra {
    PriorityQueue<Pixel> pq = new PriorityQueue<Pixel>();
    Pixel[][] matrix;
    Mat img;
    int[] sink;
    int[] source;

    public Djikstra(Mat img, int[] source, int[] sink){
        this.img = img;
        this.sink = sink;
        this.source = source;
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

    public double getDistance(int[] unproccedNodesCoords, int[] neighboursCoords){
        return 0.1 +
                Math.pow(img.get(neighboursCoords)[0]-img.get(unproccedNodesCoords)[0], 2) +
                Math.pow(img.get(neighboursCoords)[1]-img.get(unproccedNodesCoords)[1], 2) +
                Math.pow(img.get(neighboursCoords)[2]-img.get(unproccedNodesCoords)[2], 2);
    }

    public Pixel[] getNeighbours(int r, int c){
        int[] shape = {matrix[0].length, matrix.length};
        Pixel[] neighbours = new Pixel[4];
        if (r > 0 && !matrix[r-1][c].processed){
            neighbours[0] = matrix[r-1][c];
        }
        if (r < shape[0] - 1 && !matrix[r+1][c].processed){
            neighbours[1] = matrix[r+1][c];
        }
        if (c > 0 && !matrix[r][c-1].processed){
            neighbours[2] = matrix[r][c-1];
        }
        if (c < shape[1] && !matrix[r][c+1].processed){
            neighbours[3] = matrix[r][c+1];
        }
        return neighbours;
    }

    public ArrayList<int[]> solve(){
        while (pq.size() > 0){
            Pixel unproccedNodes = pq.poll();
            assert pq.peek() != null;
            pq.peek().setQueueIndex(0);
            unproccedNodes.processed = true;

           Pixel[] neighbours = getNeighbours(unproccedNodes.y, unproccedNodes.x);
           for (Pixel neighbour: neighbours){
               if (neighbour != null){
                   double dist = getDistance(new int[]{unproccedNodes.y, unproccedNodes.x}, new int[]{neighbour.y, neighbour.x});
                   if (unproccedNodes.distance + dist < neighbour.distance){
                       neighbour.distance = unproccedNodes.distance + dist;
                       neighbour.parentX = unproccedNodes.x;
                       neighbour.parentY = unproccedNodes.y;
                       pq.remove(neighbour);
                   }
               }
           }
        }
        ArrayList<int[]> path = new ArrayList<int[]>();
        Pixel iterator = matrix[sink[1]][sink[0]];
        path.add(new int[]{sink[1], sink[0]});
        while (iterator.y != sink[0] || iterator.x != sink[1]){
            path.add(new int[]{iterator.x, iterator.y});
            iterator = matrix[iterator.parentY][iterator.parentX];
        }
        path.add(new int[]{source[1], source[0]});
        return path;
    }

}
