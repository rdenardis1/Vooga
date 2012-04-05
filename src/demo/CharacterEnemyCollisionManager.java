package demo;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class CharacterEnemyCollisionManager extends AdvanceCollisionGroup {

    private DemoGame myGame;
    
    public CharacterEnemyCollisionManager(DemoGame game) {
        myGame = game;
    }
    
    public void collided (Sprite mario, Sprite enemy) {
        int collision = getCollisionSide();
        if (collision == BOTTOM_TOP_COLLISION) {
            enemy.setActive(false);
        }
        else {
            myGame.dies();
        }
    }
    
}
