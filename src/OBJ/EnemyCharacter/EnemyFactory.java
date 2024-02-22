package OBJ.EnemyCharacter;

import OBJ.Enemy;
import OBJ.Player;
import OBJ.PlayerCharacter.Warrior;
import OBJ.Statistics.Stat;
import Repository.EnemyRepository;
import java.util.Random;

public class EnemyFactory {

    EnemyRepository enemyRepository = new EnemyRepository();


    public Enemy createEnemy() {
        Random random = new Random();

        int randomVal = random.nextInt(3) + 1;

        Enemy enemy = enemyRepository.createEnemy( randomVal );
        return enemy;
    }

}
