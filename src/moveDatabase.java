import java.util.Map;
import java.util.HashMap;

/*
 * Dictionary containing all of the necessary data to create a attack move
 */

public class moveDatabase {

	public static String get_move(String move) {
		Map<String, String> database = new HashMap<String, String>();
		//currently only the moves learned by Bulbasaur/Ivysaur TM/HM not implemented

		/* note that while there are two types of status moves, stat altering and
		 * status altering, to avoid confusion in future methods, they will be 
		 * called Stat and Condition moves. SO this means that while both Growl and
		 * Sleep Powder are both considered "status" moves, Growl is a stat and 
		 * Sleep Powder is a condition. 
		 */

		/* due to the growing complexity of moves and what moves are able to do, some
		 * amount of hardcoding must be done. if a move saps life from the opponent, 
		 * moves such as leech seed, it should be marked "drain" and the amount should 
		 * be the number at the bottom of the eqn. So leech seed takes 1/8 life every turn.
		 * Amount should be set to 8. The amount drained from the opponent is then given 
		 * to the user. 
		 *
		 * IF there is a move (Mega Drain) that does damage and drains half the damage, ect.
		 * it should be marked "hit_drain" and the amount should be the number that the total
		 * damage is divided by to obtain the HP regained. So if Mega Drain does 50 damage, 
		 * amount should be set to 2 because the user regains 1/2 of health taken, so 25
		 * HP should be given to the user. 
		 */

		database.put("-", "-|-|-|0|0|0|0|0|0|0|-|0|-|-|0|0|-");
		database.put("Tackle", "Tackle|Normal|Physical|1|1|50|100|35|35|000|None|0|None|None|0|0|A physical attack in which the user charges, full body, into the foe.");
		database.put("Growl", "Growl|Normal|Stat|0|1|0|100|40|40|000|None|0|Foe|att|-1|0|The user growls cutely, making the foe lower its attack stat.");
		database.put("Leech Seed", "Leech Seed|Normal|Condition|");
		database.put("Vine Whip", "");
		database.put("Poison Powder", "");
		database.put("Sleep Powder", "");
		database.put("Take Down", "");
		database.put("Razor Leaf", "");
		database.put("Sweet Scent", "");
		database.put("Growth", "");
		database.put("Double-Edge", "");
		database.put("Worry Seed", "");
		database.put("Synthesis", "");
		database.put("Seed Bomb", "");
		database.put("Solar Beam", "");

		String data = database.get(move);
		return data;
	}
}