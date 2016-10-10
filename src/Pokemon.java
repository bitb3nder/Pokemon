import java.util.Random;

public class Pokemon extends monsterDatabase {
	//all of the global variables that make up a Pokemon
	public int id, evolve, evolve_lvl, ability1_percent, percent_male, catch_rate, exp_yield, level, exp, exp_lvl_start, exp_lvl_end, exp_to_next, happiness;
	public String name, type1, type2, evolve_cond, ability1, ability2, gender, height, weight, nature, lvl_rate, ev_yield, color, current_status;
	public String front_img, back_img, footprint, overworld_img;
	public double base_hp, base_att, base_def, base_spatt, base_spdef, base_speed, hp, att, def, spatt, spdef, speed;
	public double hp_ev, att_ev, def_ev, spatt_ev, spdef_ev, speed_ev, hp_iv, att_iv, def_iv, spatt_iv, spdef_iv, speed_iv;
	public double current_hp, evasion, accuracy;
	public Move move1, move2, move3, move4; 
	public String unlearned1, unlearned2, unlearned3, unlearned4, unlearned5, unlearned6, unlearned7, unlearned8, unlearned9, unlearned10, unlearned11, unlearned12, unlearned13, unlearned14, unlearned15;
	public String learnset[];
	public String learn_tm_hm[];
	public String pokedex_entry, nickname;

	//creates the pokemon object
	public Pokemon(int id) {
		//Use "id number" to gather info from dictionary
		String info = get_datastring(id);
		//Split line on the "\" char into array of Strings
		//79
		String data[] = info.split("\\|");

		//Set Basic information
		this.id = Integer.parseInt(data[0]);
		this.name = data[1];
		this.type1 = data[2];
		this.type2 = data[3];
		this.evolve = Integer.parseInt(data[4]);
		this.evolve_lvl = Integer.parseInt(data[5]);
		this.evolve_cond = data[6];
		this.ability1 = data[7];
		this.ability2 = data[8];
		this.ability1_percent = Integer.parseInt(data[9]);
		this.gender = data[10];
		this.percent_male = Integer.parseInt(data[11]);
		this.catch_rate = Integer.parseInt(data[12]);
		this.height = data[13];
		this.weight = data[14];
		this.nature = data[15];
		this.exp_yield = Integer.parseInt(data[16]);
		this.lvl_rate = data[17];
		this.ev_yield = data[18];
		this.front_img = data[19];
		this.back_img = data[20];
		this.overworld_img = data[21];
		this.footprint = data[22];
		this.color = data[23];

		//Set Stat Info & Placeholders
		this.level = Integer.parseInt(data[24]);
		this.exp = Integer.parseInt(data[25]);
		this.exp_lvl_start = Integer.parseInt(data[26]);
		this.exp_lvl_end = Integer.parseInt(data[27]);
		this.exp_to_next = Integer.parseInt(data[28]);

		this.base_hp = Double.parseDouble(data[29]);
		this.base_att = Double.parseDouble(data[30]);
		this.base_def = Double.parseDouble(data[31]);
		this.base_spatt = Double.parseDouble(data[32]);
		this.base_spdef = Double.parseDouble(data[33]);
		this.base_speed = Double.parseDouble(data[34]);

		this.hp = Double.parseDouble(data[35]);
		this.att = Double.parseDouble(data[36]);
		this.def = Double.parseDouble(data[37]);
		this.spatt = Double.parseDouble(data[38]);
		this.spdef = Double.parseDouble(data[39]);
		this.speed = Double.parseDouble(data[40]);

		this.hp_ev = Double.parseDouble(data[41]);
		this.att_ev = Double.parseDouble(data[42]);
		this.def_ev = Double.parseDouble(data[43]);
		this.spatt_ev = Double.parseDouble(data[44]);
		this.spdef_ev = Double.parseDouble(data[45]);
		this.speed_ev = Double.parseDouble(data[46]);

		this.hp_iv = Double.parseDouble(data[47]);
		this.att_iv = Double.parseDouble(data[48]);
		this.def_iv = Double.parseDouble(data[49]);
		this.spatt_iv = Double.parseDouble(data[50]);
		this.spdef_iv = Double.parseDouble(data[51]);
		this.speed_iv = Double.parseDouble(data[52]);

		//Battle Specific 
		this.current_hp = Double.parseDouble(data[53]);
		this.evasion = Double.parseDouble(data[54]);
		this.accuracy = Double.parseDouble(data[55]);
		this.current_status = data[56];

		//Other
		this.happiness = Integer.parseInt(data[57]);

		//Learned Move Slots
		this.move1 = new Move(data[58]);
		this.move2 = new Move(data[59]);
		this.move3 = new Move(data[60]);
		this.move4 = new Move(data[61]);

		//Unlearned Moves & levels
		this.unlearned1 = data[62];
		this.unlearned2 = data[63];
		this.unlearned3 = data[64];
		this.unlearned4 = data[65];
		this.unlearned5 = data[66];
		this.unlearned6 = data[67];
		this.unlearned7 = data[68];
		this.unlearned8 = data[69];
		this.unlearned9 = data[70];
		this.unlearned10 = data[71];
		this.unlearned11 = data[72];
		this.unlearned12 = data[73];
		this.unlearned13 = data[74];
		this.unlearned14 = data[75];
		this.unlearned15 = data[76];

		this.learnset = data[77].split(",");

		//Learnable Tms & Hms 
		this.learn_tm_hm = data[78].split(",");

		this.pokedex_entry = data[79];
		this.nickname = data[80];
	}

	//random number generator
	public static int getRandom(int low, int high){
		Random r = new Random();
		int range = high - low + 1;
		int randomNum =  r.nextInt(range) + low;
		return randomNum;
	}

	//Sets ability of new Pokemon at random
	public static void setAbility(Pokemon poke) {
		int x = getRandom(0,100);
		if(x < poke.ability1_percent) {
			poke.ability1 = poke.ability1;
		} else {
			poke.ability1 = poke.ability2;
		}
	}

	//Sets gender of new Pokemon at random
	public static void setGender(Pokemon poke) {
		int x = getRandom(0,100);
		if(x > poke.percent_male) {
			poke.gender = "Female";
		} else {
			poke.gender = "Male";
		}
	}

	//Sets nature of new Pokemon at random
	public static void setNature(Pokemon poke) {
		int x = getRandom(1,25);
		if(x == 1) {
			poke.nature = "Hardy";
		} else if(x == 2) {
			poke.nature = "Lonely";
		} else if(x == 3) {
			poke.nature = "Adamant";
		} else if(x == 4) {
			poke.nature = "Naughty";
		} else if(x == 5) {
			poke.nature = "Brave";
		} else if(x == 6) {
			poke.nature = "Bold";
		} else if(x == 7) {
			poke.nature = "Docile";
		} else if(x == 8) {
			poke.nature = "Impish";
		} else if(x == 9) {
			poke.nature = "Lax";
		} else if(x == 10) {
			poke.nature = "Relaxed";
		} else if(x == 11) {
			poke.nature = "Modest";
		} else if(x == 12) {
			poke.nature = "Mild";
		} else if(x == 13) {
			poke.nature = "Bashful";
		} else if(x == 14) {
			poke.nature = "Rash";
		} else if(x == 15) {
			poke.nature = "Quiet";
		} else if(x == 16) {
			poke.nature = "Calm";
		} else if(x == 17) {
			poke.nature = "Gentle";
		} else if(x == 18) {
			poke.nature = "Careful";
		} else if(x == 19) {
			poke.nature = "Quirky";
		} else if(x == 20) {
			poke.nature = "Sassy";
		} else if(x == 21) {
			poke.nature = "Timid";
		} else if(x == 22) {
			poke.nature = "Hasty";
		} else if(x == 23) {
			poke.nature = "Jolly";
		} else if(x == 24) {
			poke.nature = "Naive";
		} else {
			poke.nature = "Serious";
		}
	}

	//Sets IV's of new Pokemon at random
	public static void setIv(Pokemon poke) {
		poke.hp_iv = getRandom(0, 31);
		poke.att_iv = getRandom(0, 31);
		poke.def_iv = getRandom(0, 31);
		poke.spatt_iv = getRandom(0, 31);
		poke.spdef_iv = getRandom(0, 31);
		poke.speed_iv = getRandom(0, 31);
	}

	//Sets the stats for the given information
	public static void setStats(Pokemon poke) {
		//all stats are calculated regarless of the pokemons nature.
		poke.hp    = (((2 * poke.base_hp + poke.hp_iv + (poke.hp_ev/4))*poke.level)/100) + poke.level + 10;
		poke.att   = (((2 * poke.base_att + poke.att_iv + (poke.att_iv/4))*poke.level)/100) + 5;
		poke.def   = (((2 * poke.base_def + poke.def_iv + (poke.def_iv/4))*poke.level)/100) + 5;
		poke.spatt = (((2 * poke.base_spatt + poke.spatt_iv + (poke.spatt_iv/4))*poke.level)/100) + 5;
		poke.spdef = (((2 * poke.base_spdef + poke.spdef_iv + (poke.spdef_iv/4))*poke.level)/100) + 5;
		poke.speed = (((2 * poke.base_speed + poke.speed_iv + (poke.speed_iv/4))*poke.level)/100) + 5;

		/*
		 * Here, the nature will positively affect one stat and negatively 
		 * affect another. multiply by 1.1 or .9 depending on the situation.
		 * In case you're really digging into this and notice that there are
		 * 5 less natures, that is because there are 5 'neutral' natures, 
		 * that add and take away from the same stat, doing nothing. 
		 */
		if(poke.nature == "Lonely") {
			poke.att = poke.att * 1.1;
			poke.def = poke.def * .9;
		} else if(poke.nature == "Adamant") {
			poke.att = poke.att * 1.1;
			poke.spatt = poke.spatt * .9;
		} else if(poke.nature == "Naughty") {
			poke.att = poke.att * 1.1;
			poke.spdef = poke.spdef * .9;
		} else if(poke.nature == "Brave") {
			poke.att = poke.att * 1.1;
			poke.speed = poke.speed * .9;
		} else if(poke.nature == "Bold") {
			poke.def = poke.def * 1.1;
			poke.att = poke.att * .9;
		} else if(poke.nature == "Impish") {
			poke.def = poke.def * 1.1;
			poke.spatt = poke.spatt * .9;
		} else if(poke.nature == "Lax") {
			poke.def = poke.def * 1.1;
			poke.spdef = poke.spdef * .9;
		} else if(poke.nature == "Relaxed") {
			poke.def = poke.def * 1.1;
			poke.speed = poke.speed * .9;
		} else if(poke.nature == "Modest") {
			poke.spatt = poke.spatt * 1.1;
			poke.att = poke.att * .9;
		} else if(poke.nature == "Mild") {
			poke.spatt = poke.spatt * 1.1;
			poke.def = poke.def * .9;
		} else if(poke.nature == "Rash") {
			poke.spatt = poke.spatt * 1.1;
			poke.spdef = poke.spdef * .9;
		} else if(poke.nature == "Quiet") {
			poke.spatt = poke.spatt * 1.1;
			poke.speed = poke.speed * .9;
		} else if(poke.nature == "Calm") {
			poke.spdef = poke.spdef * 1.1;
			poke.att = poke.att * .9;
		} else if(poke.nature == "Gentle") {
			poke.spdef = poke.spdef * 1.1;
			poke.def = poke.def * .9;
		} else if(poke.nature == "Careful") {
			poke.spdef = poke.spdef * 1.1;
			poke.spatt = poke.spatt * .9;
		} else if(poke.nature == "Sassy") {
			poke.spdef = poke.spdef * 1.1;
			poke.speed = poke.speed * .9;
		} else if(poke.nature == "Timid") {
			poke.speed = poke.speed * 1.1;
			poke.att = poke.att * .9;
		} else if(poke.nature == "Hasty") {
			poke.speed = poke.speed * 1.1;
			poke.def = poke.def * .9;
		} else if(poke.nature == "Jolly") {
			poke.speed = poke.speed * 1.1;
			poke.spatt = poke.spatt * .9;
		} else if(poke.nature == "Naive") {
			poke.speed = poke.speed * 1.1;
			poke.spdef = poke.spdef * .9;
		} else {
			//Do nothing 
		}

		if(poke.lvl_rate == "fast") {
			poke.exp_lvl_start = (4 * (int)Math.pow(poke.level, 3))/5;
			poke.exp_lvl_end = (4 * (int)Math.pow((poke.level + 1), 3))/5;
		} else if (poke.lvl_rate == "med_fast") {
			poke.exp_lvl_start = (int)Math.pow(poke.level, 3);
			poke.exp_lvl_end = (int)Math.pow(poke.level + 1, 3);
		} else if (poke.lvl_rate == "med_slow") {
			poke.exp_lvl_start = ((6* (int)Math.pow(poke.level, 3))/5) - (15 * (int)Math.pow(poke.level, 2)) + (100 * poke.level) - 140;
			poke.exp_lvl_end = ((6* (int)Math.pow(poke.level + 1, 3))/5) - (15 * (int)Math.pow(poke.level + 1, 2)) + (100 * poke.level + 1) - 140;
		} else if (poke.lvl_rate == "slow") {
			poke.exp_lvl_start = (5 * (int)Math.pow(poke.level, 3))/4;
			poke.exp_lvl_end = (5 * (int)Math.pow(poke.level + 1, 3))/4; 
		}
	}

	//wrapper for easy calling
	public static void setNewPokemon(Pokemon poke) {
		setAbility(poke);
		setGender(poke);
		setNature(poke);
		setIv(poke);
		setStats(poke);
	}
}