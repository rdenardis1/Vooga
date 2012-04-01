package sprite;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;


public class Platform extends GameSprite {

    public Platform(BufferedImage im, double x, double y, String image)
    {
        super(im, x, y, "platform", image);

    }
    
}
