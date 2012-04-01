package sprite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Game extends com.golden.gamedev.Game
{

    Gson gson;
    GameSprite mySprite;


    Game ()
    {
        gson = new Gson();
    }


    @Override
    public void initResources ()
    {
        try
        {
            List<GameSprite> listOfSprites = readFile("sample_file.json");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }


    @Override
    public void render (Graphics2D pen)
    {
        // clear last frame
        pen.setColor(Color.BLACK);
        pen.fillRect(0, 0, getWidth(), getHeight());
        // render sprites based on their current state
        mySprite.render(pen);
    }


    @Override
    public void update (long arg0)
    {
        mySprite.update(arg0);
    }


    public List<GameSprite> readFile (String fileName)
        throws FileNotFoundException
    {
        Scanner scanner;
        scanner = new Scanner(new File(fileName));

        String wholeFile = scanner.useDelimiter("\\A").next();

        Type collectionType = new TypeToken<ArrayList<GameSprite>>()
        {}.getType();

        ArrayList<GameSprite> spriteList =
            gson.fromJson(wholeFile, collectionType);
        return spriteList;
    }


    public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new Game(), new Dimension(800, 600), false);
        loader.start();
    }

}
