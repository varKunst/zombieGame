package game;
import java.util.*;

public class Game {
	static public Scanner scan;
	static public Random ran;
	static public boolean isRun;
	
	private Hero hero = new Hero();
	private Zombie zombie = new Zombie();
	private Dracula dracula = new Dracula();
	private Boss boss = new Boss();
	
	private AncientDragon ancientDragon = new AncientDragon();
	private BabyDragon babyDragon = new BabyDragon();
	
	public static Unit[] map;
	public static final int SIZE = 100;

	private void init() {
		this.scan = new Scanner(System.in);
		this.ran = new Random();
		this.isRun = true;
		this.map = new Unit[SIZE];
		
		placeUnit();
	}
	
	private void placeUnit() {
		this.map[this.hero.getPosition()] = this.hero;
		this.map[this.zombie.getPosition()] = this.zombie;
		this.map[this.dracula.getPosition()] = this.dracula;
		this.map[this.boss.getPosition()] = this.boss;
		this.map[this.ancientDragon.getPosition()] = this.ancientDragon;
		this.map[this.babyDragon.getPosition()] = this.babyDragon;
	}
	
	private void moveRoad(int move) {
		this.map[this.hero.getPosition()] = null;
		if(this.hero.move(move)) {
			this.map[this.hero.getPosition()] = this.hero;		
		}
	}
	
	protected void run() {
		System.out.println("좀비게임 시작!");
		init();
		
		while(isRun) {
			System.out.print("주사위를 던집니까?(y/n): ");
			char input = this.scan.next().charAt(0);
			
			if(input=='y') {
				int move = this.ran.nextInt(6) + 1;
				
				moveRoad(move);
								
			} else if(input=='n') {
				System.out.println("이번 턴은 이동하지 않습니다.");
			}
		}
	}
}