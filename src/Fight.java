/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benfederline
 */
public class Fight extends MonsterGameplay {
    //private static String turnType = "";
    //will let the user know what just happened as far as statuses go.
    private static String statusLogic = "";
    
    /*
     * Status modifiers, used throught the battle and reset to 1 by the switch.
     * Constantly 1, until raised or lowered by status moves. 
     * Goes 6 stages, just like the original games.
     * each stage has a corresponding modifier as seen below
     *  -6  -5  -4  -3  -2  -1   0   1   2   3   4   5   6 
     * |.25|.28|.33|.4 |.5 |.66| 1 |1.5| 2 |2.5| 3 |3.5| 4 |
     * each pokemon starts with these values at 1, and then they are modified in battle.
     * upon switch some statuses and all modifiers are cleared.
     */
    private static double p_att_mod = 1;
    private static double p_def_mod = 1;
    private static double p_spatt_mod = 1;
    private static double p_spdef_mod = 1;
    private static double p_speed_mod = 1;
    private static double p_acc_mod = 1;
    private static double p_evas_mod = 1;
    
    private static double f_att_mod = 1;
    private static double f_def_mod = 1;
    private static double f_spatt_mod = 1;
    private static double f_spdef_mod = 1;
    private static double f_speed_mod = 1;
    private static double f_acc_mod = 1;
    private static double f_evas_mod = 1;
    
    public Fight(int id){
        super(id);
    }
    
    public static Move selectMove(Pokemon poke) {
        //Fight
        
        Move retval = new Move("Tackle");
        return retval;
        /* not currently part of the scope of the initial project */

        //Pokemon(Check stats or switch)
            //switch or cancel
            //turnType = "SWITCH";
                //return null;
        //Bag(Item use)
            //turnType = "ITEM";
                //return null;
        //Flee (only available in wild poke)
            //turnType = "FLEE";
                //return null;
    }
    
    public static Move selectNPCMove(Pokemon foe) {
        Move retval = new Move("Tackle");
        return retval;
    }
    
    public static boolean checkStatus(Pokemon poke) {
        if(null != poke.status) switch (poke.status) {
            case "sleep":
                //50% chance of a wakeup each turn.
                int wakeup = Pokemon.getRandom(1, 2);
                if(wakeup == 1) {
                    //a false return would make the value skip turn false
                    statusLogic = poke.name + " woke up!";
                    poke.status = "ok";
                    return false;
                } else {
                    //now this pokemon will be unable to move.
                    statusLogic = poke.name + " is fast asleep.";
                    return true;
                }
            case "paralyze":
                int paralyzed = Pokemon.getRandom(1, 100);                
                if(paralyzed <= 30) {
                    statusLogic = poke.name + " is paralyzed and was unable to move!";
                    return true;
                } else {
                    return false;
                }
            case "burn":
                //no moves have been programmed to allow this yet 
                return false;
            case "poison":
                //no moves have been programmed to allow this yet
                return false;
            case "freeze":
                //no moves have been programmed to allow this yet
                return false;
            case "confused":
                //no moves have been programmed to allow this yet
                return false;
        //do nothing
            default:
                return false;
        } else {
            return false;
        }
    }
    
    public static String getStatusLogic() {
        return statusLogic;
    }
    
    public static void applyMove(Pokemon poke, Pokemon foe, Move move) {
        double damage;
        int r = Pokemon.getRandom(85, 100);
        double random = r / 100; 
        
        switch (move.category) {
            case "Phys":
                damage = (((2*poke.level)+10)/250) * ((poke.att * p_att_mod) * (foe.def * f_def_mod) * move.base_pow + 2) * random;
                foe.hp = foe.hp - damage;
                break;
            case "Spec":
                damage = (((2*poke.level)+10)/250) * ((poke.spatt * p_spatt_mod) * (foe.spdef * f_spdef_mod) * move.base_pow + 2) * random;
                foe.hp = foe.hp - damage;
                break;
            case "Cond":
                foe.status = move.status;
                break;
            case "Stat":
                //target, affect, amount
                if("Foe".equals(move.target)) {
                    if(null != move.effect) switch (move.effect) {
                        case "att":
                            f_att_mod = move.amount;
                        break;
                        case "def":
                            f_def_mod = move.amount;
                        break;
                        case "spatt":
                            f_spatt_mod = move.amount;
                        break;
                        case "spdef":
                            f_spdef_mod = move.amount;
                        break;
                        case "speed":
                            f_speed_mod = move.amount;
                        break;
                        case "acc":
                            f_acc_mod = move.amount;
                        break;
                        case "evas":
                            f_evas_mod = move.amount; 
                        break;
                    }
                } else if ("Self".equals(move.target)) {
                    if(null != move.effect) switch (move.effect) {
                        case "att":
                            p_att_mod = move.amount;
                        break;
                        case "def":
                            p_def_mod = move.amount;
                        break;
                        case "spatt":
                            p_spatt_mod = move.amount;
                        break;
                        case "spdef":
                            p_spdef_mod = move.amount;
                        break;
                        case "speed":
                            p_speed_mod = move.amount;
                        break;
                        case "acc":
                            p_acc_mod = move.amount;
                        break;
                        case "evas":
                            p_evas_mod = move.amount; 
                        break;
                    }
                }    
                break;        
        }
    }
    
    public static void turn(Pokemon poke, Pokemon foe, String turnType) {
        //implement abilities (future)
        boolean p_skipMove = false;
        boolean f_skipMove = false;
        //player selects Move
        Move pokeMove = selectMove(poke);
        //foe selects Move
        Move foeMove = selectNPCMove(foe);
        
        //check both pokemon for status conditions and apply
        if(turnType.equals("FIGHT")) {
            p_skipMove = checkStatus(poke);
            f_skipMove = checkStatus(foe);
            
            if(p_skipMove == true) {
                applyMove(foe, poke, foeMove);
            }
            if(f_skipMove == true) {
                applyMove(poke, foe, pokeMove);
            } else {
                if(pokeMove.priority > foeMove.priority) {
                    //the user has picked a priority move and is allowed to go first
                    applyMove(poke, foe, pokeMove);
                    applyMove(foe, poke, foeMove);
                } else if(foeMove.priority > pokeMove.priority) {
                    //the foe has picked a priority move and is allowed to go first
                    applyMove(foe, poke, foeMove);
                    applyMove(poke, foe, pokeMove);
                } else { //it was a tie
                    //either both pokemon picked regular moves, or they both picked priority moves
                    //the pokemons speed stat will be used.
                    if(poke.speed * p_speed_mod >= foe.speed * f_speed_mod) {
                        //your pokemon is faster, ties go to the user (ur welcome lol)
                        applyMove(poke, foe, pokeMove);
                        applyMove(foe, poke, foeMove);
                    } else {
                        //foe is faster and will go first
                        applyMove(foe, poke, foeMove);
                        applyMove(poke, foe, pokeMove);
                    }
                }
            }            
        } else if(turnType.equals("SWITCH")) {
            //switch pokemon (not supported in this game)
        } else if(turnType.equals("ITEM")) {
            //use item (not supported in this game)
        } else {
            //nothing
        }
    }
    
    public static void collectEXP(Pokemon poke, Pokemon foe) {
        //use foe exp yeild to calc
    }
    
    public static void collectEV(Pokemon poke, Pokemon foe) {
        //use foe ev yeild to calc
    }
}
