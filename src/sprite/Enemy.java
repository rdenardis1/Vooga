package sprite;

import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;


public class Enemy extends GameSprite
{

    public Enemy (BufferedImage im, double x, double y, String image)
    {
        super(im, x, y, "enemy", image);

    }

}
