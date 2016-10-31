import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

//tiledlogin ben.federline@archer

public class BlockMap {
	public static TiledMap tmap;
	public static int mapWidth;
	public static int mapHeight;
	private int square[] = {1,1,15,1,15,15,1,15}; // offset for the polygon surrounding tiles
	public static ArrayList<Object> entities;

	public BlockMap(String ref) throws SlickException {
		entities = new ArrayList<Object>();
		tmap = new TiledMap(ref, "data/tiles"); // Specify a folder with resource card
		mapWidth = tmap.getWidth() * tmap.getTileWidth(); // calculate the map width in pixels
		mapHeight = tmap.getHeight() * tmap.getTileHeight(); // calculate the height of the map in pixels
	}
}