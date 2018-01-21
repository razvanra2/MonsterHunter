package strategies;

import entities.Monster;
import entities.Hero;
import entities.Treasure;
import java.util.ArrayList;
import entities.Mage;
import entities.Warrior;
import entities.Priest;


public class AttackStrategy implements Strategy {

	Hero hero;
	public AttackStrategy(Hero hero) {
		this.hero = hero;
	}

	@Override
	public void attack(Monster m) {
		ArrayList<Treasure> heroInventory = hero.getInventory();
		boolean foundHeroTypeWeapon = false;
		boolean foundWeaknessWeapon = false;
		Treasure foundWeapon = null;
		int monsterDmg = m.getDmg();
		int heroDamage = 0;
		if (heroInventory != null) {
			for (Treasure t : heroInventory) {
				if (t.getDamageType() == Treasure.DamageType.Blunt && hero instanceof Warrior ||
				t.getDamageType() == Treasure.DamageType.Magic && hero instanceof Mage ||
				t.getDamageType() == Treasure.DamageType.Poison && hero instanceof Priest) {
					heroDamage = hero.getBaseDamage() * 3;
					foundHeroTypeWeapon = true;
					foundWeapon = t;
					break;
				}
			}

			if (!foundHeroTypeWeapon) {
				Treasure.DamageType weakness = m.getWeakness();
				for (Treasure t : heroInventory) {
					if (t.getDamageType() == weakness) {
						heroDamage = hero.getBaseDamage() * 2;
						foundWeaknessWeapon = true;
						foundWeapon = t;
						break;
					}
				}
			}
		}
		System.out.println("Hero attacking monster with damage: " + heroDamage);
		System.out.println("Monster attacking hero with damage: " + monsterDmg);

		m.setHP(m.getHP() - heroDamage);
		hero.setHP(hero.getHP() - monsterDmg);

		if (foundHeroTypeWeapon) {
			heroInventory.remove(foundWeapon);
			hero.setInventory(heroInventory);
		}

		if (foundWeaknessWeapon) {
			heroInventory.remove(foundWeapon);
			hero.setInventory(heroInventory);
		}

	    // TODO implement me
	    /*	Attack algorithm
			if hero type weapon found use it (x3 weapon damage)
				else if counter weapon found use it (x2 weapon damage)
				else basic attack (no bonus)
			--> In order to find the weapon, iterate through the inventory of the hero.
	    */
	}

}
