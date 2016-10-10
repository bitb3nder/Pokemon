import java.util.*;
import java.lang.Math;

public class source extends MonsterGameplay {

	public source(int id) {
		super(id);
	}

	public static void main(String[] args) {
		System.out.println("Here is a bulbasaur! It is level 5!!!!!!");
		int target = 001;
		
		Pokemon test_poke = new Pokemon(001);
                test_poke.level = 5;
		setNewPokemon(test_poke);
		check(test_poke);

		System.out.println("Maybe worked.");
		System.out.println("Name: " + test_poke.name);
		System.out.println("Level: " + test_poke.level);
		System.out.println("Exp: " + test_poke.exp);
		System.out.println("Gender: " + test_poke.gender);
		System.out.println("Ability: " + test_poke.ability1);
		System.out.println("Nature: " + test_poke.nature);

		System.out.println("Stat Checker: ");
		System.out.println("HP:	ATT:	DEF:	SPATT:	SPDEF:	SPEED:");
		System.out.println((int)Math.floor(test_poke.hp) + "\t" + (int)Math.floor(test_poke.att) + "\t" + (int)Math.floor(test_poke.def) + "\t" + (int)Math.floor(test_poke.spatt) + "\t" + (int)Math.floor(test_poke.spdef) + "\t" + (int)Math.floor(test_poke.speed));

		System.out.println("Adding exp and reprinting.");

		addExp(test_poke);
		check(test_poke);

		System.out.println("Maybe worked.");
		System.out.println("Name: " + test_poke.name);
		System.out.println("Level: " + test_poke.level);
		System.out.println("Exp: " + test_poke.exp);
		System.out.println("Gender: " + test_poke.gender);
		System.out.println("Ability: " + test_poke.ability1);
		System.out.println("Nature: " + test_poke.nature);

		System.out.println("Stat Checker: ");
		System.out.println("HP:	ATT:	DEF:	SPATT:	SPDEF:	SPEED:");
		System.out.println((int)Math.floor(test_poke.hp) + "\t" + (int)Math.floor(test_poke.att) + "\t" + (int)Math.floor(test_poke.def) + "\t" + (int)Math.floor(test_poke.spatt) + "\t" + (int)Math.floor(test_poke.spdef) + "\t" + (int)Math.floor(test_poke.speed));

	}
}