package enemies;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.golden.gamedev.gui.TButton;

public  class Buttons extends TButton{
    private boolean pressed;
    private BufferedImage myImage;
    private String imageName;
    private String myType;
    public Buttons(String name, int x, int y, int width, int height, BufferedImage i, String imName, String type) {
        super(name, x, y, width, height);
        myImage = i;
        imageName = imName;
        myType = type;
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
    
    public String getType()
    {
        return myType;
    }
    
    public String getImageName()
    {
        return imageName;
    }


}
