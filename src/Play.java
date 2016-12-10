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
        //create a test player
        Player player = new Player();
                
        /*give the player a few things he will obtain in the story
         *to prove that the features of the game work
         */
        player.name = "BEN";
        player.main = new Pokemon(001);
        Pokemon.setNewPokemon(player.main, 3);
        
        player.pokedex.seennum +=3;
        player.pokedex.caughtnum += 3;
        
        player.money = 1000;
        player.badge1 = true;
        player.badge2 = true;
        player.badge3 = true;
        player.badge4 = true;
        player.badge5 = true;
        player.badge6 = true;
        player.badge7 = true;
        player.badge8 = true;
        
        
        try {
            container = new AppGameContainer(new Test(player));
            container.setDisplayMode(768,640,false);
            container.start();
        } catch (SlickException e) {
        }
    }
}
