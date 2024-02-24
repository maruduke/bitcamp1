package Game;

import OBJ.Enemy;
import OBJ.EnemyCharacter.EnemyFactory;
import OBJ.Player;
import OBJ.PlayerCharacter.PlayerFactory;
import OBJ.PlayerCharacter.Warrior;
import OBJ.Statistics.Stat;
import Server.SendAndReceive;
import java.util.Random;
import javax.xml.transform.SourceLocator;
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

public class Poketmon implements Game{

    List<Player> players = new ArrayList<Player>();// 플레이어
    Queue<Player> sequence = new ConcurrentLinkedDeque<>(); // 캐릭터 행동 순서
    EnemyFactory enemyFactory = new EnemyFactory();
    PlayerFactory playerFactory = new PlayerFactory();




    Enemy enemy = null;
    String state = null;
    public Poketmon(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void enterRoom(List<Socket> sockets) throws IOException {
        System.out.println();
        // 게임 방문자
        SendAndReceive sendAndReceive = new SendAndReceive(players);

        // player 데이터 입력 (수정 필요)
        for(Socket socket : sockets) {
            // player name + position 받아오기 json;
            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String json = br.readLine();
            JSONObject obj = new JSONObject(json);


            System.out.println(obj.getString("name"));
            Player player = playerFactory.createPlayer(obj.getString("name"), obj.getString("position"), socket);
            players.add(player);
            sequence.add(player);
        }


    }


    @Override
    public void start() throws Exception {
        SendAndReceive sendAndReceive = new SendAndReceive(players);
        String gameLog = null;
        try {
        sendAndReceive.broadcast(players.get(0).getStat().getImage());
        Thread.sleep(1500);
        sendAndReceive.broadcast(players.get(1).getStat().getImage());
        Thread.sleep(1500);
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
        } catch (Exception e) { e.printStackTrace();}
        // 게임 시작

        while(!sequence.isEmpty()) {
            // player1 -> enemy -> player2 -> enemy 순서대로 반복

            Player player = sequence.poll();

            if(player.isDead()) {
                sendAndReceive.broadcast( player.getStat().getName() + "님이 사망하셨습니다.");
                continue;
            }

            Thread.sleep(1500);
            sendAndReceive.broadcast(menu(players, player, enemy));

            String command = sendAndReceive.turn(player.getSocket());
            // command 입력

            gameLog = player.activate(command, enemy, sequence.stream().toList());
            Thread.sleep(1500);

            sequence.add(player);

            System.out.println(gameLog);
            sendAndReceive.broadcast(gameLog);
            Thread.sleep(1500);



            Thread.sleep(1500);

            if(enemy.isDead()) {
                sendAndReceive.broadcast("Players Win");
                return;
            }

            gameLog = enemy.activate("1", enemy, sequence.stream().toList());
            System.out.println(gameLog);
            sendAndReceive.broadcast(gameLog);
            Thread.sleep(1500);


        }

        sendAndReceive.broadcast("Players defeat");


        return;

    }

    public String menu(List<Player> players, Player turn,Enemy enemy) throws IOException {
        return enemy.getStat().getImage()
            + "\n+------------------------------+------+--------------------------------+\n"
            + String.format("     +--      HP %-2d / %-2d", enemy.getStat().getHp(), enemy.getStat().getMax_hp())
            + String.format("        %-17s", enemy.getStat().getName())
            + String.format("MP %-2d / %-2d     --+\n", enemy.getStat().getPp(), enemy.getStat().getMax_pp())
            + "+------------------------------+------+--------------------------------+\n"
            + " █ Player1-"
            + printPlayerInfo(players.get(0).getStat().getName(),players.get(0).getPosition(), players.get(0).getStat().getHp(),players.get(0).getStat().getMax_hp(), players.get(0).getStat().getPp(), players.get(0).getStat().getMax_pp())
            + " █ Player2-"
            + printPlayerInfo(players.get(1).getStat().getName(),players.get(1).getPosition(), players.get(1).getStat().getHp(), players.get(1).getStat().getMax_hp(),players.get(1).getStat().getPp(), players.get(1).getStat().getMax_pp())
            + String.format("[1] %s\n",turn.getStat().getTech1())
            + String.format("[2] %s\n",turn.getStat().getTech2())
            + String.format("[3] %s\n",turn.getStat().getTech3())
            + String.format("[4] %s\n",turn.getStat().getTech4())
            + "+----------------------------------------------------------------------+\n"
            + String.format(">>  %s(%s)의 턴입니다. >>\n", turn.getStat().getName(), turn.getPosition());
    }
    private String printPlayerInfo(String name, String position, int hp, int maxHP, int mp, int maxMP) {
        return String.format(" %s", name)
            + String.format("(%s)\n", position)
            + String.format("  > HP %-2d / %-2d   MP %-2d / %-2d\n", hp, maxHP, mp, maxMP);
    }
    public String enemyTech(){
        Random random= new Random();
        int randomVal = random.nextInt(3)+1;
        return enemyTech().toString();
    }
}


