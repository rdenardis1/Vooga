package enemies;

import java.awt.image.BufferedImage;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;


public class HappyFace
{
    private Sprite myself;


    public HappyFace (Game game)
    {
        new Sprite(game.getImage("resources/happy.jpg"));
    }


    public BufferedImage getImage ()
    {
        return myself.getImage();
    }
}
