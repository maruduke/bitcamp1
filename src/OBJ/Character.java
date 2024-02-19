package OBJ;

import OBJ.Statistics.Stat;

import java.util.List;
import java.util.Vector;

public abstract class Character {

    public Stat stat;
    String tech1, tech2, tech3, tech4; // 기술 이름
    
    Character(Stat stat) {
        this.stat = stat;
    }


    public int hit(int damage) {
        // 공격 받음
        return this.stat.hit(damage);
    }
    public int heal(int heal) {
        // 치료 받음
        return this.stat.heal(heal);
    }

    public boolean isDead() {
        return stat.getHp() <= 0 ? true : false;
    }

    String tech1(Enemy enemy, List<Player> characters) {

        // 평타
        return "평타";
    };

    public abstract String tech2(Enemy enemy, List<Player> characters);
    public abstract String tech3(Enemy enemy, List<Player> characters);
    public abstract String tech4(Enemy enemy, List<Player> characters);



    public abstract void activate(Enemy enemy, List<Player> players);

    public Stat getStat() {
        return this.stat;
    }
}
