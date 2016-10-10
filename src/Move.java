public class Move extends moveDatabase {
	public String name, type, category, status, target, effect, description;
	public int contact, protect, amount, priority, tmID; 
	public double base_pow, base_acc, pp, max_pp, status_percent;


	public Move(String move){
		//Use "move" to gather info from dictionary
		String info = get_move(move);
		//Split line on the "\" char into array of Strings
		String data[] = info.split("\\|");

		//basics of every move
		this.name = data[0];
		this.type = data[1];
		this.category = data[2];
		this.contact = Integer.parseInt(data[3]);
		this.protect = Integer.parseInt(data[4]);
		this.base_pow = Double.parseDouble(data[5]);
		this.base_acc = Double.parseDouble(data[6]);
		this.pp = Double.parseDouble(data[7]);
		this.max_pp = Double.parseDouble(data[8]);
		this.tmID = Integer.parseInt(data[9]);

		//for status editing moves
		this.status = data[10];
		this.status_percent = Double.parseDouble(data[11]);

		//for special moves 
		this.target = data[12];
		this.effect = data[13];
		this.amount = Integer.parseInt(data[14]);

		//for meaasuring speed 
		this.priority = Integer.parseInt(data[15]);

		this.description = data[16];

		/* add slots to hold images of the attacks for use 
		 * this.battle_image = example.png;
		 */
	}

}