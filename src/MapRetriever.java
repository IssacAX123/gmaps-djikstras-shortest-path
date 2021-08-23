import APIKEY.APIKEY;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class MapRetriever {
    APIKEY apiKey = new APIKEY();
    String baseUrl = "https://maps.googleapis.com/maps/api/staticmap?";
    String center = "center=";
    String zoom = "zoom=15&";
    String size= "size=1080x1080&";
    String scale = "scale=2&";
    String styleLabels = "style=feature:all|element:labels|visibility:off&";
    String styleRoads = "style=feature:road|color:0xffffff";
    public MapRetriever() {
    }

    public void getMap(String address){
        BufferedImage image = null;
        center += address;
        String urlString = baseUrl + center + "&" + zoom + size + scale + styleLabels + styleRoads +"&key=" + apiKey;
        center = "center=";
        System.out.println(urlString);
        try{
            File out= new File("src/images/" + address +".png");
            URL url = new URL(urlString);
            image = ImageIO.read(url);
            ImageIO.write(image, "png", out);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
