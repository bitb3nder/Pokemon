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
		//database.put(001, "001|Bulbasaur|Grass|Poison|002|16|null|Overgrow|null|100|null|85|45|2'04\"|15.2|x|64|med_slow|1_spatt|data/Pokemon/001.png|data/Pokemon/001b.png|data/Pokemon/001anim.png|001_foot.png|green|1|0|0|0|0|45|49|49|65|65|45|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|100|100|ok|70|-|-|-|-|Tackle,1:Growl,4:Leech Seed,7:Vine Whip,10:Poison Powder,13:Sleep Powder,13:Take Down,16:Razor Leaf,20:Sweet Scent,22:Growth,25:Double-Edge,27:Worry Seed,30:Synthesis,33:Seed Bomb,35|06,09,10,11,17,19,21,22,27,32,36,42,43,44,45,53,58,70,75,78,82,83,86,87,90,501,504,506|The seed on its back is filled with nutrients. The seed grows steadily larger as its body grows.|x|x");
		//database.put(002, "002|Ivysaur|Grass|Poison|003|32|null|Overgrow|null|100|null|85|45|3'03\"|28.7|x|141|med_slow|1_spatt&1_spdef|002.png|002b.png|002_world.png|002_foot.png|green|1|0|0|0|0|60|62|63|80|80|60|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|100|100|ok|70|-|-|-|-|Tackle|Growl|Leech Seed|Vine Whip|Poison Powder|Sleep Powder|Take Down|Razor Leaf|Sweet Scent|Growth|Double-Edge|Worry Seed|Synthesis|Solar Beam|null|1,1,1,9,13,13,15,20,23,28,31,36,39,44|06,09,10,11,16,17,19,20,21,22,27,32,36,42,44,45,48,49,53,70,75,78,86,87,88,90,94,96,501,504,506|Exposure to sunlight adds to its strength. Sunlight also makes the bud on its back grow larger.|x|x");
                database.put(001, "001|Foliat|Grass|null|002|16|null|Overgrow|null|100|null|85|45|1'0\"|4.6|x|28|med_slow|1_speed|data/Pokemon/001.png|data/Pokemon/001b.png|data/Pokemon/001anim.png|001foot.png|green|0|0|0|0|0|42|42|45|68|46|75|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|100|100|ok|70|-|-|-|-|Tackle,1:Growl,4:Absorb,7:Peck,10:Mega Drain,13:Pluck,16:Grass Whistle,19:Leaf Tornado,22:Agility,25:Leaf Shield,28:Synthesis,31:Wind Shear,34:Drill Peck,37:Giga Drain,40:Solar Beam,49|06,09,10,11,17,19,21,22,27,32,36,42,43,44,45,53,58,70,75,78,82,83,86,87,90,501,504,506|Although Foliat is unable to fly, it still has an extremely high metabolism. It's often found feeding on sugar-rich nectar and sap found in plants.|x|x");             
                //database.put(002, "002");
                //database.put(003, "003");
                //database.put(004, "004|Kidling|Fire|null|005|16|null|Blaze|null|100|null|85|45|1'4\"|11.5|null|28|med_slow|1_att|Pokemon/004.png|Pokemon/004b.png|Pokemon/004anim.png|004foot.png|orange|0|0|0|0|0|43|63|47|57|40|59|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|100|100|ok|70|-|-|-|-|Tackle,1|Tail Whip,4|Ember,7|Headbutt,10|Flame Charge,13|Mudslide,16|Bulk Up,19|Brace,22|Blaze Kick,25|Battering Ram,28|Rock Climb,31|Swagger,34|Flamethrower,37|Standoff,40|Flare Blitz,49|Kidling headbutts humans and Pokémon as a sign of affection. When its head makes contact, even gently, the flame on its tail flares up briefly.|x|x");
                //database.put(005, "005");
                //database.put(006, "006");
                //database.put(007, "007|Aguade|Water|null|008|16|null|Torrent|null|100|null|85|45|1'8\"|20.9|null|28|med_slow|1_def|Pokemon/007.png|Pokemon/007b.png|Pokemon/007anim.png|007foot.png|blue|0|0|0|0|0|55|55|63|45|45|45|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|100|100|ok|70|-|-|-|-|Scratch,1|Tail Whip,4|Water Gun,7|Bite,10|Water Pulse,13|Bide,16|Brine,19|Cheap Shot,22|Take Down,25|Salt Crash,28|Brace,31|Aqua Tail,34|Hammer Arm,37|Quick Guard,40|Hydro Pump,49|Aguade's face and hands are armored with rock-hard salt. It uses its fists like hammers and its head to block incoming attacks.|x|x");
                //database.put(008, "008");
                //database.put(009, "009");
                //database.put(010, "010");
                
		String data = database.get(id);
		return data;
	}
}