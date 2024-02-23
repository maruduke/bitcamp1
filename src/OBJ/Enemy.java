package OBJ;

import OBJ.Statistics.Stat;

import java.util.List;
import java.util.Vector;

public abstract class Enemy extends Character{
    String image;

    protected Enemy(Stat stat) {
        super(stat);
    }

    @Override
    public String activate(String commnad, Enemy enemy, List<Player> players) {
        String comment = null;

        System.out.println("enemy turn");

        if(commnad.equals("1"))
            comment = tech1(enemy, players);
        else if(commnad.equals("2") )
            comment = tech2(enemy, players);
        else if(commnad.equals("3"))
            comment = tech3(enemy, players);
        else if(commnad.equals("4"))
            comment = tech4(enemy,players);

        return comment;
    }

    public String getImage() {
        return image;
    }

}

