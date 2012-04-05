package demo;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;


public class CharacterGroundCollisionManager extends AdvanceCollisionGroup
{

    public void collided (Sprite mario, Sprite stuff)
    {
        int collision = getCollisionSide();
        if (collision == BOTTOM_TOP_COLLISION)
        {
            mario.setVerticalSpeed(0);
            mario.setY(stuff.getY() - 50);

        }
        if (collision == TOP_BOTTOM_COLLISION)
        {
            mario.setVerticalSpeed(.1);
        }

    }

}
