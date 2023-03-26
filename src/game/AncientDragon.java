package game;

public class AncientDragon extends Dragon {

	public AncientDragon() {
		super("고대드래곤", 250, 25, Game.ran.nextInt(10) + 75);
		super.setDefense(20);
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
					return true;
				}
			}
			
			System.out.printf("%s[%d/%d]\n", unit.getName(), unit.getHp(), unit.MAX_Hp);

			int ranNum = Game.ran.nextInt(6) + 1;

			if(ranNum>3)
				burn(target);
		}
		
		return false;
	}
}