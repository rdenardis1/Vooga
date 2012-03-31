import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;


public class GameSprite extends Sprite {
    
    private String filename;
    
    
    
    public GameSprite(BufferedImage image, double x, double y)
    {
        
        super(image, x, y);
        
    }
    
    public String getFileName()
    {
        return filename;
    }

}
