import java.util.Map;
import java.util.HashMap;


/*
 * Dictionary containing all of the necessary data to create a pokemon.
 * Simply calling the name of the monster returns a string will all of
 * its data (seperated by pipes).
 */
public class monsterDatabase {

	public static String get_datastring(int id) {
		Map<Integer, String> database = new HashMap<Integer, String>();
		//database.put(000, "000|--|--|--|000|0|null|--|0|null|0|0|0|0|x|0|x|x|000.png|000b.png|000_world.png|");
		database.put(001, "001|Bulbasaur|Grass|Poison|002|16|null|Overgrow|null|100|null|85|45|2'04\"|15.2|x|64|med_slow|1_spatt|001.png|001b.png|001_world.png|001_foot.png|green|1|0|0|0|0|45|49|49|65|65|45|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|100|100|ok|70|-|-|-|-|Tackle|Growl|Leech Seed|Vine Whip|Poison Powder|Sleep Powder|Take Down|Razor Leaf|Sweet Scent|Growth|Double-Edge|Worry Seed|Synthesis|Seed Bomb|null|1,3,7,9,13,13,15,19,21,25,27,31,32,37,0|06,09,10,11,17,19,21,22,27,32,36,42,43,44,45,53,58,70,75,78,82,83,86,87,90,501,504,506|The seed on its back is filled with nutrients. The seed grows steadily larger as its body grows.|x");
		database.put(002, "002|Ivysaur|Grass|Poison|003|32|null|Overgrow|null|100|null|85|45|3'03\"|28.7|x|141|med_slow|1_spatt&1_spdef|002.png|002b.png|002_world.png|002_foot.png|green|1|0|0|0|0|60|62|63|80|80|60|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|100|100|ok|70|-|-|-|-|Tackle|Growl|Leech Seed|Vine Whip|Poison Powder|Sleep Powder|Take Down|Razor Leaf|Sweet Scent|Growth|Double-Edge|Worry Seed|Synthesis|Solar Beam|null|1,1,1,9,13,13,15,20,23,28,31,36,39,44|06,09,10,11,16,17,19,20,21,22,27,32,36,42,44,45,48,49,53,70,75,78,86,87,88,90,94,96,501,504,506|Exposure to sunlight adds to its strength. Sunlight also makes the bud on its back grow larger.|x");

		String data = database.get(id);
		return data;
	}
}