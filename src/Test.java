import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author benfederline
 */
public class Test extends BasicGame {
    public static Player p;
    
    public static AppGameContainer container;
    public static String GAMESTATE = "PLAY";
    private static int menuState = 1;
    private static int pokeMenuState = 1;
    public BlockMap map;
    
    TrueTypeFont font, font2;
    
    public String spritemap = "data/tiles/PewterCity.tmx";

    public static final int PLAYER_SIZE = 16;
    public static final int TILE_SIZE = 16;
    public static final float PLAYER_SPEED = 0.3f;
    public static final int scale = 2;
    
    private float playerX = 17;
    private float playerY = 26;
    
    private float dirX;
    private float dirY;
    
    private float charScreenCenterX, charScreenCenterY;

    private Animation sprite, up, down, left, right;
    public boolean blocked[][];
    public String portal[][];
    
    public Test(Player p) {
        super("Pokemon");
        Test.p = p;
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
            if(null != portal[(int)x][(int)y]) switch (portal[(int)x][(int)y]) {
                case "pc":
                    spritemap = "data/tiles/pokemonCenter.tmx";
                    sprite = up;
                    playerX = 7;
                    playerY = 8;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    return false;          
                case "outside":
                    spritemap = "data/tiles/PewterCity.tmx";
                    sprite = down;
                    playerX = 17;
                    playerY = 26;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "downGrass":
                    spritemap = "data/tiles/route2.tmx";
                    sprite = down;
                    playerX = 10;
                    playerY = 1;
            } 

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
        Image [] movementUp = {new Image("data/hero/backhero.png"), new Image("data/hero/backstep1.png"), new Image("data/hero/backstep2.png")};
        Image [] movementDown = {new Image("data/hero/fronthero.png"), new Image("data/hero/frontstep1.png"), new Image("data/hero/frontstep2.png")};
        Image [] movementLeft = {new Image("data/hero/lefthero.png"), new Image("data/hero/leftstep1.png"), new Image("data/hero/leftstep2.png")};
        Image [] movementRight = {new Image("data/hero/righthero.png"), new Image("data/hero/rightstep1.png"), new Image("data/hero/rightstep2.png")};
        int [] duration = {90, 90, 90}; 
           
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
       
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        try {
		InputStream inputStream	= ResourceLoader.getResourceAsStream("data/fonts/Pokemon DPPt.ttf");
 
		Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont2 = awtFont2.deriveFont(55f); // set font size
		font = new TrueTypeFont(awtFont2, false);
 
	} catch (FontFormatException | IOException e) {
	}  
        try {
		InputStream inputStream	= ResourceLoader.getResourceAsStream("data/fonts/Pokemon DPPt.ttf");
 
		Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont2 = awtFont2.deriveFont(40f); // set font size
		font2 = new TrueTypeFont(awtFont2, false);
 
	} catch (FontFormatException | IOException e) {
	}
    }
    
    private void playGameStateLogic(GameContainer container, int delta) throws SlickException {
        if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
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
        if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
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
        if (container.getInput().isKeyDown(Input.KEY_UP)) {
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
        if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
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
        if (container.getInput().isKeyPressed(Input.KEY_P)) {
            GAMESTATE = "PAUSE";
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
    
    private void pauseMenuLogic(GameContainer container, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_P)) {
            GAMESTATE = "PLAY";
        } 
        if(container.getInput().isKeyPressed(Input.KEY_Z)) {
            GAMESTATE = "PLAY";
        }
        if(container.getInput().isKeyPressed(Input.KEY_UP)) {
            menuState --;
            if(menuState <= 1) 
                menuState = 1;
        }
        if(container.getInput().isKeyPressed(Input.KEY_DOWN)) {
            menuState ++;
            if(menuState >= 7)
                menuState = 7;
        }   
        if(container.getInput().isKeyPressed(Input.KEY_X)) {
            if(menuState == 1) {
                //GAMESTATE = "POKEDEX";
            } else if(menuState == 2) {
                GAMESTATE = "POKEMON";
            } else if(menuState == 3) {
                //GAMESTATE = "BAG";
            } else if(menuState == 4) {
                //GAMESTATE = "TRAINERCARD";
            } else if(menuState == 5) {
                //GAMESTATE = "SAVE";
            } else if(menuState == 6) {
                //GAMESTATE = "OPTIONS";
            } else if(menuState == 7) {
                GAMESTATE = "PLAY";
            }
        } 
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (null != GAMESTATE) switch (GAMESTATE) {
            case "PLAY":
                playGameStateLogic(container, delta);
                break;
            case "PAUSE":
                pauseMenuLogic(container, delta);
            case "POKEDEX":
                break;
            case "POKEMON":
                if(container.getInput().isKeyPressed(Input.KEY_Z)) {
                    GAMESTATE = "PAUSE";
                }
                if(container.getInput().isKeyPressed(Input.KEY_UP)) {
                    pokeMenuState --;
                    if(pokeMenuState < 1) 
                        pokeMenuState = 7;
                }
                if(container.getInput().isKeyPressed(Input.KEY_DOWN)) {
                    pokeMenuState ++;
                    if(pokeMenuState > 7) 
                        pokeMenuState = 1;
                }
                if(container.getInput().isKeyPressed(Input.KEY_X)) {
                    if(pokeMenuState == 1) {
                        //GAMESTATE = "POKEDEX";
                    } else if(pokeMenuState == 2) {
                        //GAMESTATE = "POKEMON";
                    } else if(pokeMenuState == 3) {
                        //GAMESTATE = "BAG";
                    } else if(pokeMenuState == 4) {
                        //GAMESTATE = "TRAINERCARD";
                    } else if(pokeMenuState == 5) {
                        //GAMESTATE = "SAVE";
                    } else if(pokeMenuState == 6) {
                        //GAMESTATE = "OPTIONS";
                    } else if(pokeMenuState == 7) {
                        GAMESTATE = "PAUSE";
                    }
                }    
                break;
            case "BAG":
                break;
            case "TRAINERCARD":
                break;
            case "SAVE":
                break;
            case "OPTIONS":
                break;
        }
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException  {        
        //Slick2D for some godforsaken reason creates a new Color object every time you use the 
        //setColor(...) method. Plan to modify source to improve performance, but this is out of 
        //scope at this point in time. 
        
        charScreenCenterX = BlockMap.tmap.getWidth()/scale *7f - (playerX * TILE_SIZE); 
        charScreenCenterY = BlockMap.tmap.getHeight()/scale * 7f - (playerY * TILE_SIZE); 
        
        g.scale(scale, scale);         
        g.translate(charScreenCenterX,charScreenCenterY);
        
        // Render your character and other stuff here! 
        BlockMap.tmap.render(0,0);
        g.drawAnimation(sprite, playerX * TILE_SIZE, playerY * TILE_SIZE);
        g.resetTransform(); 
        
        if("PAUSE".equals(GAMESTATE)) {         
            Image menuPoint = new Image("data/tiles/pointer.png");
            //actual menu
            g.setColor(Color.white);
            g.drawRoundRect(530, 8, 230, 430, 10);
            g.fillRoundRect(530, 8, 230, 430, 10);

            //menu drawing
            Color.white.bind();
            font.drawString(590, 20, "POKEDEX", Color.black); //1
            font.drawString(590, 80, "POKEMON", Color.black); //2
            font.drawString(590, 140,"BAG"    , Color.black); //3
            font.drawString(590, 200, p.name, Color.black);   //4
            font.drawString(590, 260, "SAVE", Color.black);   //5
            font.drawString(590, 320, "OPTION", Color.black); //6
            font.drawString(590, 380, "EXIT", Color.black);   //7
            
            if(menuState == 1) {
                menuPoint.draw(550,30,3);
            } else if(menuState == 2) {
                menuPoint.draw(550,90,3);
            } else if(menuState == 3) {
                menuPoint.draw(550,150,3);
            } else if(menuState == 4) {
                menuPoint.draw(550,210,3);
            } else if(menuState == 5) {
                menuPoint.draw(550,270,3);
            } else if(menuState == 6) {
                menuPoint.draw(550,330,3);
            } else if(menuState == 7) {
                menuPoint.draw(550,390,3);
            }
        } else if("POKEMON".equals(GAMESTATE)) {
            Image pokemonMenuMain = new Image("data/tiles/pokemonmenumain.png");
            Image bigSelectedSlot = new Image("data/tiles/bigSelectedSlot.png");
            Image selectedSlot = new Image("data/tiles/selectedSlot.png");
            Image bigUnselectedSlot = new Image("data/tiles/bigUnselectedSlot.png");
            Image unselectedSlot = new Image("data/tiles/unselectedSlot.png");
            Image selectedCancel = new Image("data/tiles/selectedCancel.png");
            Image unselectedCancel = new Image("data/tiles/unselectedCancel.png");
            Image holditem = new Image("data/tiles/holditem.png");
            Image mainPokeImage=null, poke2image=null, poke3image=null, poke4image=null, poke5image=null, poke6image=null;
            if(p.main != null) {
                mainPokeImage = new Image(p.main.overworld_img);            
            }
            if(p.p2 != null) {
                poke2image = new Image(p.p2.overworld_img);
            }
            if(p.p3 != null) {
                poke3image = new Image(p.p3.overworld_img);
            }
            if(p.p4 != null) {
                poke4image = new Image(p.p4.overworld_img);
            }
            if(p.p5 != null) {
                poke5image = new Image(p.p5.overworld_img);
            }
            if(p.p6 != null) {
                poke6image = new Image(p.p6.overworld_img);
            }
            
            //background
            pokemonMenuMain.draw(0,0,2);
            
            //check the status of the players current pokemon lineup and print accordingly
            if(pokeMenuState == 1) {
                bigSelectedSlot.draw(50,150,3);
                unselectedSlot.draw(310,50,3);
                unselectedSlot.draw(310,150,3);
                unselectedSlot.draw(310,250,3);
                unselectedSlot.draw(310,350,3);
                unselectedSlot.draw(310,450,3);
                unselectedCancel.draw(510,550,3);
      
            } else if(pokeMenuState == 2) {
                bigUnselectedSlot.draw(50,150,3);
                selectedSlot.draw(310,50,3);
                unselectedSlot.draw(310,150,3);
                unselectedSlot.draw(310,250,3);
                unselectedSlot.draw(310,350,3);
                unselectedSlot.draw(310,450,3);
                unselectedCancel.draw(510,550,3);
                
            } else if(pokeMenuState == 3) {
                bigUnselectedSlot.draw(50,150,3);
                unselectedSlot.draw(310,50,3);
                selectedSlot.draw(310,150,3);
                unselectedSlot.draw(310,250,3);
                unselectedSlot.draw(310,350,3);
                unselectedSlot.draw(310,450,3);
                unselectedCancel.draw(510,550,3);
                
            } else if(pokeMenuState == 4) {
                bigUnselectedSlot.draw(50,150,3);
                unselectedSlot.draw(310,50,3);
                unselectedSlot.draw(310,150,3);
                selectedSlot.draw(310,250,3);
                unselectedSlot.draw(310,350,3);
                unselectedSlot.draw(310,450,3);
                unselectedCancel.draw(510,550,3);
                
            } else if(pokeMenuState == 5) {
                bigUnselectedSlot.draw(50,150,3);
                unselectedSlot.draw(310,50,3);
                unselectedSlot.draw(310,150,3);
                unselectedSlot.draw(310,250,3);
                selectedSlot.draw(310,350,3);
                unselectedSlot.draw(310,450,3);
                unselectedCancel.draw(510,550,3);
                
            } else if(pokeMenuState == 6) {
                bigUnselectedSlot.draw(50,150,3);
                unselectedSlot.draw(310,50,3);
                unselectedSlot.draw(310,150,3);
                unselectedSlot.draw(310,250,3);
                unselectedSlot.draw(310,350,3);
                selectedSlot.draw(310,450,3);
                unselectedCancel.draw(510,550,3);
                
            } else if(pokeMenuState == 7) {
                bigUnselectedSlot.draw(50,150,3);
                unselectedSlot.draw(310,50,3);
                unselectedSlot.draw(310,150,3);
                unselectedSlot.draw(310,250,3);
                unselectedSlot.draw(310,350,3);
                unselectedSlot.draw(310,450,3);
                selectedCancel.draw(510,550,3);
                
            }    
            
            if(p.main != null) {
                mainPokeImage.draw(50,150,3);
                font2.drawString(130, 200, p.main.name, Color.white);
                font2.drawString(200, 232, "" + p.main.level,Color.white);
                font2.drawString(195, 282, "" + (int)p.main.current_hp,Color.white);
                font2.drawString(245, 282, "" + (int)p.main.hp,Color.white);
                
                
            }
            if(p.p2 != null) {
                //also draw this one
            }
            if(p.p3 != null) {
                //also draw this one
            }
            if(p.p4 != null) {
                //also draw this one
            }
            if(p.p5 != null) {
                //also draw this one
            }
            if(p.p6 != null) {
                //aaaand also draw this one
            }
            
        }
    }
}
