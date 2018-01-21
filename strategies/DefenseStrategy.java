package strategies;

import entities.Monster;
import entities.Hero;
import entities.Treasure;
import java.util.ArrayList;
import entities.Mage;
import entities.Warrior;
import entities.Priest;

public class DefenseStrategy implements Strategy {

	//TODO Implement constructor with a Hero as argument
	Hero hero;
	public DefenseStrategy(Hero hero) {
		this.hero = hero;
	}
	@Override
	public void attack(Monster m) {
		ArrayList<Treasure> heroInventory = hero.getInventory();
		boolean foundHeroTypeWeapon = false;
		Treasure foundWeapon = null;
		if (heroInventory != null) {
			for (Treasure t : heroInventory) {
				if (t.getDamageType() == Treasure.DamageType.Blunt && hero instanceof Warrior ||
				t.getDamageType() == Treasure.DamageType.Magic && hero instanceof Mage ||
				t.getDamageType() == Treasure.DamageType.Poison && hero instanceof Priest) {
					hero.setHP(hero.getHP() + t.getBoostHp() + hero.getBaseHpBoost());
					foundHeroTypeWeapon = true;
					foundWeapon = t;
					break;
				}
			}
		}

		if (!foundHeroTypeWeapon) {
			hero.setHP(hero.getHP() + hero.getBaseHpBoost());
		}

		int monsterDmg = m.getDmg();
		System.out.println("Hero attacking monster with damage: " + hero.getBaseDamage());
		System.out.println("Monster attacking hero with damage: " + monsterDmg);

		m.setHP(m.getHP() - hero.getBaseDamage());
		hero.setHP(hero.getHP() - monsterDmg);

		if (foundHeroTypeWeapon) {
			heroInventory.remove(foundWeapon);
			hero.setInventory(heroInventory);
		}
	}

}
