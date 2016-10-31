public class Pokedex {
	
	int seennum, seen[], caughtnum, caught[]; 

	/* Creates a pokedex object that records all seen Pokemon and all 
	 * caught Pokemon by their id's. Stores data in an array and holds 
	 * that fkkin info for later when we actaully try to access the 
	 * pokedex from a frame. 
	 */
	public Pokedex() {
		this.seennum = 0;
		this.seen = null;
		this.caughtnum = 0;
		this.caught = null; 
	}

	public void registerSeen(int id) {
            seennum++;
	}

	public void registerCaught(int id) {
            caughtnum++;
	}
}