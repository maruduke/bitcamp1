package OBJ;

import OBJ.Statistics.Stat;

import java.util.List;
import java.util.Vector;

public abstract class Enemy extends Character{
    String image;

    protected Enemy(Stat stat, String image) {
        super(stat);
        this.image = image;
    }

    int tech1(Enemy enemy, List<Character> characters) {
        // 평타
        return stat.getAttackPoint();
    };

    public abstract void tech2(Enemy enemy, List<Character> characters);
    public abstract void tech3(Enemy enemy, List<Character> characters);
    public abstract void tech4(Enemy enemy, List<Character> characters);



    public void activate(Enemy enemy, Vector<Player> characters) {

    };

}

