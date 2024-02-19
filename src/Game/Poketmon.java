package Game;

import OBJ.Character;
import OBJ.Enemy;
import OBJ.EnemyCharacter.Devil;
import OBJ.EnemyCharacter.EnemyFactory;
import OBJ.Player;
import OBJ.Statistics.Stat;

import java.net.Socket;
import java.util.Hashtable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Poketmon implements Game{

    Hashtable<String, Player> players;// 플레이어
    Queue<Character> sequence = new ConcurrentLinkedDeque<>(); // 캐릭터 행동 순서
    EnemyFactory enemyFactory = new EnemyFactory();

    Enemy enemy = enemyFactory.Devil();

    public Poketmon() {

    }

    @Override
    public void enterRoom(String name, Socket socket) {
        // 게임 방문자

        // player 데이터 입력 (수정 필요)
        Player player = new Player(new Stat(), socket);
        players.put(name, player);
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
        while(true) {
            // player1, player2, enemy 순서대로 치기

            Character character = sequence.poll();
            character.activate(4);
            enemy.activate(4);
        }
    }
}
