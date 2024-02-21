package OBJ.EnemyCharacter;

import OBJ.Enemy;
import OBJ.Player;
import OBJ.PlayerCharacter.Warrior;
import OBJ.Statistics.Stat;
import Repository.EnemyRepository;

public class EnemyFactory {

    EnemyRepository enemyRepository;

    public Enemy Devil() {
        
        return new Devil(new Stat("Devil", 10, 10, 15, 10), "");
//          return enemyRepository.createEnemy("Devil"); // DB 연결용
    }
}
