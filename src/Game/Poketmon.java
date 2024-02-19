package Game;

import OBJ.Character;
import OBJ.Enemy;
import OBJ.EnemyCharacter.EnemyFactory;
import OBJ.Player;
import OBJ.Statistics.Stat;

import java.net.Socket;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Poketmon implements Game{

    Vector<Player> players;// 플레이어
    Queue<Player> sequence = new ConcurrentLinkedDeque<>(); // 캐릭터 행동 순서
    EnemyFactory enemyFactory = new EnemyFactory();

    Enemy enemy = enemyFactory.Devil();

    public Poketmon() {

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
    public void waitGame() {
        // 인원 수 충족시 까지 기다림
        while(true) {
            if (players.size() == 2) {
                break;
            }
        }

        start();
    }

    @Override
    public void start() {

        // 게임 시작
        while(!sequence.isEmpty()) {
            // player1 -> enemy -> player2 -> enemy 순서대로 반복

            Player player = sequence.poll();
            if(!player.isDead()) {
                sequence.add(player);
            }

            player.activate(enemy, players);

            if(enemy.isDead()) {
                System.out.println("you win");
                return;
            }

            enemy.activate(enemy, players);


        }

        System.out.println("loser");
        return;
    }
}
