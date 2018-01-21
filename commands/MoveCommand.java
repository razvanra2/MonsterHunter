package commands;

import entities.Hero;

public class MoveCommand implements Command {

	Hero hero;
	Hero.Direction dir;

	public MoveCommand(Hero newHero, Hero.Direction newDir) {
		this.hero = newHero;
		this.dir = newDir;
	}
	@Override
	public void undo() {
		if (dir == Hero.Direction.N) {
			hero.move(Hero.Direction.S);
		}

		if (dir == Hero.Direction.E) {
			hero.move(Hero.Direction.W);
		}

		if (dir == Hero.Direction.W) {
			hero.move(Hero.Direction.E);
		}

		if (dir == Hero.Direction.S) {
			hero.move(Hero.Direction.N);
		}

	}

	@Override
	public void execute() {
		hero.move(dir);

	}
}
