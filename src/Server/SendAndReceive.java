package Server;

import OBJ.Player;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class SendAndReceive {
    List<Player> players;
    String ThreadName = Thread.currentThread().getName();

    public SendAndReceive(List<Player> players) {
        this.players = players;
    }

    public void broadcast(String GameLog) throws IOException {
        for (Player p : players) {
            Socket s = p.getSocket();

            OutputStream out = s.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out) );
            System.out.println(ThreadName + ": " + GameLog);
            pw.println(GameLog);
            pw.flush();
        }
    }


}

