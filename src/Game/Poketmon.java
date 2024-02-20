package Game;

import OBJ.Enemy;
import OBJ.EnemyCharacter.EnemyFactory;
import OBJ.Player;
import OBJ.Statistics.Stat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Poketmon implements Game{

    Vector<Player> players = new Vector<Player>();// 플레이어
    Queue<Player> sequence = new ConcurrentLinkedDeque<>(); // 캐릭터 행동 순서
    EnemyFactory enemyFactory = new EnemyFactory();

    Enemy enemy = enemyFactory.Devil();

    public Poketmon() {

    }

    public boolean state() {
        return players.size() == 2 ? true : false;
    }

    @Override
    public void enterRoom(Socket socket) {
        // 게임 방문자

        // player 데이터 입력 (수정 필요)
        Player player = new Player(new Stat(), socket);
        players.add(player);
        sequence.add(player);

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

            if(enemy.isDead()) {
                SendAndReceive.broadcast("Players Win");
                return;
            }

             gameLog = enemy.activate("1", enemy, players);

            SendAndReceive.broadcast(gameLog);

            if(!player.isDead()) {
                sequence.add(player);
            }
        }

        SendAndReceive.broadcast("Players defeat");

    }
}


class SendAndReceive {
    static Vector<Player> players;
    static String ThreadName = Thread.currentThread().getName();

    SendAndReceive(Vector<Player> players) {
        this.players = players;
    }

    public static void broadcast(String GameLog) throws IOException {
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