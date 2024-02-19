package OBJ;

import OBJ.Statistics.Stat;

import java.net.Socket;
import java.util.List;
import java.util.Vector;

public abstract class Player extends Character {

    String Id;
    String Pw;
    String image;
    Socket socket;

    protected Player(Stat stat, Socket socket, String image, String Id, String Pw) {
        super(stat);
        this.socket = socket;
        this.image = image;
        this.Id = Id;
        this.Pw = Pw;
    }

    int tech1(Enemy enemy) {

        // 평타
        return stat.getAttackPoint();
    };

    public abstract void tech2(Enemy enemy, List<Character> characters);
    public abstract void tech3(Enemy enemy, List<Character> characters);
    public abstract void tech4(Enemy enemy, List<Character> characters);



    public void activate(Enemy enemy, Vector<Player> characters) {

    };





}
