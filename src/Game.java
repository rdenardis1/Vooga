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
import com.golden.gamedev.object.Sprite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Game extends com.golden.gamedev.Game {
    
    Gson gson;
    GameSprite mySprite;
    
    Game()
    {
        gson = new Gson();
    }

    @Override
    public void initResources()
    {
        LevelEditor lv = new LevelEditor();
        BaseLoader loader = new BaseLoader(new BaseIO(lv.getClass()), Color.PINK);
        ArrayList<GameSprite> spriteList = new ArrayList<GameSprite>();
        GameSprite sprite = new GameSprite("resources/block3.png", loader.getImage("resources/block3.png"), 0, 0);
        GameSprite sprite2 = new GameSprite("resources/block3.png",loader.getImage("resources/block3.png"), 30, 500);
        spriteList.add(sprite);
        spriteList.add(sprite2);
        
        writeFile("sample_file.json", spriteList);
        try
        {
            List<GameSprite> listOfSprites = readFile("sample_file.json");
            mySprite = listOfSprites.get(0);
            mySprite.setImage(getImage(mySprite.getFileName()));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        
        
        
    }

    @Override
    public void render(Graphics2D pen)
    {
     // clear last frame
        pen.setColor(Color.BLACK);
        pen.fillRect(0, 0, getWidth(), getHeight());
        // render sprites based on their current state
        mySprite.render(pen);
    }

    @Override
    public void update(long arg0)
    {
        mySprite.update(arg0);
    }
    
    

    public List<GameSprite> readFile(String fileName) throws FileNotFoundException
    {
        Scanner scanner;
        scanner = new Scanner(new File(fileName));
    
        String wholeFile = scanner.useDelimiter("\\A").next();
                
        Type collectionType = new TypeToken<ArrayList<GameSprite>>(){}.getType();
        
        ArrayList<GameSprite> spriteList = gson.fromJson(wholeFile, collectionType);
        return spriteList;
    }
    
    public void writeFile(String fileName, List<GameSprite> spriteList)
    {
        
        String jsonString = gson.toJson(spriteList);   
        FileWriter fileOut;
        try
        {
            fileOut = new FileWriter("sample_file.json");
            BufferedWriter out = new BufferedWriter(fileOut);
            out.write(jsonString);
            out.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new Game(), new Dimension(800, 600), false);
        loader.start();
    }


}
