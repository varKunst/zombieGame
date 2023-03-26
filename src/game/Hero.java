package game;

public class Hero extends Unit {
	
	private boolean infected;
	private boolean burnd;
	
	public Hero() {
		super("주인공", 150, 20, 0);
	}
	
	public boolean isInfected() {
		return this.infected;
	}

	public void setInfected(boolean infected) {
		System.out.println("좀비에게 물려서 감염되었다!");
		this.infected = infected;
	}

	public boolean isBurnd() {
		return this.infected;
	}

	public void setBurnd(boolean burnd) {
		System.out.println("드래곤의 숨결로 화상을 입었다!");
		this.infected = burnd;
	}

	public boolean move(int number) {
		System.out.printf("%d만큼 이동합니다.\n", number);
		
		for(int i=0; i<number; i++) {
			super.setPosition(super.getPosition()+1);
			
			if(super.getPosition()<Game.SIZE && Game.map[super.getPosition()]!=null) {
				fight(Game.map[super.getPosition()]);
				break;
			}	
			
			if(super.getPosition()==Game.SIZE-1) {
				winGame();
				return false;
			}
		}
		
		if(!super.isDead())
			System.out.printf("[현재 위치: %d]\n", super.getPosition());	
		
		return true;
	}

	@Override
	public boolean attack(Unit unit) {
		int damage = super.getPower() + Game.ran.nextInt(6);
		System.out.printf("%d의 데미지로 공격!\n", damage);
		
		for(int i=0; i<damage; i++) {
			unit.setHp(unit.getHp()-1);
			
			if(unit.getHp()==0) {
				return true;
			}
		}
		System.out.printf("%s[%d/%d]\n", unit.getName(), unit.getHp(), unit.MAX_Hp);
		
		return false;
	}
	
	private void fight(Unit unit) {
		System.out.printf("%s(이)가 나타났다!\n", unit.getName());
		
		while(true) {
			System.out.println("[1.공격한다] [2.회복한다] [3.도망간다]");
			int sel = Game.scan.nextInt();
			
			if(sel==1) {
				unit.setDead(attack(unit));
				if(unit.isDead()) {
					System.out.printf("%s의 승리!\n", super.getName());
					break;
				}
				
			} else if(sel==2) {
				heal();
				
			} else if(sel==3) {
				System.out.println("무사히 도망쳤다.");
				return;
			}
			
			System.out.println();
			super.setDead(unit.attack(this));
			if(super.isDead()) {
				System.out.printf("%s의 승리!\n", unit.getName());
				break;
			}

			if(this.infected) {
				super.setHp(super.getHp()-1);
				System.out.println("독으로 데미지를 1 입었다!");
				System.out.printf("%s[%d/%d]\n", super.getName(), super.getHp(), super.MAX_Hp);
			}

			if(this.burnd) {
				super.setHp(super.getHp()-1);
				System.out.println("화상으로 데미지를 1 입었다!");
				System.out.printf("%s[%d/%d]\n", super.getName(), super.getHp(), super.MAX_Hp);
			}
		}
	}
	
	private void heal() {
		int ranNum = Game.ran.nextInt(6) + 1;
		int prevHp = super.getHp();
		int recoveredHp = super.getHp()+ranNum*ranNum*2+ranNum*2<=super.MAX_Hp? 
				super.getHp()+ranNum*ranNum*2+ranNum*2: super.MAX_Hp;
		super.setHp(recoveredHp);
		System.out.printf("HP가 %d만큼 회복되었습니다.\n", recoveredHp-prevHp);
		
		if(super.getHp()==super.MAX_Hp) {
			System.out.println("HP가 가득 찼습니다.");
		}
		
		this.infected = false;
		this.burnd = false;
	}
	
	private void winGame() {
		System.out.println("GAME CLEAR!");
		Game.isRun = false;
	}
}