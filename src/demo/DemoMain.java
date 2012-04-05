package demo;

import java.awt.Dimension;
import com.golden.gamedev.GameLoader;
import demo.DemoGame;


public class DemoMain
{
    public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new DemoGame(), new Dimension(800, 600), false);
        loader.start();
    }
}
