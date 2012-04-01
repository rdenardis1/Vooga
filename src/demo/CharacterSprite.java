package demo;

import java.awt.image.BufferedImage;
import com.golden.gamedev.object.AnimatedSprite;


public class CharacterSprite extends AnimatedSprite
{
    /**
     * 
     */
    private static final long serialVersionUID = -2912870477087271174L;
    private boolean hasPowerUp = false;
    private boolean isDead = false;
    private boolean nextLevel = false;
    private boolean water = false;


    public CharacterSprite (BufferedImage[] image, double a, double b)
    {
        super(image, a, b);
    }


    public void setHasPowerUp (boolean hasPowerUp)
    {
        this.hasPowerUp = hasPowerUp;
    }


    public boolean HasPowerUp ()
    {
        return hasPowerUp;
    }


    public void setDead (boolean isDead)
    {
        this.isDead = isDead;
    }


    public boolean isDead ()
    {
        return isDead;
    }


    public void setNextLevel (boolean nextLevel)
    {
        this.nextLevel = nextLevel;
    }


    public boolean isNextLevel ()
    {
        return nextLevel;
    }


    public void setWater (boolean water)
    {
        this.water = water;
    }


    public boolean isWater ()
    {
        return water;
    }

}
