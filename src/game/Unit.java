package game;

abstract public class Unit {
	private String name;
	public int MAX_Hp;
	private int hp;
	private int power;
	private int position;
	private boolean dead;
	
	public Unit(String name, int hp, int power, int position) {
		this.name = name;
		this.MAX_Hp = hp;
		this.hp = this.MAX_Hp;
		this.power = power;
		this.position = position;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		if(dead) {
			System.out.printf("\n%s IS DEAD...\n", this.name);	
			if(this instanceof Hero) {
				System.out.println("GAME OVER...");
				Game.isRun = false;
			}		
		}
		
		this.dead = dead;
	}

	abstract public boolean attack(Unit unit);
}