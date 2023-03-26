package game;

public class Boss extends Dragon implements Undead {

	public Boss() {
		super("보스", 300, 30, 98);
		super.setDefense(30);
	}

	@Override
	public boolean attack(Unit unit) {
		if(unit instanceof Hero) {
			Hero target = (Hero)unit;
			int damage = super.getPower() + Game.ran.nextInt(6);
			System.out.printf("%d의 데미지로 공격!\n", damage);
			
			for(int i=0; i<damage; i++) {
				target.setHp(target.getHp()-1);
				
				if(target.getHp()==0) {
					target.setDead(true);
					return true;
				}
			}
			
			System.out.printf("%s[%d/%d]\n", unit.getName(), unit.getHp(), unit.MAX_Hp);
			
			int ranNum = Game.ran.nextInt(6) + 1;
			
			if(ranNum>3)
				infect(target);

			ranNum = Game.ran.nextInt(6) + 1;

			if(ranNum>3)
				burn(target);
		}
		
		return false;
	}
	
	@Override
	public void infect(Hero hero) {
		if(!hero.isInfected()) {
			hero.setInfected(true);
		}
	}
}
