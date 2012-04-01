package editor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.net.URI;
import java.nio.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import buttons.Buttons;
import buttons.OpenButton;
import buttons.SaveButton;

import com.golden.gamedev.Game;
import com.golden.gamedev.gui.*;
import com.golden.gamedev.gui.toolkit.*;
import com.golden.gamedev.object.Sprite;
import sprite.Enemy;
import sprite.GameSprite;
import sprite.Platform;


public class SetGame extends Game
{
    private FrameWork framework;
    private static ArrayList<GameSprite> allSprites;
    private ArrayList<Buttons> allButtons;
    private static final int MENU_START = 800;

    private TButton openButton;
    private LevelEditor myEditor;

    static
    {}


    @Override
    public void initResources ()
    {
        myEditor = new LevelEditor();
        allButtons = new ArrayList<Buttons>();

        allSprites = new ArrayList<GameSprite>();

        framework = new FrameWork(bsInput, 1000, 600);
        framework.getTheme()
                 .getUIRenderer("Label")
                 .put("Background Border Color", Color.BLACK);
        TLabel label = new TLabel("Label test", 100, 100, 100, 100);

        TPanel infoBox = new TPanel(MENU_START, 0, 200, 600);
        infoBox.UIResource().put("Background Color", Color.LIGHT_GRAY);
        TLabel l = new TLabel("Menu", 2, 0, 196, 40);
        l.UIResource().put("Background Border Color", Color.LIGHT_GRAY);
        l.UIResource().put("Text Horizontal Alignment Integer",
                           UIConstants.CENTER);

        TLabel l2 = new TLabel("Enemies", 2, 80, 196, 40);
        l2.UIResource().put("Text Horizontal Alignment Integer",
                            UIConstants.CENTER);
        l2.UIResource().put("Background Border Color", Color.LIGHT_GRAY);

        infoBox.add(l);
        infoBox.add(l2);

        Buttons button =
            new Buttons("Happy",
                        10,
                        140,
                        60,
                        40,
                        getImage("resources/happy.jpg"),
                        "resources/happy.jpg",
                        "enemy");

        Buttons bowserbutton =
            new Buttons("Bowser",
                        100,
                        140,
                        60,
                        40,
                        getImage("resources/Bowser.jpg"),
                        "resources/Bowser.jpg",
                        "enemy");

        TLabel l3 = new TLabel("Platforms", 2, 200, 196, 40);
        l3.UIResource().put("Text Horizontal Alignment Integer",
                            UIConstants.CENTER);
        l3.UIResource().put("Background Border Color", Color.LIGHT_GRAY);

        Buttons platformbutton1 =
            new Buttons("Platform1",
                        10,
                        240,
                        60,
                        40,
                        getImage("resources/platform1.png"),
                        "resources/platform1.png",
                        "platform");
        Buttons platformbutton2 =
            new Buttons("Platform2",
                        100,
                        240,
                        60,
                        40,
                        getImage("resources/platform2.png"),
                        "resources/platform2.png",
                        "platform");

        TLabel l4 = new TLabel("File", 2, 300, 196, 40);
        l4.UIResource().put("Text Horizontal Alignment Integer",
                            UIConstants.CENTER);
        l4.UIResource().put("Background Border Color", Color.LIGHT_GRAY);

        TButton openButton = new OpenButton("Open", 10, 340, 60, 40, this);

        SaveButton saveButton = new SaveButton("Save", 100, 340, 60, 40, this);

        infoBox.add(l3);
        infoBox.add(l4);
        infoBox.add(button);
        infoBox.add(bowserbutton);
        infoBox.add(platformbutton1);
        infoBox.add(platformbutton2);
        infoBox.add(openButton);
        infoBox.add(saveButton);

        allButtons.add(button);
        allButtons.add(bowserbutton);
        allButtons.add(platformbutton1);
        allButtons.add(platformbutton2);
        framework.add(infoBox);

    }


    @Override
    public void render (Graphics2D pen)
    {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        framework.render(pen);
        for (Sprite s : allSprites)
        {
            s.render(pen);
        }

    }


    @Override
    public void update (long elapsedTime)
    {
        framework.update();
        if (click())
        {
            for (Buttons button : allButtons)
            {

                if (button.getClicked())
                {
                    GameSprite s = null;
                    if (button.getType().equals("platform"))
                    {
                        s =
                            new Platform(button.getImage(),
                                         getMouseX(),
                                         getMouseY() -
                                                 button.getImage().getHeight(),
                                         button.getImageName());
                    }
                    if (button.getType().equals("enemy"))
                    {
                        s =
                            new Enemy(button.getImage(),
                                      getMouseX(),
                                      getMouseY() -
                                              button.getImage().getHeight(),
                                      button.getImageName());
                    }

                    if (checkInterference(s)) allSprites.add(s);
                }
            }

        }

    }


    public boolean checkInterference (Sprite s)
    {
        boolean t = true;
        for (Sprite sprite : allSprites)
        {
            //System.out.println("checking");
            if ((s.getX() + s.getWidth() > sprite.getX()) &&
                (s.getX() < sprite.getX() + sprite.getWidth()))
            {
                if ((s.getY() + s.getHeight() > sprite.getY()) &&
                    (s.getY() < sprite.getY() + sprite.getHeight()))
                {
                    t = false;
                }
            }
        }
        if (getMouseX() + s.getWidth() > MENU_START) t = false;
        return t;
    }


    public void openFile ()
    {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            allSprites.clear();
            allSprites.addAll(myEditor.readFile(file.getAbsolutePath()));

        }
    }


    public void saveFile ()
    {
        String selectedValue =
            JOptionPane.showInputDialog("Where would you like to save the level?");
        myEditor.writeFile(selectedValue, allSprites);
    }

}
