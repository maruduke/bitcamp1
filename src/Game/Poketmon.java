package Game;

import java.net.Socket;
import java.util.Hashtable;
import java.util.List;

public class Poketmon implements Game{

    Hashtable<String, Socket> sockets;
    Hashtable<String, String> command = new Hashtable();

    @Override
    public void enterRoom(String name, Socket socket) {
        // 게임 방문자
        sockets.put(name, socket);
    }

    @Override
    public void waitGame() {
        // 인원 수 충족시 까지 기다림
        while(true) {
            if (sockets.size() == 2) {
                break;
            }
        }

        start();
    }

    @Override
    public void start() {
        // 게임 시작
        while(true) {
            // player1, player2, enemy 순서대로 치기

        }
    }
}
