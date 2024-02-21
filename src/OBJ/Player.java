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

    protected Player(Stat stat, Socket socket, String image) {
        super(stat);
        this.socket = socket;
        this.image = image;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public String activate(String commnad, Enemy enemy, List<Player> players) {
        String comment = null;

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

}
