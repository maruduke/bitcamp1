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

    @Override
    public void activate(Enemy enemy, List<Player> players) {
        int i = 4;

        if(i == 1)
            tech1(enemy, players);
        else if(i == 2)
            tech2(enemy, players);
    }


}

