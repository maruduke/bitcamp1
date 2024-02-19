package OBJ.EnemyCharacter;

import OBJ.Enemy;
import OBJ.Statistics.Stat;

public class EnemyFactory {

    public Enemy Devil() {
        Stat stat = new Stat("name", 10, 10, 15,15);
        Enemy enemy = new Devil(stat, "image");
        return enemy;
    }
}
