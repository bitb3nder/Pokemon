import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

/**
 *
 * @author benfederline
 */
public class Test extends BasicGame {
    public static AppGameContainer container;
    public BlockMap map;
    
    public String spritemap = "data/tiles/PewterCity.tmx";

    public static final int PLAYER_SIZE = 16;
    public static final int TILE_SIZE = 16;
    public static final float PLAYER_SPEED = 0.3f;
    
    private float playerX = 17;
    private float playerY = 26;
    
    private float dirX;
    private float dirY;

    private Animation sprite, up, down, left, right;
    public boolean blocked[][];
    public String portal[][];
    
    public Test() {
        super("Pokemon");
    }
    
    public boolean blocked(float x, float y) {
        return blocked[(int)x][(int)y];
    }
    
    public boolean isPortal(float x, float y) {
        return portal[(int)x][(int)y] != null;
    }
    
    public boolean tryMove(float x, float y) throws SlickException{
        if(blocked(dirX, dirY)) {
            dirX = playerX;
            dirY = playerY;
            return false;   
        } else if(isPortal(x,y)) {
            if("pc".equals(portal[(int)x][(int)y])) {
                spritemap = "data/tiles/pokemonCenter.tmx";
                sprite = up;
                playerX = 7;
                playerY = 8;
                dirX = playerX;
                dirY = playerY;
                init(container);
                return false;
            } else if("outside".equals(portal[(int)x][(int)y])) {
                spritemap = "data/tiles/PewterCity.tmx";
                sprite = down;
                playerX = 17;
                playerY = 26;
                dirX = playerX;
                dirY = playerY;
                init(container);
            } 
            /*else if("downGrass".equals(portal[(int)x][(int)y])){
                //teleport 2 route2
            }*/
        } else {
            while(playerX != dirX || playerY != dirY) {
                playerX = dirX;
                playerY = dirY;
            }
            return true;
        }
        return true;
    }
    
    public boolean isMoving() {
        return dirX != playerX || dirY != playerY;
    }
    
    @Override 
    public void init(GameContainer container) throws SlickException {
        container.setVSync(true);
        Image [] movementUp = {new Image("data/hero/backhero.png"), new Image("data/hero/backstep1.png")};
        Image [] movementDown = {new Image("data/hero/fronthero.png"), new Image("data/hero/frontstep1.png")};
        Image [] movementLeft = {new Image("data/hero/lefthero.png"), new Image("data/hero/leftstep1.png")};
        Image [] movementRight = {new Image("data/hero/righthero.png"), new Image("data/hero/rightstep1.png")};
        int [] duration = {300, 300}; 
           
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);
        
        sprite = down;
        
        map = new BlockMap(spritemap);
        blocked = new boolean[BlockMap.tmap.getWidth()][BlockMap.tmap.getHeight()];
        for (int x=0;x<BlockMap.tmap.getWidth();x++) {
            for (int y=0;y<BlockMap.tmap.getHeight();y++) {
		int tileID = BlockMap.tmap.getTileId(x, y, 0);
		String value = BlockMap.tmap.getTileProperty(tileID, "blocked", "false");
		if ("true".equals(value)) {
		blocked[x][y] = true;
		}
            }
        }
        portal = new String[BlockMap.tmap.getWidth()][BlockMap.tmap.getHeight()];
        for (int x=0;x<BlockMap.tmap.getWidth();x++) {
            for (int y=0;y<BlockMap.tmap.getHeight();y++) {
		int tileID = BlockMap.tmap.getTileId(x, y, 0);
		String value = BlockMap.tmap.getTileProperty(tileID, "portal", null);
                portal[x][y] = value;   
            }
        }
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_LEFT)) {
            sprite = left;
            if(!isMoving()) {
                dirY = playerY;
                dirX = playerX - 1;
            }
            if (tryMove(dirX, dirY)) {
		// if we managed to move update the animation
		sprite.update(delta);
            }
        }
        if (container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
            sprite = right;
            if(!isMoving()) {
                dirY = playerY;
                dirX = playerX + 1;
            }
            if (tryMove(dirX, dirY)) {
		// if we managed to move update the animation
		sprite.update(delta);
            }
        }
        if (container.getInput().isKeyPressed(Input.KEY_UP)) {
            sprite = up;
            if(!isMoving()) {
                dirY = playerY - 1; //moves up one tile, stays on same x
                dirX = playerX;
            }
            if (tryMove(dirX, dirY)) {
		// if we managed to move update the animation
		sprite.update(delta);
            }
        }
        if (container.getInput().isKeyPressed(Input.KEY_DOWN)) {
            sprite = down;
            if(!isMoving()) {
                dirY = playerY + 1;
                dirX = playerX;
            }
            if (tryMove(dirX, dirY)) {
		// if we managed to move update the animation
		sprite.update(delta);
            }
        }
        if (container.getInput().isKeyPressed(Input.KEY_Q)) {
            spritemap = "data/tiles/pokemonCenter.tmx";
            init(container);
        }
        if(container.getInput().isKeyDown(Input.KEY_DOWN)|  // character animation in motion
            container.getInput().isKeyDown(Input.KEY_UP)|
            container.getInput().isKeyDown(Input.KEY_LEFT)|
            container.getInput().isKeyDown(Input.KEY_RIGHT)){
            sprite.setAutoUpdate(true);
        } else { 
                sprite.setAutoUpdate(false);
        }
    }
    
    @Override
    public void render(GameContainer container, Graphics g)  {
        BlockMap.tmap.render(0, 0);
        g.drawAnimation(sprite, playerX * TILE_SIZE, playerY * TILE_SIZE);
    }
    
    public static void main(String[] argv) throws SlickException {
        try {
            container = new AppGameContainer(new Test());
            container.setDisplayMode(768,640,false);
            container.start();
        } catch (SlickException e) {
        }
    }
}
