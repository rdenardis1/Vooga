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

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class LevelEditor {
    
        Gson gson;
        
        LevelEditor()
        {
            gson = new Gson();
        }

        public List<GameSprite> readFile(String fileName) throws FileNotFoundException
        {
            BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
            Scanner scanner;
            scanner = new Scanner(new File(fileName));
        
            String wholeFile = scanner.useDelimiter("\\A").next();
                    
            Type collectionType = new TypeToken<ArrayList<GameSprite>>(){}.getType();
            
            ArrayList<GameSprite> spriteList = gson.fromJson(wholeFile, collectionType);
            for(GameSprite s: spriteList)
            {
                s.setImage(loader.getImage(s.getFileName()));
            }
            return spriteList;
        }
        
        public void writeFile(String fileName, List<GameSprite> spriteList)
        {
            
            String jsonString = gson.toJson(spriteList);   
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
            BaseLoader loader = new BaseLoader(new BaseIO(lv.getClass()), Color.PINK);
            ArrayList<GameSprite> spriteList = new ArrayList<GameSprite>();
            GameSprite sprite = new GameSprite("resources/block3.png", loader.getImage("resources/block3.png"), 40, 50);
            GameSprite sprite2 = new GameSprite("resources/block3.png",loader.getImage("resources/block3.png"), 30, 500);
            spriteList.add(sprite);
            spriteList.add(sprite2);
            
            lv.writeFile("sample_file.json", spriteList);
            List<GameSprite> listOfSprites = lv.readFile("sample_file.json");
            System.out.println(listOfSprites.get(0).getHeight());
            System.out.println(listOfSprites.get(1).getHeight());
        }
        
}
