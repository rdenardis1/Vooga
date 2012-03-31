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

        public List<Sprite> readFile(String fileName) throws FileNotFoundException
        {
            Scanner scanner;
            scanner = new Scanner(new File(fileName));
        
            String wholeFile = scanner.useDelimiter("\\A").next();
                    
            Type collectionType = new TypeToken<ArrayList<Sprite>>(){}.getType();
            
            ArrayList<Sprite> spriteList = gson.fromJson(wholeFile, collectionType);
            return spriteList;
        }
        
        public void writeFile(String fileName)
        {
            BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
            ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
            Sprite sprite = new Sprite(loader.getImage("resources/coin.gif"), 40, 50);
            Sprite sprite2 = new Sprite(loader.getImage("resources/coin.gif"), 30, 500);
            spriteList.add(sprite);
            spriteList.add(sprite2);

        
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
        
        
}
