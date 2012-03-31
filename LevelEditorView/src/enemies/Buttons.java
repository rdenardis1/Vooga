package enemies;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.golden.gamedev.gui.TButton;

public  abstract class Buttons extends TButton{
    private boolean pressed;
    private BufferedImage myImage;
    
    public Buttons(String name, int x, int y, int width, int height, BufferedImage i) {
        super(name, x, y, width, height);
        myImage = i;
    }
    public boolean getClicked()
    {
        boolean t = pressed;
        pressed = false;
        return t;
    }
    public BufferedImage getImage()
    {
        return myImage;
    }
    public void doAction() {
        pressed=true;
    }


}
