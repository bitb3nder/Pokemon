/*
 * This file contains all of the methods that upgrade and track the progression of the pokemon,
 * handling everything from leveling up and learning new moves to evolution. 
 */

public class MonsterGameplay extends Pokemon {
	public MonsterGameplay(int id) {
		super(id);
	}

	public static void levelUp(Pokemon poke) {
            //poke.level will be where the pokemon is currently on thier training path
            //exp will hold the total exp
            //exp level start will be the min for the level
            //exp level end will be the max for the level
            //exp to next will hold the exp value required to get to the next level
            switch (poke.lvl_rate) {
                case "fast":
                    while(poke.exp < poke.exp_lvl_end) {
                        poke.level = poke.level + 1;
                        setStats(poke);
                    }
                    break;
                case "med_fast":
                    while(poke.exp < poke.exp_lvl_end) {
				poke.level = poke.level + 1;
				setStats(poke);
			}
                    break;
                case "slow":
                    while(poke.exp < poke.exp_lvl_end) {
				poke.level = poke.level + 1;
				setStats(poke);
			}
                    break;
                case "med_slow":
                    while(poke.exp < poke.exp_lvl_end) {
				poke.level = poke.level + 1;
				setStats(poke);
			}
                    break;
            //do nothing
                default:
                    break;
            }
	}

	public static void addExp(Pokemon poke) {
		poke.exp = poke.exp + 5000;
	}

	public static void evolve(Pokemon poke) {
		//if the pokemon is at evolve level or met the condition
		checkSpecialEvolve(poke);
		if(poke.evolve_lvl <= poke.level || "true".equals(poke.evolve_cond)) {
			Pokemon evo = new Pokemon(poke.evolve);
			//changes the pokemons name to its evolution.
			poke.id = evo.id;
			poke.name = evo.name;
			poke.type1 = evo.type1;
			poke.type2 = evo.type2;
			poke.evolve = evo.evolve;
			poke.evolve_lvl = evo.evolve_lvl;
			poke.evolve_cond = evo.evolve_cond;
			//poke.ability1 holds the actual ability. if evo
			poke.ability1 = evo.ability1;
			poke.ability2 = evo.ability2;
			//gender stays the same 
			poke.height = evo.height;
			poke.weight = evo.weight;
			poke.exp_yield = evo.exp_yield;
			poke.front_img = evo.front_img;
			poke.back_img = evo.back_img;
			poke.overworld_img = evo.overworld_img;
			poke.footprint = evo.footprint;
			poke.color = evo.color;

			//all level specs remain the same; base stats are reset
			poke.base_hp = evo.base_hp;
			poke.base_att = evo.base_att;
			poke.base_def = evo.base_def;
			poke.base_spatt = evo.base_spatt;
			poke.base_spdef = evo.base_spdef;
			poke.base_speed = evo.base_speed;

			//all ev's and iv's remain the same
			//all current stats and happiness remain the same
			//all learned moves are the same 

			poke.unlearned1 = evo.unlearned1;
			poke.unlearned2 = evo.unlearned2;
			poke.unlearned3 = evo.unlearned3;
			poke.unlearned4 = evo.unlearned4;
			poke.unlearned5 = evo.unlearned5;
			poke.unlearned6 = evo.unlearned6;
			poke.unlearned7 = evo.unlearned7;
			poke.unlearned8 = evo.unlearned8;
			poke.unlearned9 = evo.unlearned9;
			poke.unlearned10 = evo.unlearned10;
			poke.unlearned11 = evo.unlearned11;
			poke.unlearned12 = evo.unlearned12;
			poke.unlearned13 = evo.unlearned13;
			poke.unlearned14 = evo.unlearned14;
			poke.unlearned15 = evo.unlearned15;
 
			poke.learnset = evo.learnset;

			poke.learn_tm_hm = evo.learn_tm_hm;

			poke.pokedex_entry = evo.pokedex_entry;
			//nickname stays the same
		}
		//post level up, should recalculate stats based on new info 
		setStats(poke);
	}

	public static void checkSpecialEvolve(Pokemon poke) {
		//if thunderstone, if waterstone, etc
                //not yet a functionality supported 
		poke.evolve_cond = "false"; 
	}

	public static void learnMove(Pokemon poke) {
	}

	public static void teachTm(Pokemon poke) {
	}

	public static void check(Pokemon poke) {
		levelUp(poke);
		evolve(poke);
		learnMove(poke);
	}

}