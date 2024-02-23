package Game;

import OBJ.Enemy;
import OBJ.EnemyCharacter.EnemyFactory;
import OBJ.Player;
import OBJ.PlayerCharacter.Warrior;
import OBJ.Statistics.Stat;
import Server.SendAndReceive;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Poketmon implements Game {

    List<Player> players = new ArrayList<Player>();// 플레이어
    Queue<Player> sequence = new ConcurrentLinkedDeque<>(); // 캐릭터 행동 순서
    EnemyFactory enemyFactory = new EnemyFactory();

    Enemy enemy = null;
    String state = null;

    public Poketmon(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void enterRoom(List<Socket> sockets) throws IOException { // 게임방
        // 게임 방문자
        SendAndReceive sendAndReceive = new SendAndReceive(players);

        // player 데이터 입력 (수정 필요)
        for (Socket socket : sockets) {
            // player name + position 받아오기 json;
            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String json = br.readLine();
            JSONObject obj = new JSONObject(json);

            System.out.println(obj.getString("name"));
            Player player = new Warrior(new Stat(obj.getString("name"), 10, 10, 15, 5), socket, "");
            players.add(player);
            sequence.add(player);
        }
    }

    @Override
    public void start() throws Exception {
        SendAndReceive sendAndReceive = new SendAndReceive(players);
        String gameLog = null;
        sendAndReceive.broadcast();

        sendAndReceive.broadcast(""
            + "   █████████     █████████    ██████   ██████  ██████████        █████████   ███████████    █████████    ███████████    ███████████\n"
            + "  ███░░░░░███   ███░░░░░███  ░░██████ ██████  ░░███░░░░░█       ███░░░░░███░ █░░░███░░░█   ███░░░░░███  ░░███░░░░░███  ░█░░░███░░░█\n"
            + " ███     ░░░   ░███    ░███   ░███░█████░███   ░███  █ ░       ░███    ░░░ ░    ░███  ░   ░███    ░███   ░███    ░███  ░   ░███  ░ \n"
            + "░███           ░███████████   ░███░░███ ░███   ░██████         ░░█████████      ░███      ░███████████   ░██████████       ░███    \n"
            + "░███    █████  ░███░░░░░███   ░███ ░░░  ░███   ░███░░█          ░░░░░░░░███     ░███      ░███░░░░░███   ░███░░░░░███      ░███    \n"
            + "░░███    ░██   ░███    ░███   ░███      ░███   ░███ ░   █       ███    ░███     ░███      ░███    ░███   ░███    ░███      ░███    \n"
            + " ░░█████████   █████   █████  █████     █████  ██████████      ░░█████████      █████     █████   █████  █████   █████     █████   \n"
            + "  ░░░░░░░░░   ░░░░░   ░░░░░  ░░░░░     ░░░░░  ░░░░░░░░░░        ░░░░░░░░░      ░░░░░     ░░░░░   ░░░░░  ░░░░░   ░░░░░     ░░░░░    ");
            Thread.sleep(1500);
        // 게임 시작
        sendAndReceive.broadcast(enemy.getImage());
        Thread.sleep(1000);

        while (!sequence.isEmpty()) {
            // player1 -> enemy -> player2 -> enemy 순서대로 반복

            Player player = sequence.poll();

            if (player.isDead()) {
                sendAndReceive.broadcast(player.getStat().getName() + "님이 사망하셨습니다.");
                Thread.sleep(1000);
                continue;
            }

            String command = sendAndReceive.turn(player.getSocket());
            // command 입력

            gameLog = player.activate(command, enemy, sequence.stream().toList());

            sequence.add(player);
            System.out.println(gameLog);
            System.out.print("gamelog:");
            sendAndReceive.broadcast(gameLog);
            Thread.sleep(1000);
            state = "boss HP: " + enemy.getStat().getHp();
            for (Player p : players) {
                state += " " + p.getStat().getName() + " HP: " + p.getStat().getHp();
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            if (enemy.isDead()) {
                sendAndReceive.broadcast("Players Win");
                return;
            }

            gameLog = enemy.activate("1", enemy, sequence.stream().toList());
            System.out.println(gameLog);
            sendAndReceive.broadcast(gameLog);
            Thread.sleep(1000);

            state = "boss HP: " + enemy.getStat().getHp();
            for (Player p : players) {
                state += " " + p.getStat().getName() + " HP: " + p.getStat().getHp();
            }

            sendAndReceive.broadcast(state);
            Thread.sleep(1000);


        }

        sendAndReceive.broadcast("Players defeat");

        return;

    }
}


