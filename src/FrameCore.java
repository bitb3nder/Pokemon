import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author benfederline
 */
public class FrameCore extends BasicGame{
    
    /*
    private TiledMap grassMap;
    private TiledMap test;
    private Animation sprite, up, down, left, right;
    private float x = 34f, y = 34f;

    private boolean[][] blocked;
    private static final int SIZE = 34;
    
    public FrameCore() {
        super("Pokemon");
    }
    
    public static void main(String[] args){
        try
        {
            AppGameContainer app = new AppGameContainer(new FrameCore());
            app.setDisplayMode(500, 400, false);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    } 

    @Override
    public void init(GameContainer container) throws SlickException {
        
        test = new TiledMap("data/test.tmx");
        
        Image [] movementUp = {new Image("data/backhero.png"), new Image("data/backstep1.png")};
        Image [] movementDown = {new Image("data/fronthero.png"), new Image("data/frontstep1.png")};
        Image [] movementLeft = {new Image("data/lefthero.png"), new Image("data/leftstep1.png")};
        Image [] movementRight = {new Image("data/righthero.png"), new Image("data/rightstep1.png")};
        int [] duration = {300, 300};         
       
        grassMap = new TiledMap("data/homef2.tmx");

        /*
         * false variable means do not auto update the animation.
         * By setting it to false animation will update only when
         * the user presses a key.
         
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);

        // Original orientation of the sprite. It will look down.
        sprite = down;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        grassMap.render(0, 0); 
    }
*/
}