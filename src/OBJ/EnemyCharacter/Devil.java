package OBJ.EnemyCharacter;

import OBJ.Character;
import OBJ.Enemy;
import OBJ.Player;
import OBJ.Statistics.Stat;

import java.util.List;

public class Devil extends Enemy {

    public Devil(Stat stat, String image) {
        super(stat, image);
    }

    @Override
    public String tech2(Enemy enemy, List<Player> characters) {
        return "강타";
    }

    @Override
    public String tech3(Enemy enemy, List<Player> characters) {
        return "버프";
    }

    @Override
    public String tech4(Enemy enemy, List<Player> characters) {
        enemy.heal(10);
        return "회복";
    }

}
