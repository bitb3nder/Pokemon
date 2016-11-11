import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benfederline
 */
public class Play extends Test{
    //The official Test Class of the Pokemon game to this point
    
    public Play(Player x) {
        super(x);
    }
    
    public static void main(String[] args) throws SlickException {
        Player player = new Player();
        player.name = "BEN";
        player.main = new Pokemon(001);
        //player.p2 = new Pokemon(004);
        // player.p3 = new Pokemon(007);
        
        try {
            container = new AppGameContainer(new Test(player));
            container.setDisplayMode(768,640,false);
            container.start();
        } catch (SlickException e) {
        }
    }
}
