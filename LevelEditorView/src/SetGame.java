import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;


import com.golden.gamedev.Game;
import com.golden.gamedev.gui.*;
import com.golden.gamedev.gui.toolkit.*;
import com.golden.gamedev.object.Sprite;

import enemies.Buttons;
import enemies.EnemyButton;
import enemies.HappyFace;


public class SetGame extends Game
{
    private FrameWork framework;
    private static ArrayList<Sprite> allSprites;
    private ArrayList<Buttons> allButtons;
    private static final int MENU_START=800;  


    static{
    }

    @Override
    public void initResources ()
    {
        allButtons = new ArrayList<Buttons>();

        allSprites = new ArrayList<Sprite> ();


        framework = new FrameWork(bsInput, 1000, 600);
        framework.getTheme().getUIRenderer("Label").put("Background Border Color", Color.BLACK);
        TLabel label = new TLabel("Label test", 100, 100, 100, 100);

        TPanel infoBox = new TPanel(MENU_START, 0, 200, 600);
        infoBox.UIResource().put("Background Color", Color.LIGHT_GRAY);
        TLabel l = new TLabel("Menu", 2, 0, 196, 40);
        l.UIResource().put("Background Border Color", Color.LIGHT_GRAY);
        l.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);

        TLabel l2 = new TLabel("Enemies", 2, 80, 196, 40);
        l2.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        l2.UIResource().put("Background Border Color", Color.LIGHT_GRAY);

        infoBox.add(l);
        infoBox.add(l2);

        Buttons button = new EnemyButton("Happy", 10, 140, 60, 40, getImage("resources/happy.jpg"));
        Buttons bowserbutton = new EnemyButton("Bowser", 100, 140, 60, 40, getImage("resources/Bowser.jpg"));

        TLabel l3 = new TLabel("Platforms", 2, 200, 196, 40);
        l3.UIResource().put("Text Horizontal Alignment Integer", UIConstants.CENTER);
        l3.UIResource().put("Background Border Color", Color.LIGHT_GRAY);

        
        Buttons platformbutton1 = new EnemyButton ("Platform1", 10, 240, 60, 40, getImage ("resources/platform1.png"));
        Buttons platformbutton2 = new EnemyButton ("Platform2", 100, 240, 60, 40, getImage ("resources/platform2.png"));

        
        infoBox.add(l3);
        infoBox.add(button);
        infoBox.add(bowserbutton);
        infoBox.add(platformbutton1);
        infoBox.add(platformbutton2);


        allButtons.add(button);
        allButtons.add(bowserbutton);
        allButtons.add(platformbutton1);
        allButtons.add(platformbutton2);
        framework.add(infoBox);

    }

    @Override
    public void render (Graphics2D pen)
    {
        framework.render(pen);
        for(Sprite s: allSprites)
        {
            s.render(pen);
        }

    }

    @Override
    public void update (long elapsedTime)
    {
        framework.update();
        if(click())
        {
            for(Buttons button: allButtons)
            {
                if(button.getClicked()){
                    Sprite s = new Sprite(button.getImage(), getMouseX(), getMouseY()-button.getImage().getHeight());
                    if(checkInterference(s))
                        allSprites.add(s);
                }
            }
        }
    }

    public boolean checkInterference(Sprite s)
    {
        boolean t=true;
        for(Sprite sprite: allSprites)
        {
            System.out.println("checking");
            if( (s.getX()+s.getWidth()>sprite.getX()) && (s.getX()<sprite.getX()+sprite.getWidth()) )
            {
                if( (s.getY()+s.getHeight()>sprite.getY()) && (s.getY()<sprite.getY()+sprite.getHeight()) )
                {
                    t = false;
                }
            }
        }
        if(getMouseX()+s.getWidth()>MENU_START)
            t = false;
        return t;
    }

}
