import java.util.Scanner;

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
                    while(poke.exp > poke.exp_lvl_end) {
                        poke.level = poke.level + 1;
                        System.out.println("lvl up!");
                        setStats(poke);
                        evolve(poke);
                        learnMove(poke);
                    }
                    break;
                case "med_fast":
                    while(poke.exp > poke.exp_lvl_end) {
                        poke.level = poke.level + 1;
                        System.out.println("lvl up!");
                        setStats(poke);
                        evolve(poke);
                        learnMove(poke);
                    }
                    break;
                case "slow":
                    while(poke.exp > poke.exp_lvl_end) {
			poke.level = poke.level + 1;
			System.out.println("lvl up!");
                        setStats(poke);
                        evolve(poke);
                        learnMove(poke);
                    }
                    break;
                case "med_slow":
                    while(poke.exp > poke.exp_lvl_end) {
			poke.level = poke.level + 1;
			System.out.println("lvl up!");
                        setStats(poke);
                        evolve(poke);
                        learnMove(poke);
                    }
                    break;
            //do nothing
                default:
                    break;
            }
	}

	public static void evolve(Pokemon poke) {
		//if the pokemon is at evolve level or met the condition
		checkSpecialEvolve(poke);
		if(poke.evolve_lvl <= poke.level || "true".equals(poke.evolve_cond)) {
                        System.out.println("Evolved!");
                    
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

			poke.learnSet = evo.learnSet;

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
            //if there is a move to learn & u dont already know it 
            if(poke.learnSet.get(poke.level) != null && !poke.learnSet.get(poke.level).equals(poke.move1.name) && !poke.learnSet.get(poke.level).equals(poke.move2.name) && !poke.learnSet.get(poke.level).equals(poke.move3.name) && !poke.learnSet.get(poke.level).equals(poke.move4.name)) {
                Move newMove = new Move(poke.learnSet.get(poke.level));
                //check for empty slots 
                if("-".equals(poke.move1.name) && (newMove.name != poke.move1.name)) {
                    poke.move1 = newMove;
                    System.out.println(poke.name + " learned " + poke.move1.name);
                    return;
                }
                
                if("-".equals(poke.move2.name)&& (newMove.name != poke.move2.name)) {
                    poke.move2 = newMove;
                    System.out.println(poke.name + " learned " + poke.move2.name);
                    return;
                }
                
                if("-".equals(poke.move3.name)&& (newMove.name != poke.move3.name)) {
                    poke.move3 = new Move(poke.learnSet.get(poke.level));
                    System.out.println(poke.name + " learned " + poke.move3.name);
                    return;
                }
                
                if("-".equals(poke.move4.name)&& (newMove.name != poke.move4.name)) {
                    poke.move4 = new Move(poke.learnSet.get(poke.level));
                    System.out.println(poke.name + " learned " + poke.move4.name);
                    return;
                }
                //if no empty slot prompt user to replace 
                System.out.println(poke.name + " wants to learn " + poke.learnSet.get(poke.level) + "!");
                System.out.println("But, " + poke.name + " already knows four moves.");
                System.out.println("Delete a move to make room for " + poke.learnSet.get(poke.level) + "?");
                System.out.println("enter (no) or 1 2 3 4 to delete the corresponding move.");
                Scanner sc = new Scanner(System.in);
                String ans = sc.nextLine();
                if("1".equals(ans)){
                    System.out.println(poke.name + " forgot " + poke.move1.name);
                    poke.move1 = new Move(poke.learnSet.get(poke.level));
                    System.out.println(poke.name + " learned " + poke.move1.name); 
                } else if("2".equals(ans)){
                    System.out.println(poke.name + " forgot " + poke.move2.name);
                    poke.move2 = new Move(poke.learnSet.get(poke.level));
                    System.out.println(poke.name + " learned " + poke.move2.name);
                } else if("3".equals(ans)){
                    System.out.println(poke.name + " forgot " + poke.move3.name);
                    poke.move3 = new Move(poke.learnSet.get(poke.level));
                    System.out.println(poke.name + " learned " + poke.move3.name);
                } else if("4".equals(ans)){
                    System.out.println(poke.name + " forgot " + poke.move4.name);
                    poke.move4 = new Move(poke.learnSet.get(poke.level));
                    System.out.println(poke.name + " learned " + poke.move4.name);
                } else if("no".equals(ans)) {
                    System.out.println(poke.name + " did not learn " + poke.learnSet.get(poke.level));
                }               
            }
	}

	public static void teachTm(Pokemon poke) {
            //need to redo with the GUI
	}

	public static void check(Pokemon poke) {
		levelUp(poke);
		evolve(poke);
		learnMove(poke);
	}
        
        public static void healTeam(Player p) {
            p.main.current_hp = p.main.hp;
            //p.p2.current_hp = p.p2.hp;
            //p.p3.current_hp = p.p3.hp;
            //p.p4.current_hp = p.p4.hp;
            //p.p5.current_hp = p.p5.hp;
            //p.p6.current_hp = p.p6.hp;
            
            //deal with PP and other stuff later when it matters
        }

}