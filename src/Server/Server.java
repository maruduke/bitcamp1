package Server;

import Game.Game;
import Game.Poketmon;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        final int port = 9000;


        try {
            ServerSocket serverSocket = new ServerSocket(port);
            String mainThreadName = Thread.currentThread().getName();


            Game poketmon = new Poketmon();

            while(true) {
                Socket socket = serverSocket.accept();
                if(poketmon == null) {
                    poketmon = new Poketmon();
                }
                else {
                    poketmon.enterRoom(socket);
                }

                if(poketmon.state()) {
                    // 실행
                    GameStart gameStart = new GameStart(poketmon);
                    gameStart.run();
                    poketmon = null;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

class GameStart extends Thread {
    Game poketmon;
    public GameStart(Game poketmon) {
        this.poketmon = poketmon;
    }

    @Override
    public void run() {
        try {
            poketmon.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
