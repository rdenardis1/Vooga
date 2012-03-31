import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;


public class Platform extends GameSprite {

    private long origX;
    private long origY;
    
    Platform(BufferedImage image, long x, long y )
    {
        super(image, x, y);
        setImmutable(true);
        origX = x;
        origY = y;
        
    }
    
    Platform(BufferedImage image, Platform base, long xDistance)
    {
        super(image,0,0);
        origY = (long) (base.getY()- getHeight());
        origX = (long) (base.getX() + xDistance);
        setLocation(origX, origY);
        setImmutable(true);
    }
    
    public void reset()
    {
        setLocation(origX, origY);
    }
    
    public void moveLeft()
    {
        moveX(-3);
    }
    
    public void moveRight()
    {
        moveX(3);
    }
}
