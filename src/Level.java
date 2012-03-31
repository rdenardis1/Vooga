
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class Level {

    List<Sprite> sg;
    
    public List<Sprite> getSprites()
    {
        return sg;
    }
    
    public void setSpriteGroup(List<Sprite> s)
    {
        sg = s;
    }
}
