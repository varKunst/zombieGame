package game;

abstract public class Dragon extends Unit {
	private int defense;

	public Dragon(String name, int hp, int power, int position) {
		super(name, hp, power, position);
	}
	
	public int getDefense() {
		return defense;
	}
	
	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void burn(Hero hero) {
		if(!hero.isBurnd()) {
			hero.setBurnd(true);
		}
	}
}