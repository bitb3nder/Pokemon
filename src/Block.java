import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

// this class create a poly which answer for collision
public class Block  {
	public Polygon poly;
	public Block(int x, int y, int test[],String type) {
        poly = new Polygon(new float[]{
            x+test[0], y+test[1], //1,1
            x+test[2], y+test[3], //15,1
            x+test[4], y+test[5], //15,15
            x+test[6], y+test[7], //1,15
        });
	}

	public void update(int delta) {
	}

	public void draw(Graphics g) {
		g.draw(poly); //drawing poly
	}
}


