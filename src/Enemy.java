import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;


public class Enemy extends GameSprite {
    private long origX;
    private long origY;
    private long enemySpecial;

    private boolean leftFacing;
    
    Enemy(BufferedImage left, BufferedImage right, Platform base, long xDistance)
    {
        super(left,0,0);
        origY = (long) (base.getY()- getHeight());
        origX = (long) (base.getX() + xDistance);
        setLocation(origX, origY);
        setHorizontalSpeed(0);
        
        setImmutable(true);
        leftFacing = true;
        enemySpecial = 5;
    }
    Enemy(BufferedImage left, BufferedImage right, long xDistance, long yDistance)
    {
        super(left,0,0);
        origY = xDistance;
        origX = yDistance;
        setLocation(origX, origY);
        setHorizontalSpeed(0);
        
        setImmutable(true);
        leftFacing = true;
        enemySpecial = 5;
    }

    
    public long getEnemySpecial() {
        return enemySpecial;
    }
    
    public void reset()
    {
        setLocation(origX, origY);
        setHorizontalSpeed(0);
        leftFacing = true;
        
        setActive(true);
    }
    
    public void moveLeft()
    {
        moveX(-3);
    }
    
    public void moveRight()
    {
        moveX(3);
    }
    
    public void switchDirection()
    {
       
        
       
        setHorizontalSpeed(-getHorizontalSpeed());
        leftFacing= !leftFacing;
        return;

    }

    
}
