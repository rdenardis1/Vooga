import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;


public class GameSprite extends Sprite {
    
    private String filename;
    
    GameSprite(String f, BufferedImage image, double x, double y)
    {
        
        super(image, x, y);
        filename = f;
    }
    
    public String getFileName()
    {
        return filename;
    }

}
