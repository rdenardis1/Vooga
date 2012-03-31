package enemies;

import java.awt.image.BufferedImage;

import com.golden.gamedev.gui.TButton;

public class PlatformButton extends Buttons{
    private boolean pressed;
    private BufferedImage myImage;

    public PlatformButton(String name, int x, int y, int width, int height, BufferedImage i) {
        super(name, x, y, width, height, i);
        myImage = i;

    }


}