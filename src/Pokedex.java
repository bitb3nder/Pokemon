public class Pokedex {
	
	int seennum, seen[], caughtnum, caught[]; 

	/* Creates a pokedex object that records all seen Pokemon and all 
	 * caught Pokemon by their id's. Stores data in an array and holds 
	 * that fkkin info for later when we actaully try to access the 
	 * pokedex from a frame. 
	 */
	public Pokedex() {
		this.seennum = seennum;
		this.seen = seen;
		this.caughtnum = caughtnum;
		this.caught = caught; 
	}

	public registerSeen(int id) {
		//to make sure the pokemon has not been previously spotted!
		if((ArrayUtils.contains(seen, key)) == false) {
			

			//check off another one ;)
			seennum++;
		}
	}

	public registerCaught(int id) {
		//to make sure the pokemon has not been previously caught!
		if((ArrayUtils.contains(caught, key)) == false) {


			//check off another one ;)
			caughtnum++;
		}
	}
}