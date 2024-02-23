package OBJ.EnemyCharacter;

import OBJ.Character;
import OBJ.Enemy;
import OBJ.Player;
import OBJ.Statistics.Stat;

import java.util.List;
import java.util.Random;

public class Devil extends Enemy {

    Random random= new Random();
    public Devil(Stat stat) {
        super(stat);
    }

    @Override
    public String tech1(Enemy enemy, List<Player> characters) {

        int randomVal = random.nextInt(10); // 0 이상 10 미만의 랜덤 정수

        int random = (randomVal) % characters.size();
        System.out.println("random code:" + random);
        int damage = characters.get(random).hit(this.stat.getAttackPoint());
        String log = stat.getName() + "이 "+ characters.get(random).getStat().getName() + "을 공격하였습니다. 데미지: " + damage;
        return log;
    }

    @Override
    public String tech2(Enemy enemy, List<Player> characters) {
        int random = ((int) Math.random()*10) % characters.size();
        int damage = characters.get(random).hit(this.stat.getAttackPoint() + 10);
        String log = stat.getName() + "이" + characters.get(random).getStat().getName() + "에게 강타를 사용하였습니다. 데미지: " + damage;
        return log;
    }

    @Override
    public String tech3(Enemy enemy, List<Player> characters) {
        enemy.strength(10);
        return "적의 공격력이 상승하였습니다.";
    }

    @Override
    public String tech4(Enemy enemy, List<Player> characters) {
        enemy.heal(10);
        return "적이 회복을 사용하였습니다.";
    }

}
