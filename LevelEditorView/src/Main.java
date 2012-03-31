import java.awt.Dimension;
import com.golden.gamedev.GameLoader;


public class Main
{
    public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new SetGame(), new Dimension(1000, 600), false);
        loader.start();
    }
}
