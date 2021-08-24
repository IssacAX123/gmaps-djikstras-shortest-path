package Algorithm;

public class Pixel implements Comparable<Pixel>{
    int x;
    int y;
    double distance;
    Integer parentX;
    Integer parentY;
    boolean processed;
    Integer queueIndex;

    public Pixel(int x, int y){
        this.x = x;
        this.y = y;
        distance = Double.POSITIVE_INFINITY;
        parentX = null;
        parentY = null;
        processed = false;
        queueIndex = null;
    }

    @Override
    public int compareTo(Pixel o) {
        if (this.distance > o.distance){
            return 1;
        }else if (this.distance < o.distance){
            return -1;
        }else{
            return 0;
        }
    }

    public void setQueueIndex(int index){
        this.queueIndex = index;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }
}
