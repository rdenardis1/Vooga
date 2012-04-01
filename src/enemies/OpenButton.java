package enemies;

import java.io.File;
import javax.swing.JFileChooser;
import com.golden.gamedev.gui.TButton;
import editor.SetGame;


public class OpenButton extends TButton
{

    SetGame myGame;


    public OpenButton (String name,
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
        myGame.openFile();

    }

}
