package Server;

import Game.Game;
import Game.Poketmon;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        final int port = 9000;


        try {
            ServerSocket serverSocket = new ServerSocket(port);
            String mainThreadName = Thread.currentThread().getName();
            List<Socket> sockets = new ArrayList<>();

//            Game poketmon = null;

            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("socket connect successful");

                sockets.add(socket);

                if(sockets.size() == 2) {
                    GameStart poketmon = new GameStart(sockets);
                    poketmon.start();
                    sockets.clear();
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

class GameStart extends Thread {
    Game poketmon;
    public GameStart(List<Socket> sockets) {
        this.poketmon = new Poketmon();
        poketmon.enterRoom(sockets);
    }

    @Override
    public void run() {
        try {
            System.out.println("---------------- game starting ----------------");
            poketmon.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
