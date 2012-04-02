package demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import editor.LevelEditor;
import sprite.GameSprite;


@SuppressWarnings("unused")
public class DemoGame extends Game
{

    private CollisionManager myGroundManager;
    private CollisionManager myEnemyManager;

    private Background myBackground;
    private CharacterSprite mySprite;

    private SpriteGroup mySpriteGroup;
    private PlayField myPF;

    private AdvanceSpriteGroup myGroundGroup;
    private AdvanceSpriteGroup myEnemyGroup;

    private ArrayList<GameSprite> groundList;
    private ArrayList<GameSprite> enemyList;

    boolean allowJump = true;
    int jump = 75;

    private LevelEditor myEditor;


    public void openFile ()
    {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            groundList.clear();
            enemyList.clear();
            List<GameSprite> sprites = myEditor.readFile(file.getAbsolutePath());
            for (GameSprite object: sprites) {
                if (object.getType().equals("platform")) {
                    groundList.add(object);
                }
                else if (object.getType().equals("enemy")) {
                    enemyList.add(object);
                }
            }
            
        }
    }


    @Override
    public void initResources ()
    {
        myEditor = new LevelEditor();

        groundList = new ArrayList<GameSprite>();
        enemyList = new ArrayList<GameSprite>();
        openFile();
        BufferedImage[] spriteImage = new BufferedImage[4];
        spriteImage[0] = getImage("resources/RunningChikapu1.png");
        spriteImage[1] = getImage("resources/RunningChikapu2.png");
        spriteImage[2] = getImage("resources/RunningChikapu3.png");
        spriteImage[3] = getImage("resources/RunningChikapu4.png");

        mySprite = new CharacterSprite(spriteImage, 0, 0);
        mySprite.getAnimationTimer().setDelay(300);
        mySprite.setAnimationFrame(0, 3);
        mySprite.setAnimate(true);
        mySprite.setLoopAnim(true);

        myBackground = new ImageBackground(getImage("resources/city.jpg"));
        myGroundGroup = new AdvanceSpriteGroup("ground");
        myEnemyGroup = new AdvanceSpriteGroup("enemy");

        mySpriteGroup = new SpriteGroup("character");
        mySpriteGroup.add(mySprite);
        myPF = new PlayField();
        myPF.add(mySprite);
        for (Sprite s : groundList)
        {
            myGroundGroup.add(s);
        }
        
        for (Sprite s: enemyList) {
            myEnemyGroup.add(s);
        }

        mySprite.setY(500);
        mySprite.setX(150);

        myGroundManager = new CharacterGroundCollisionManager();
        myGroundManager.setCollisionGroup(mySpriteGroup, myGroundGroup);
        
        myEnemyManager = new CharacterEnemyCollisionManager(this);
        myEnemyManager.setCollisionGroup(mySpriteGroup, myEnemyGroup);

        mySprite.setHasPowerUp(false);
        mySprite.setNextLevel(false);
        mySprite.setWater(false);
        myPF.addCollisionGroup(mySpriteGroup, myGroundGroup, myGroundManager);
        myPF.addCollisionGroup(mySpriteGroup, myEnemyGroup, myEnemyManager);
        
    }


    @Override
    public void render (Graphics2D arg0)
    {

        myBackground.render(arg0);
        mySprite.render(arg0);
        myPF.render(arg0);
        myGroundGroup.render(arg0);
        myEnemyGroup.render(arg0);

    }


    @Override
    public void update (long arg0)
    {

        mySpriteMovement();

        myGroundGroup.update(arg0);
        myEnemyGroup.update(arg0);

        mySprite.update(arg0);
        myPF.update(arg0);

    }


    public void dies () {
        myBackground =
                new ImageBackground(getImage("resources/game-over.png"));
        for (Sprite e: myEnemyGroup.getSprites()) {
            if (e != null) {
                e.setActive(false);
            }
        }
        for (Sprite g: myGroundGroup.getSprites()) {
            if (g != null) {
                g.setActive(false);
            }
        }
        if (keyDown(KeyEvent.VK_Y))
        {

            initResources();
        }
        if (keyDown(KeyEvent.VK_N))
        {
            System.exit(0);
        }
    }
    
    private void mySpriteMovement ()
    {
        if (mySprite.getY() > 800)
        {
            dies();
        }
        else
        {

            if (keyDown(KeyEvent.VK_RIGHT))
            {
                for (Sprite g : groundList)
                {
                    double x = g.getX();
                    g.setX(x - 1.6);
                }
                for (Sprite e: enemyList) {
                    double x = e.getX();
                    e.setX(x - 1.6);
                }

            }
            if (keyDown(KeyEvent.VK_LEFT))
            {

                for (Sprite g : groundList)
                {
                    double x = g.getX();
                    g.setX(x + 1.6);
                }
                for (Sprite e : enemyList)
                {
                    double x = e.getX();
                    e.setX(x + 1.6);
                }

            }
            if (keyPressed(KeyEvent.VK_UP))
            {
                if (mySprite.getVerticalSpeed() == 0)
                {
                    mySprite.setVerticalSpeed(-.1);
                    jump--;
                }

            }
            else if (jump == 75)
            {
                mySprite.setVerticalSpeed(.1);
            }

            else if (jump > 0)
            {
                jump--;
            }
            else if (jump == 0)
            {
                jump = 75;
                allowJump = true;
            }

        }
    }

}
