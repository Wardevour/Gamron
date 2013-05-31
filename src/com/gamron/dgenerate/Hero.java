public class Hero{
	String name, heroClass;
	int level;
	
	public Hero(){
		name = "Unknown Hero";
		level = 1;
		heroClass = "Halfling";
	}
	
	public Hero(String nme, int lvl, String hc){
		name = nme;
		level = lvl;
		heroClass = hc;
	}
}