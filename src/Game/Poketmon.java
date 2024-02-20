package Game;

import OBJ.Enemy;
import OBJ.EnemyCharacter.EnemyFactory;
import OBJ.Player;
import OBJ.PlayerCharacter.Warrior;
import OBJ.Statistics.Stat;
import Server.SendAndReceive;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Poketmon implements Game{

    List<Player> players = new ArrayList<Player>();// 플레이어
    Queue<Player> sequence = new ConcurrentLinkedDeque<>(); // 캐릭터 행동 순서
    EnemyFactory enemyFactory = new EnemyFactory();

    Enemy enemy = enemyFactory.Devil();

    public Poketmon() {

    }

    public boolean state() {
        return players.size() == 2 ? true : false;
    }

    @Override
    public void enterRoom(List<Socket> sockets) {
        // 게임 방문자

        // player 데이터 입력 (수정 필요)
        for(Socket socket : sockets) {
            Player player = new Warrior(new Stat("name", 10, 10, 15, 15), socket, "", "", "");
            players.add(player);
            sequence.add(player);
        }


    }


    @Override
    public void start() throws IOException {
        SendAndReceive sendAndReceive = new SendAndReceive(players);
        String gameLog = null;

        // 게임 시작
        while(!sequence.isEmpty()) {
            // player1 -> enemy -> player2 -> enemy 순서대로 반복

            Player player = sequence.poll();

            player.activate("1", enemy, sequence.stream().toList());

            try{
                Thread.sleep(1000);
            }catch(Exception e) {

            }
            if(enemy.isDead()) {
                sendAndReceive.broadcast("Players Win");
                return;
            }

             gameLog = enemy.activate("1", enemy, players);
            System.out.println(gameLog);
            sendAndReceive.broadcast(gameLog);

            if(!player.isDead()) {
                sequence.add(player);
            }
        }

        sendAndReceive.broadcast("Players defeat");

    }
}


