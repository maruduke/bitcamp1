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

    public Socket getSocket() {
        return socket;
    }







}
