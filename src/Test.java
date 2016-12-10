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
    public static String LOCATION = "PEWTER";
    public static String turnType = "";
    private static int menuState = 1; //of the main menu
    private static int pokeMenuState = 1; //pf the pokemon menu
    private static int menuMenuState = 1; //of each pokemon (swap, item, etc)
    private static int profileState = 1;
    private static int interactCount = 1;
    private static int finalInteractCount = 1;
    
    private static int battleSelectTypeCount = 1;
    private static int moveSelectCount = 1;
    public BlockMap map;
    
    TrueTypeFont font, font2, font3, font4;
    
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

    private Image nurse,martman,gymleader,prof,mom,bill,oldman,rival;
    private Animation sprite, up, down, left, right;
    public boolean blocked[][];
    public String portal[][];
    public String interactable[][];
    
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
    
    public boolean isInteractable(float x, float y) {
        return interactable[(int)x][(int)y] != null;
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
                    LOCATION = "POKECENTER";
                    sprite = up;
                    playerX = 7;
                    playerY = 8;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;  
                //temporary fix: brings the player outside from pc
                case "outside":
                    spritemap = "data/tiles/PewterCity.tmx";
                    LOCATION = "PEWTER";
                    sprite = down;
                    playerX = 17;
                    playerY = 26;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "mart":
                    spritemap = "data/tiles/martTile.tmx";
                    LOCATION = "POKEMART";
                    sprite = up;
                    playerX = 4;
                    playerY = 7;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                //same shitty design oops
                case "outside2":
                    spritemap = "data/tiles/PewterCity.tmx";
                    LOCATION = "PEWTER";
                    sprite = down;
                    playerX = 28;
                    playerY = 19;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "gym":
                    spritemap = "data/tiles/gym1Tile.tmx";
                    LOCATION = "GYM";
                    sprite = up;
                    playerX = 6;
                    playerY = 14;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                //lmao again and again and again 
                case "outside3":
                    spritemap = "data/tiles/PewterCity.tmx";
                    LOCATION = "PEWTER";
                    sprite = down;
                    playerX = 15;
                    playerY = 17;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "lab1":
                    spritemap = "data/tiles/labF1.tmx";
                    LOCATION = "LABF1";
                    sprite = up;
                    playerX = 14;
                    playerY = 9;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "outside4":
                    spritemap = "data/tiles/PewterCity.tmx";
                    LOCATION = "PEWTER";
                    sprite = down;
                    playerX = 17;
                    playerY = 7;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "lab2":
                    spritemap = "data/tiles/labF1.tmx";
                    LOCATION = "LABF1";
                    sprite = up;
                    playerX = 21;
                    playerY = 9;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "outside5":
                    spritemap = "data/tiles/PewterCity.tmx";
                    LOCATION = "PEWTER";
                    sprite = down;
                    playerX = 25;
                    playerY = 5;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "labF2":
                    spritemap = "data/tiles/labF2.tmx";
                    LOCATION = "LABF2";
                    sprite = right;
                    playerX = 10;
                    playerY = 8;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "labF1":
                    spritemap = "data/tiles/labF1.tmx";
                    LOCATION = "LABF1";
                    sprite = left;
                    playerX = 9;
                    playerY = 8;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "herohome":
                    spritemap = "data/tiles/herohome.tmx";
                    LOCATION = "HOME";
                    sprite = down;
                    playerX = 4;
                    playerY = 7;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "outside6":
                    spritemap = "data/tiles/PewterCity.tmx";
                    LOCATION = "PEWTER";
                    sprite = down;
                    playerX = 9;
                    playerY = 31;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "rivalhome":
                    spritemap = "data/tiles/rivalhome.tmx";
                    LOCATION = "RIVALHOME";
                    sprite = down;
                    playerX = 4;
                    playerY = 7;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
                case "outside7":
                    spritemap = "data/tiles/PewterCity.tmx";
                    LOCATION = "PEWTER";
                    sprite = down;
                    playerX = 33;
                    playerY = 12;
                    dirX = playerX;
                    dirY = playerY;
                    init(container);
                    break;
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
        
        nurse = new Image("data/tiles/nursejoy.png");
        martman = new Image("data/tiles/martman.png");
        gymleader = new Image("data/tiles/gymleader.png");
        prof = new Image("data/tiles/profoak.png");
        mom = new Image("data/tiles/mom.png");
        bill = new Image("data/tiles/bill.png");
        oldman = new Image("data/tiles/oldman.png");
        rival = new Image("data/tiles/rival.png");
        
           
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
        interactable = new String[BlockMap.tmap.getWidth()][BlockMap.tmap.getHeight()];
        for (int x=0;x<BlockMap.tmap.getWidth();x++) {
            for (int y=0;y<BlockMap.tmap.getHeight();y++) {
		int tileID = BlockMap.tmap.getTileId(x, y, 0);
		String value = BlockMap.tmap.getTileProperty(tileID, "interactable", null);
                interactable[x][y] = value;   
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
        try {
		InputStream inputStream	= ResourceLoader.getResourceAsStream("data/fonts/Pokemon DPPt.ttf");
 
		Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont2 = awtFont2.deriveFont(30f); // set font size
		font3 = new TrueTypeFont(awtFont2, false);
 
	} catch (FontFormatException | IOException e) {
	}
        try {
		InputStream inputStream	= ResourceLoader.getResourceAsStream("data/fonts/Pokemon DPPt.ttf");
 
		Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont2 = awtFont2.deriveFont(20f); // set font size
		font4 = new TrueTypeFont(awtFont2, false);
 
	} catch (FontFormatException | IOException e) {
	}
    }
    
    private void playGameStateLogic(GameContainer container, int delta) throws SlickException {
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
        if (container.getInput().isKeyPressed(Input.KEY_X)) {
            if(isInteractable(playerX, playerY)) {
                GAMESTATE = "INTERACTING";
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
    
    private void interactableLogic(GameContainer container, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_X) || container.getInput().isKeyPressed(Input.KEY_Z)){
            interactCount++;
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
                GAMESTATE = "POKEDEX";
            } else if(menuState == 2) {
                GAMESTATE = "POKEMON";
            } else if(menuState == 3) {
                GAMESTATE = "BAG";
            } else if(menuState == 4) {
                GAMESTATE = "TRAINERCARD";
            } else if(menuState == 5) {
                GAMESTATE = "SAVE";
            } else if(menuState == 6) {
                GAMESTATE = "OPTIONS";
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
            case "INTERACTING":
                interactableLogic(container, delta);
                break;
            case "PAUSE":
                pauseMenuLogic(container, delta);
            case "POKEDEX":
                if(container.getInput().isKeyPressed(Input.KEY_Z)) {
                    GAMESTATE = "PAUSE";
                }
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
                        //individual menu thing 
                        GAMESTATE = "POKEMENUMENU";
                    } else if(pokeMenuState == 2) {
                        //individual menu thing
                        GAMESTATE = "POKEMENUMENU";
                    } else if(pokeMenuState == 3) {
                        //individual menu thing
                        GAMESTATE = "POKEMENUMENU";
                    } else if(pokeMenuState == 4) {
                        //individual menu thing
                        GAMESTATE = "POKEMENUMENU";
                    } else if(pokeMenuState == 5) {
                        //individual menu thing 
                        GAMESTATE = "POKEMENUMENU";
                    } else if(pokeMenuState == 6) {
                        //individual menu thing 
                        GAMESTATE = "POKEMENUMENU";
                    } else if(pokeMenuState == 7) {
                        GAMESTATE = "PAUSE";
                    }
                }       
                break;
            case "POKEMENUMENU":
                if(container.getInput().isKeyPressed(Input.KEY_UP)) {
                    menuMenuState --;
                    if(menuMenuState < 1) 
                        menuMenuState = 4;
                }
                if(container.getInput().isKeyPressed(Input.KEY_DOWN)) {
                    menuMenuState ++;
                    if(menuMenuState > 4) 
                        menuMenuState = 1;
                }
                if(container.getInput().isKeyPressed(Input.KEY_X) && menuMenuState == 1) {
                    GAMESTATE = "POKEPROFILE";
                } else if(container.getInput().isKeyPressed(Input.KEY_X) && menuMenuState == 4) {
                    GAMESTATE = "POKEMON";
                } else if(container.getInput().isKeyPressed(Input.KEY_Z)) {
                    GAMESTATE = "POKEMON";
                }
                break;
            case "POKEPROFILE":
                if(container.getInput().isKeyPressed(Input.KEY_Z)) {
                    GAMESTATE = "POKEMENUMENU";
                }
                if(container.getInput().isKeyPressed(Input.KEY_LEFT)) {
                    profileState--;
                    if(profileState < 1){
                        profileState = 3;
                    }
                    
                }
                if(container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
                    profileState++;
                    if(profileState > 3){
                        profileState = 1;
                    }
                }
                break;
            case "BAG":
                if(container.getInput().isKeyPressed(Input.KEY_Z)) {
                    GAMESTATE = "PAUSE";
                }
                break;
            case "TRAINERCARD":
                if(container.getInput().isKeyPressed(Input.KEY_Z)) {
                    GAMESTATE = "PAUSE";
                }
                break;
            case "SAVE":
                if(container.getInput().isKeyPressed(Input.KEY_Z)) {
                    GAMESTATE = "PAUSE";
                }
                break;
            case "OPTIONS":
                if(container.getInput().isKeyPressed(Input.KEY_Z)) {
                    GAMESTATE = "PAUSE";
                }
                break;
                
            case "BATTLE":
                //menu has values 1, 2, 3, 4
                // 1 & 2 are next to each other
                // 3 & 4 are next to each other and under 1 & 2
                // traveling between 1 & 2 is done with left/right keys
                // traveling between sets 1&2 and 3&4 is done with up/down keys
                
                if(container.getInput().isKeyPressed(Input.KEY_UP)) {
                    //if currently at 3, goes to 1... if 4 goes to 2
                    battleSelectTypeCount -= 2;
                    if(battleSelectTypeCount < 1) {
                        //if already at 1 or 2 and up is pressed, undoes incrementation
                        battleSelectTypeCount += 2;
                    }
                }
                if(container.getInput().isKeyPressed(Input.KEY_DOWN)) {
                    //if currently at 1, goes to 3... if 2 goes to 4
                    battleSelectTypeCount += 2;
                    if(battleSelectTypeCount > 4) {
                        //if already at 3 or 4 and down is pressed, undoes incrementation
                        battleSelectTypeCount -= 2;
                    }
                } 
                if(container.getInput().isKeyPressed(Input.KEY_LEFT)) {
                    //if currently at 2, goes to 1... if 4 goes to 3
                    battleSelectTypeCount -= 1;
                    if(battleSelectTypeCount < 1) {
                        //if already at 1 or 3 and down is pressed, undoes incrementation
                        battleSelectTypeCount += 1;
                    }
                }
                if(container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
                    //if currently at 1, goes to 3... if 2 goes to 4
                    battleSelectTypeCount += 1;
                    if(battleSelectTypeCount > 4) {
                        //if already at 3 or 4 and down is pressed, undoes incrementation
                        battleSelectTypeCount -= 1;
                    }
                }
                if(container.getInput().isKeyPressed(Input.KEY_X)) {
                    //selected FIGHT
                    if(battleSelectTypeCount == 1) {
                        GAMESTATE = "SELECTMOVE";
                        turnType = "FIGHT";
                    } else if(battleSelectTypeCount == 2) {
                        //not yet a required feature
                        turnType = "ITEM";
                    } else if(battleSelectTypeCount == 3) {
                        //not yet a required feature
                        turnType = "SWAP";
                    } else if(battleSelectTypeCount == 4) {
                        GAMESTATE = "PLAY";
                    }
                }
                break;
            case "SELECTMOVE":
                if(container.getInput().isKeyPressed(Input.KEY_Z)) {
                    GAMESTATE = "BATTLE";
                }
                if(container.getInput().isKeyPressed(Input.KEY_X)) {
                    //selected FIGHT
                    if(moveSelectCount == 1) {
                    } else if(moveSelectCount == 2) {
                    } else if(moveSelectCount == 3) {
                    } else if(moveSelectCount == 4) {
                    }
                }
                break;
        }
    }
    
    public void battle(Pokemon poke, Pokemon foe) {
        while(poke.current_hp > 0 && foe.current_hp > 0) {
            Fight.turn(poke, foe, turnType);
            GAMESTATE = "BATTLE";
        }  
        if(poke.current_hp <= 0) {
            System.out.println("You lost. You were not supposed to do that.");
            GAMESTATE = "PLAY";
            //Start the entire game over.
        } else if(foe.current_hp <= 0) {
            Fight.collectEXP(poke, foe);
            Fight.collectEV(poke, foe);
            MonsterGameplay.check(poke);
            GAMESTATE = "PLAY";
        }
    }
    
    public void drawPauseMenu(Graphics g) throws SlickException {
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
    }
    
    public void drawPokeMenu(Graphics g) throws SlickException {
        drawPauseMenu(g);
            Image pokemonMenuMain = new Image("data/tiles/pokemonmenumain2.png");
            Image bigSelectedSlot = new Image("data/tiles/bigSelectedSlot.png");
            Image selectedSlot = new Image("data/tiles/selectedSlot.png");
            Image bigUnselectedSlot = new Image("data/tiles/bigUnselectedSlot.png");
            Image unselectedSlot = new Image("data/tiles/unselectedSlot.png");
            Image selectedCancel = new Image("data/tiles/selectedCancel.png");
            Image unselectedCancel = new Image("data/tiles/unselectedCancel.png");
            Image holditem = new Image("data/tiles/holditem.png");
            Image male = new Image("data/tiles/malelogo.png");
            Image female = new Image("data/tiles/femalelogo.png");
            
            Image greenhp = new Image("data/tiles/greenhealth.png");
            Image yellowhp = new Image("data/tiles/yellowhealth.png");
            Image redhp = new Image("data/tiles/redhealth.png");
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
            g.setColor(Color.black);
            g.drawRoundRect(15, 8, 484, 324, 10);
            g.fillRoundRect(15, 8, 484, 324, 10);
            pokemonMenuMain.draw(17,10,2);
            
            font2.drawString(40,280,"SELECT A POKeMON",Color.black);
            
            //check the status of the players current pokemon lineup and print accordingly
            if(pokeMenuState == 1) {
                bigSelectedSlot.draw(22,46,2);
                unselectedSlot.draw(190,30,2);
                unselectedSlot.draw(190,78,2);
                unselectedSlot.draw(190,126,2);
                unselectedSlot.draw(190,174,2);
                unselectedSlot.draw(190,222,2);
                unselectedCancel.draw(384,276,2);
      
            } else if(pokeMenuState == 2) {
                bigUnselectedSlot.draw(22,46,2);
                selectedSlot.draw(190,30,2);
                unselectedSlot.draw(190,78,2);
                unselectedSlot.draw(190,126,2);
                unselectedSlot.draw(190,174,2);
                unselectedSlot.draw(190,222,2);
                unselectedCancel.draw(384,276,2);
                
            } else if(pokeMenuState == 3) {
                bigUnselectedSlot.draw(22,46,2);
                unselectedSlot.draw(190,30,2);
                selectedSlot.draw(190,78,2);
                unselectedSlot.draw(190,126,2);
                unselectedSlot.draw(190,174,2);
                unselectedSlot.draw(190,222,2);
                unselectedCancel.draw(384,276,2);
                
            } else if(pokeMenuState == 4) {
                bigUnselectedSlot.draw(22,46,2);
                unselectedSlot.draw(190,30,2);
                unselectedSlot.draw(190,78,2);
                selectedSlot.draw(190,126,2);
                unselectedSlot.draw(190,174,2);
                unselectedSlot.draw(190,222,2);
                unselectedCancel.draw(384,276,2);
                
            } else if(pokeMenuState == 5) {
                bigUnselectedSlot.draw(22,46,2);
                unselectedSlot.draw(190,30,2);
                unselectedSlot.draw(190,78,2);
                unselectedSlot.draw(190,126,2);
                selectedSlot.draw(190,174,2);
                unselectedSlot.draw(190,222,2);
                unselectedCancel.draw(384,276,2);
                
            } else if(pokeMenuState == 6) {
                bigUnselectedSlot.draw(22,46,2);
                unselectedSlot.draw(190,30,2);
                unselectedSlot.draw(190,78,2);
                unselectedSlot.draw(190,126,2);
                unselectedSlot.draw(190,174,2);
                selectedSlot.draw(190,222,2);
                unselectedCancel.draw(384,276,2);
                
            } else if(pokeMenuState == 7) {
                bigUnselectedSlot.draw(22,46,2);
                unselectedSlot.draw(190,30,2);
                unselectedSlot.draw(190,78,2);
                unselectedSlot.draw(190,126,2);
                unselectedSlot.draw(190,174,2);
                unselectedSlot.draw(190,222,2);
                selectedCancel.draw(384,276,2);
                
            }    
            
            if(p.main != null) {
                mainPokeImage.draw(22,46,2);
                font3.drawString(80,80, p.main.name, Color.white);
                font4.drawString(130,105, "" + p.main.level, Color.white);
                font4.drawString(120, 136, "" + (int)p.main.current_hp, Color.white);
                font4.drawString(155, 136, "" + (int)p.main.hp, Color.white);
                if("MALE".equals(p.main.gender)) {
                    male.draw(170,85,2);
                } else {
                    female.draw(170,85,2);
                }
                //draw health bar depending on current_hp and hp.
                for(int i=0; i<94; i++) {
                    greenhp.draw(83+i, 127, 2);
                }    
            }
            if(p.p2 != null) {
                poke2image.draw(190,30,2);
                font3.drawString(245,33, p.p2.name, Color.white);
                font4.drawString(294,56, "" + p.p2.level, Color.white);
                font4.drawString(415, 56, "" + (int)p.p2.current_hp, Color.white);
                font4.drawString(457, 56, "" + (int)p.p2.hp, Color.white);
                if("MALE".equals(p.p2.gender)) {
                    male.draw(340,40,2);
                } else {
                    female.draw(340,40,2);
                }
            }
            if(p.p3 != null) {
                poke3image.draw(190,78,2);
                font3.drawString(245,82, p.p3.name, Color.white);
                font4.drawString(294,105, "" + p.p3.level, Color.white);
                font4.drawString(415, 105, "" + (int)p.p3.current_hp, Color.white);
                font4.drawString(457, 105, "" + (int)p.p3.hp, Color.white);
                if("MALE".equals(p.p3.gender)) {
                    male.draw(340,89,2);
                } else {
                    female.draw(340,89,2);
                }
            }
            if(p.p4 != null) {
                poke4image.draw(190,126,2);
                font3.drawString(245,130, p.p4.name, Color.white);
                font4.drawString(294,153, "" + p.p4.level, Color.white);
                font4.drawString(415, 153, "" + (int)p.p4.current_hp, Color.white);
                font4.drawString(457, 153, "" + (int)p.p4.hp, Color.white);
                if("MALE".equals(p.p4.gender)) {
                    male.draw(340,135,2);
                } else {
                    female.draw(340,135,2);
                }
            }
            if(p.p5 != null) {
                poke5image.draw(190,174,2);
                font3.drawString(245,178, p.p5.name, Color.white);
                font4.drawString(294,201, "" + p.p5.level, Color.white);
                font4.drawString(415, 201, "" + (int)p.p5.current_hp, Color.white);
                font4.drawString(457, 201, "" + (int)p.p5.hp, Color.white);
                if("MALE".equals(p.p5.gender)) {
                    male.draw(340,183,2);
                } else {
                    female.draw(340,183,2);
                }
            }
            if(p.p6 != null) {
                poke6image.draw(190,222,2);
                font3.drawString(245,225, p.p6.name, Color.white);
                font4.drawString(294,248, "" + p.p6.level, Color.white);
                font4.drawString(415, 248, "" + (int)p.p6.current_hp, Color.white);
                font4.drawString(457, 248, "" + (int)p.p6.hp, Color.white);
                if("MALE".equals(p.p6.gender)) {
                    male.draw(340,231,2);
                } else {
                    female.draw(340,231,2);
                }
            }
    }
    
    public void drawPokeMenuMenu(Graphics g) throws SlickException {
        drawPauseMenu(g);
        drawPokeMenu(g);
        
        Image menuPoint = new Image("data/tiles/pointer.png");
        Image menumenu = new Image("data/tiles/pokemenumenu.png");
        menumenu.draw(370,142,2);
    
        font2.drawString(410,156, "INFO", Color.black);
        font2.drawString(410,196, "ITEM", Color.black);
        font2.drawString(410,236, "SWAP", Color.black);
        font2.drawString(410,276, "EXIT", Color.black);
        
        if(menuMenuState == 1) {
            menuPoint.draw(386,166,2);
        } else if(menuMenuState == 2) {
            menuPoint.draw(386,206,2);
        } else if(menuMenuState == 3) {
            menuPoint.draw(386,246,2);
        } else if(menuMenuState == 4) {
            menuPoint.draw(386,286,2);
        }
    }
    
    public void drawPokeProfile(Pokemon poke, Graphics g) throws SlickException {
        drawPauseMenu(g);
        
        Image pro1 = new Image("data/tiles/pokeprofile1.png");
        Image pro2 = new Image("data/tiles/pokeprofile2.png");
        Image pro3 = new Image("data/tiles/pokeprofile3.png");
        Image prohead2 = new Image("data/tiles/pokeprofileheader2.png");
        Image prohead3 = new Image("data/tiles/pokeprofileheader3.png");
        
        Image pokeImage = new Image(poke.front_img);
        
        g.setColor(Color.black);
        g.drawRoundRect(15, 8, 484, 324, 10);
        g.fillRoundRect(15, 8, 484, 324, 10);        
        pro1.draw(17,10,2);
        
        
        font3.drawString(35,48,"No." + poke.id ,Color.white);
        font3.drawString(35,205,poke.name,Color.white);
        font3.drawString(35,235,"/"+poke.name,Color.white);
            
        
        font3.drawString(35,265,"Lv"+poke.level,Color.white);
        pokeImage.draw(-5,0,2);
           
        if(profileState == 1){   
            font3.drawString(200,75,"OT/",Color.white);
            font3.drawString(245,75,p.name,Color.blue);
            font3.drawString(400,75,"IDNo12008",Color.white);
        
            font3.drawString(200,105,"EXP:",Color.black);
            font3.drawString(240, 105, ""+poke.exp,Color.black);
            //draw the type of the mon in box form
            font3.drawString(200,160,poke.ability1,Color.white);
            
            font3.drawString(200,250,poke.nature + " nature.",Color.red);
            
        }else if(profileState == 2) {
            prohead2.draw(192,10,2);
            pro2.draw(177,52,2);
        } else if(profileState == 3) {
            prohead3.draw(192,10,2);
            pro3.draw(17,52,2);
        }
    }
    
    public void drawBagMenu(Graphics g) throws SlickException {
        drawPauseMenu(g);
        
        Image bagMenu = new Image("data/tiles/bagmenumain.png");
        g.setColor(Color.black);
        g.drawRoundRect(15, 8, 484, 324, 10);
        g.fillRoundRect(15, 8, 484, 324, 10);
        bagMenu.draw(17,10,2);
    }
    
    public void drawTrainerCard(Graphics g) throws SlickException {
        drawPauseMenu(g);
        
        Image background = new Image("data/tiles/trainercardbackground.png");
        Image trainercard = new Image("data/tiles/trainercardbase.png");
        Image trainer = new Image("data/tiles/fullsizetrainer.png");
        
        g.setColor(Color.black);
        g.drawRoundRect(15, 8, 484, 324, 10);
        g.fillRoundRect(15, 8, 484, 324, 10);
        background.draw(17,10,2);
        trainercard.draw(30,10,2);
        trainer.draw(343,115,2);
        
        font2.drawString(308, 28, "IdNo.61225", Color.white);
        font2.drawString(73, 67, "NAME: " + p.name, Color.black);
        font2.drawString(73, 124, "MONEY: ",Color.black);
        font2.drawString(73, 157, "POKeDEX: ", Color.black);
        font2.drawString(73, 189, "TIME:", Color.black);
        
        font2.drawString(250, 124, "$" + p.money, Color.black);
        font2.drawString(250, 157, "" + p.pokedex.caughtnum, Color.black);
        font2.drawString(250, 189, "3:47", Color.black);
        
        if(p.badge1) {
            Image badge1 = new Image("data/tiles/badge1.png");
            badge1.draw(84,256,2);
        }
        if(p.badge2) {
            Image badge2 = new Image("data/tiles/badge2.png");
            badge2.draw(135,256,2);
        }
        if(p.badge3) {
            Image badge3 = new Image("data/tiles/badge3.png");
            badge3.draw(179,254,2);
        }
        if(p.badge4) {
            Image badge4 = new Image("data/tiles/badge4.png");
            badge4.draw(226,254,2);
        }
        if(p.badge5) {
            Image badge5 = new Image("data/tiles/badge5.png");
            badge5.draw(276,256,2);
        }
        if(p.badge6) {
            Image badge6 = new Image("data/tiles/badge6.png");
            badge6.draw(324,256,2);
        }
        if(p.badge7) {
            Image badge7 = new Image("data/tiles/badge7.png");
            badge7.draw(372,255,2);
        }
        if(p.badge8) {
            Image badge8 = new Image("data/tiles/badge8.png");
            badge8.draw(419,254,2);
        }
    }
    
    public void drawPokedex(Graphics g) throws SlickException {
        drawPauseMenu(g);
        
        Image pokedexbackground = new Image("data/tiles/pokedexmain.png");
        Image pokedexinfopage = new Image("data/tiles/pokedexinfo.png");
        
        g.setColor(Color.red);
        g.drawRoundRect(15, 8, 484, 324, 10);
        g.fillRoundRect(15, 8, 484, 324, 10);
        
        pokedexbackground.draw(17,10,2);
    }
    
    public void drawSaveMenu(Graphics g) throws SlickException {
        drawPauseMenu(g);
        
        g.setColor(Color.white);
        g.drawRoundRect(15, 8, 484, 324, 10);
        g.fillRoundRect(15, 8, 484, 324, 10);
        
        font.drawString(150, 15, "PEWTER CITY", Color.blue);
        font2.drawString(70, 85, "PLAYER", Color.black);
        font2.drawString(70, 135, "BADGES", Color.black);
        font2.drawString(70, 185, "POKeDEX", Color.black);
        font2.drawString(70, 235, "TIME", Color.black);
        
        font2.drawString(370, 85, p.name, Color.black);
        font2.drawString(370, 135, "8", Color.black);
        font2.drawString(370, 185, "" + p.pokedex.caughtnum, Color.black);
        font2.drawString(370, 235, "3:47", Color.black);
    }
    
    public void drawInteract(Graphics g, String talk) throws SlickException {
        g.setColor(Color.white);
        g.drawRoundRect(15, 8, 730, 100, 10);
        g.fillRoundRect(15, 8, 730, 100, 10);
        font.drawString(40, 15, talk, Color.black);
    }
        
    public void drawOptionMenu(Graphics g) throws SlickException {
        drawPauseMenu(g);
        
        g.setColor(Color.white);
        g.drawRoundRect(15, 8, 484, 324, 10);
        g.fillRoundRect(15, 8, 484, 324, 10);
        
        font.drawString(140, 15, "NOT AVAILABLE", Color.red);
        font2.drawString(60, 85, "in this current version (v.1.1)", Color.red);
    }
    
    public void drawInteraction(Graphics g) throws SlickException {
        if(null != interactable[(int)playerX][(int)playerY]) switch (interactable[(int)playerX][(int)playerY]) {
            case "mom":
                finalInteractCount = 5;
                String[] momtalk = new String[6];
                momtalk[1] = "MOM: BLAHBLAHBLAHBLAHBLAHBLAHBLAH";
                momtalk[2] = "MOM: BLAHBLAHDISHESBLAHBLAHBLAHBL";
                momtalk[3] = "MOM: BLAHROOMBLAHBLAHBLAHBLAHBLAH";
                momtalk[4] = "MOM: BLAHBLAHBLAHBLAHBLAHBLAHBLAH";
                momtalk[5] = p.name + " got bored and left...";
                if(interactCount <= finalInteractCount) {
                    drawInteract(g, momtalk[interactCount]);
                } else {
                    interactCount = 1;
                    GAMESTATE = "PLAY";
                }
            break;
            case "rival":
                finalInteractCount = 4;
                String[] rivaltalk = new String[5];
                rivaltalk[1] = "MATT: Wats up, " +p.name+ "?";
                rivaltalk[2] = "MATT: How is your "+p.main.name+" doing?";
                rivaltalk[3] = "MATT: Level " + p.main.level + "???";
                if(p.main.level < 10) {
                    rivaltalk[4] = "MATT: Get good, "+p.name+" you n00b!";
                } else if(p.main.level > 10 && p.main.level < 30) {
                    rivaltalk[4] = "MATT: Getting good, " + p.name +".";
                } else if(p.main.level > 30) {
                    rivaltalk[4] = "MATT: Gym leader will get rekt!!";
                }
                if(interactCount <= finalInteractCount) {
                    drawInteract(g, rivaltalk[interactCount]);
                } else {
                    interactCount = 1;
                    GAMESTATE = "PLAY";
                }
            break;
            case "bill":
                finalInteractCount = 2;
                String[] billtalk = new String[3];
                billtalk[1] = "BILL: Heya, Im Bill, the pokemaniac!";
                billtalk[2] = "BILL: Im very busy, please leave.";
                if(interactCount <= finalInteractCount) {
                    drawInteract(g, billtalk[interactCount]);
                } else {
                    interactCount = 1;
                    GAMESTATE = "PLAY";
                }
            break;
            case "nurse":
                finalInteractCount = 3;
                String[] nursetalk = new String[4];
                nursetalk[1] = "NURSE: Hi, welcome to the PokeCenter.";
                nursetalk[2] = "NURSE: Imma health you up, " + p.name + ".";
                nursetalk[3] = "NURSE: Hope you have a nice day!";
                if(interactCount <= finalInteractCount) {
                    drawInteract(g, nursetalk[interactCount]);
                    if(interactCount == 2) {
                        MonsterGameplay.healTeam(p);
                    }
                } else {
                    interactCount = 1;
                    GAMESTATE = "PLAY";
                }
            break;
            case "martman":
                finalInteractCount = 1;
                if(interactCount <= finalInteractCount) {
                    drawInteract(g, "CLERK: I dont have anything in stock.");
                } else {
                    interactCount = 1;
                    GAMESTATE = "PLAY";
                }
            break;
            case "oldman":
                finalInteractCount = 6;
                String[] oldmantalk = new String[7];
                oldmantalk[1] = "ELDER: Yo "+ p.name +"! Looking tough!";
                oldmantalk[2] = "ELDER: I can help train " + p.main.name+".";
                oldmantalk[3] = "ELDER: Just because you talk to me.";
                oldmantalk[4] = "ELDER: Such a nice thing to do.";
                oldmantalk[5] = "*trains " + p.main.name + "*";
                oldmantalk[6] = "ELDER: Done! I believe in you, "+p.name+".";
                if(interactCount <= finalInteractCount) {
                    drawInteract(g, oldmantalk[interactCount]);
                } else {
                    addExpManually(p.main, 500);
                    interactCount = 1;
                    GAMESTATE = "PLAY";
                }
            break;
            case "prof":
                finalInteractCount = 1;
                if(interactCount <= finalInteractCount) {
                    drawInteract(g, "PROF OAK: Whats good, little man.");
                } else {
                    interactCount = 1;
                    GAMESTATE = "PLAY";
                }
            break;
            case "gymleader":
                finalInteractCount = 4;
                String[] gymtalk = new String[5];
                gymtalk[1] = "STIG: BWAHAHA! Welcome to my gym!";
                gymtalk[2] = "STIG: I am OP af, compared to you.";
                gymtalk[3] = "STIG: So, have you come to fail?";
                gymtalk[4] = "STIG: FOOL! A GRAVE MISTAKE!";
                if(interactCount <= finalInteractCount) {
                    drawInteract(g, gymtalk[interactCount]);
                    if(interactCount == 4) {
                        GAMESTATE = "BATTLE";
                    }
                } else {
                    interactCount = 1;
                    GAMESTATE = "PLAY";
                }
            break;
        }
    }
    
    public void addExpManually(Pokemon poke, int amount) {
        poke.exp += amount;
        MonsterGameplay.check(poke);
    }
    
    public void drawBattle(Graphics g) throws SlickException {
        Image battleGround = new Image("data/tiles/gymBattleground.png");
        Image backgroundTextBox = new Image("data/tiles/backgroundTextBox.png");
        Image selectTextBox = new Image("data/tiles/moveTypeBoxSelect.png");
        Image pointer = new Image("data/tiles/pointer.png");
        g.setColor(Color.black);
        g.drawRoundRect(15, 8, 484, 324, 10);
        g.fillRoundRect(15, 8, 484, 324, 10);
        battleGround.draw(17,10,2);
        
        backgroundTextBox.draw(17, 234, 2);
        
        //selectTextBox
        
        selectTextBox.draw(257,234,2);
        if(battleSelectTypeCount == 1) {
            pointer.draw(272,259,2);
        } else if(battleSelectTypeCount == 2) {
            pointer.draw(382,259,2);
        }else if(battleSelectTypeCount == 3) {
            pointer.draw(272,289,2);
        }else if(battleSelectTypeCount == 4) {
            pointer.draw(382,289,2);
        }
    }
    
    public void drawSelectMove(Graphics g) throws SlickException {
        drawBattle(g);
        Image selectMoveBox = new Image("data/tiles/moveSelectBox.png");
        selectMoveBox.draw(17, 234, 2);
        
        //moveSelectCount
        
        font3.drawString(47, 254, p.main.move1.name, Color.black);
        font3.drawString(182, 254, p.main.move2.name, Color.black);
        font3.drawString(47, 289, p.main.move3.name, Color.black);
        font3.drawString(182, 289, p.main.move4.name, Color.black);
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
        if(null != LOCATION)switch (LOCATION) {
            case "HOME":
                mom.draw(7 * TILE_SIZE, 4 * TILE_SIZE);
                break;
            case "POKECENTER":
                nurse.draw(7*TILE_SIZE,24);
                break;
            case "POKEMART":
                martman.draw(2*TILE_SIZE,2*TILE_SIZE);
                break;
            case "RIVALHOME":
                rival.draw(4*TILE_SIZE,4*TILE_SIZE);
                break;
            case "GYM":
                gymleader.draw(6*TILE_SIZE,5*TILE_SIZE);
                break;
            case "LABF1":
                prof.draw(20*TILE_SIZE,4*TILE_SIZE);
                break;
            case "PEWTER":
                bill.draw(7*TILE_SIZE,4*TILE_SIZE);
                oldman.draw(34*TILE_SIZE,16*TILE_SIZE);
                break;
        }
        sprite.draw(playerX * TILE_SIZE, playerY * TILE_SIZE, Color.white);
        g.resetTransform(); 
        
        if(null != GAMESTATE) switch (GAMESTATE) {
            case "PLAY":
                break;
            case "INTERACTING":
                drawInteraction(g);
                break;
            case "PAUSE":
                drawPauseMenu(g);
                break;
            case "POKEMON":
                drawPokeMenu(g);
                break;
            case "POKEMENUMENU":
                drawPokeMenuMenu(g);
                break;
            case "POKEPROFILE":
                Pokemon poke = null;
                if(pokeMenuState == 1) {
                    poke = p.main;
                } else if(pokeMenuState == 2) {
                    poke = p.p2;
                } else if(pokeMenuState == 3) {
                    poke = p.p3;
                } else if(pokeMenuState == 4) {
                    poke = p.p4;
                } else if(pokeMenuState == 5) {
                    poke = p.p5;
                } else if(pokeMenuState == 6) {
                    poke = p.p6;
                }   
                drawPokeProfile(poke,g);
                break;
            case "BAG":
                drawBagMenu(g);
                break;
            case "TRAINERCARD":
                drawTrainerCard(g);
                break;
            case "OPTIONS":
                drawOptionMenu(g);
                break;
            case "SAVE":
                drawSaveMenu(g);
                break;
            case "POKEDEX":
                drawPokedex(g);
                break;
            case "BATTLE":
                drawBattle(g);
                break;
            case "SELECTMOVE":
                drawSelectMove(g);
                break;
        }
    }
}
