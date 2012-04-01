package editor;
import java.awt.Color;
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

import sprite.Enemy;
import sprite.GameSprite;
import sprite.Platform;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


public class LevelEditor {
    Gson gson;

    LevelEditor()
    {
        gson = new Gson();
    }

    public List<GameSprite> readFile(String fileName)
    {
        Scanner scanner;
        try
        {
            scanner = new Scanner(new File(fileName));
            String wholeFile = scanner.useDelimiter("\\A").next();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(wholeFile).getAsJsonArray();
            List<Enemy> enemyList = new ArrayList<Enemy>();
            List<Platform> platformList = new ArrayList<Platform>();
    
            for (int i = 0; i < array.size(); i++)
            {
                GameSprite g = gson.fromJson(array.get(i), GameSprite.class);
    
                if (g.getType().equals("enemy"))
                {
                    enemyList.add(gson.fromJson(array.get(i), Enemy.class));
                }
                if (g.getType().equals("platform"))
                {
                    platformList.add(gson.fromJson(array.get(i), Platform.class));
                }
            }
            List<GameSprite> spriteList = new ArrayList<GameSprite>();
            spriteList.addAll(enemyList);
            spriteList.addAll(platformList);
            BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
            for(GameSprite s: spriteList)
            {
                  System.out.println(s.getImageName());
                    
                  s.setImage(loader.getImage(s.getImageName()));
            }
            return spriteList;
        } catch (FileNotFoundException e)
        {

            e.printStackTrace();
        }
        return null;
    }

    public void writeFile(String fileName, ArrayList<GameSprite> list)
    {

        String jsonString = gson.toJson(list);
        FileWriter fileOut;
        try
        {
            fileOut = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fileOut);
            out.write(jsonString);
            out.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        LevelEditor lv = new LevelEditor();
        Gson gson = new Gson();
        BaseLoader loader = new BaseLoader(new BaseIO(lv.getClass()), Color.PINK);
        ArrayList<GameSprite> collection = new ArrayList<GameSprite>();
        collection.add(new Enemy(loader.getImage("resources/happy.jpg"),5, 4, "resources/happy.jpg"));
        collection.add(new Platform(loader.getImage("resources/platform1.png"),4, 5, "resources/platform1.png"));
        lv.writeFile("sample_file.json", collection);
        
        
        List<GameSprite> s =lv.readFile("sample_file.json");
        for(GameSprite x: s)
        {
            System.out.println(x.getImageName());
        }
        

    }

}
