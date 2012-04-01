package enemies;

import java.awt.image.BufferedImage;
import com.golden.gamedev.gui.TButton;
import editor.SetGame;


public class SaveButton extends TButton
{

    SetGame myGame;


    public SaveButton (String name,
                       int x,
                       int y,
                       int width,
                       int height,
                       SetGame s)
    {
        super(name, x, y, width, height);
        myGame = s;
    }


    public void doAction ()
    {
        myGame.saveFile();
    }

}
