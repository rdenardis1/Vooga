package sprite;
import java.awt.Color;
import java.awt.image.BufferedImage;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;


public class GameSprite extends Sprite {
    
    String myType;
    String myImageName;
    
    GameSprite(BufferedImage im, double x, double y, String type, String image)
    {
        
        super(im, x, y);
        BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
        setImage(im);
        myType = type;
        myImageName = image;
    }
    
    public String getType()
    {
        return myType;
    }
    
    public String getImageName()
    {
        return myImageName;
    }
    

}
