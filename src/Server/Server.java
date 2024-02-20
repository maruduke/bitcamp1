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
                    Thread poketmon = new Thread(new GameStart(new ArrayList<>(sockets), new Poketmon()));
                    poketmon.start();
                    sockets.clear();
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

class GameStart implements Runnable {
    List<Socket> sockets;
    Game poketmon;
    public GameStart(List<Socket> sockets, Poketmon poketmon) {
        this.sockets = sockets;
        this.poketmon = poketmon;
    }

    @Override
    public void run() {
        try {
            poketmon.enterRoom(sockets);
            System.out.println("---------------- game starting ----------------");
            poketmon.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
