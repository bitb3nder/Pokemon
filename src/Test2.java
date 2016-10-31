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
    public BlockMap map;

    public static final int PLAYER_SIZE = 16;
    public static final int TILE_SIZE = 16;
    public static final float speed = .003f;
    
    private float playerX = 17;
    private float playerY = 26;
    
    private float playerXInPixels = playerX * TILE_SIZE;
    private float playerYInPixels = playerY * TILE_SIZE;
    
    private float oldX;
    private float oldY;
    
    private float dirX;
    private float dirY;
    
    private float velX;
    private float velY;
    
    private String intention;
    private String lastMove;

    private Animation sprite, up, down, left, right;
    public boolean blocked[][];
    
    public Test() {
        super("Pokemon");
    }
    
    public void getTileAdjacentToTile(float x, float y, String direction) {
        switch (direction) {
            case "up":
                dirY -= 1;
                break;
            case "down":
                dirY += 1;
                break;
            case "left":
                dirX -= 1;
                break;
            case "right":
                dirX += 1;
                break;
        }
    }
    
    public void startMoving(String direction) {
        getTileAdjacentToTile(playerX, playerY, direction);
        setVelocity(dirX, dirY, speed);
        lastMove = direction;
    }
    
    public void continueMovingToDestination() {
        setVelocity(dirX, dirY, speed);
    }
    
    public void continueMovingFromDestination() {
       getTileAdjacentToTile(playerX, playerY, lastMove);
       setVelocity(dirX, dirY, speed);
    }
    
    public void changeDirectionAndContinueMoving(String direction) {
        snapToTile();
        getTileAdjacentToTile(playerX, playerY, direction);
        setVelocity(dirX, dirY, speed);
        lastMove = direction;
    }
    
    public void stopMoving() {
        snapToTile();
        dirX = playerX;
        dirY = playerY;
        velX = velY = 0;
    }
    
    public void snapToTile() {
        playerXInPixels = dirX * TILE_SIZE;
        playerYInPixels = dirY * TILE_SIZE;
    }
    
    public boolean justReachedDestination() {
        float destX = dirX * TILE_SIZE;
        float destY = dirY * TILE_SIZE;
        
        switch (intention) {
            case "up":
                if(playerYInPixels >= destY)
                    return true;
                break;
            case "down":
                if(playerYInPixels <= destY)
                    return true;
                break;
            case "left":
                if(playerXInPixels <= destX)
                    return true;
                break;
            case "right":
                if(playerXInPixels >= destX)
                    return true;
                break;
        }
        return false; 
    }
    
    public boolean blocked(float x, float y) {
        return blocked[(int)x][(int)y];
    }
    
    public boolean canMoveDirectionFromTile(float x, float y){
        return blocked(x, y);
    }
    
    public boolean isMoving() {
        return intention != null;
    }
    
    public void setVelocity(float x, float y, float speed) {
        switch (intention) {
            case "up":
                velY = speed;
                break;
            case "down":
                velY = -speed;
                break;
            case "left":
                velX = -speed;
                break;
            case "right":
                velX = speed;
                break;
        }
        while(justReachedDestination()){
            playerXInPixels += velX;
            playerYInPixels += velY;
        }
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
        
        map = new BlockMap("data/tiles/PewterCity.tmx");
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
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        //reset intention
        intention = null;
        if (container.getInput().isKeyPressed(Input.KEY_LEFT)) {
            intention = "left";
            sprite = left;
        }
        if (container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
            intention = "right";
            sprite = right;
        }
        if (container.getInput().isKeyPressed(Input.KEY_UP)) {
            intention = "up";
            sprite = up;
        }
        if (container.getInput().isKeyPressed(Input.KEY_DOWN)) {
            intention = "down";
            sprite = down;
        }
        
        // Stop the moving entity if at the destination.
        if(isMoving() && justReachedDestination() && (intention == null)) {
            stopMoving();
        }
        // Stop the moving entity when it hits a wall.
        else if(isMoving() && justReachedDestination() && intention != null && !canMoveDirectionFromTile(dirX, dirY)) {
            stopMoving();
        }
        // Destination reached, but set new destination and keep going.
        else if(isMoving() && justReachedDestination() && intention != null && canMoveDirectionFromTile(dirX, dirY) && (intention == null ? lastMove == null : intention.equals(lastMove))) {
            continueMovingFromDestination();
        }
        // Destination reached, but changing direction and continuing.
        else if(isMoving() && justReachedDestination() && intention != null && canMoveDirectionFromTile(dirX, dirY) && (intention == null ? lastMove != null : !intention.equals(lastMove))) {
            changeDirectionAndContinueMoving(intention);
        }
        // Destination not yet reached, so keep going.
        else if(isMoving() && !justReachedDestination()) {
            continueMovingToDestination();
        }
        // Not moving, but wanting to, so start!
        else if(!isMoving() && intention != null && canMoveDirectionFromTile(dirX, dirY)) {
            startMoving(intention);
        }
    }
    
    @Override
    public void render(GameContainer container, Graphics g)  {
        BlockMap.tmap.render(0, 0);
        g.drawAnimation(sprite, playerXInPixels, playerYInPixels);
    }
    
    public static void main(String[] argv) throws SlickException {
        try {
            AppGameContainer container = new AppGameContainer(new Test());
            container.setDisplayMode(768,640,false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}