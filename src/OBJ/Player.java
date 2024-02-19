package OBJ;

import OBJ.Statistics.Stat;

import java.net.Socket;
import java.util.List;

public abstract class Player extends Character {

    String image;
    Socket socket;

    protected Player(Stat stat, Socket socket, String image) {
        super(stat);
        this.socket = socket;
        this.image = image;
    }

    int tech1(Enemy enemy) {

        // 평타
        return stat.getAttackPoint();
    };

    public abstract void tech2(Enemy enemy, List<Character> characters);
    public abstract void tech3(Enemy enemy, List<Character> characters);
    public abstract void tech4(Enemy enemy, List<Character> characters);



    public void activate(int command, Enemy enemy, List<Character> characters) {

    };



}
