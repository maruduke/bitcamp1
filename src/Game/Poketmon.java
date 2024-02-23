package Game;

import OBJ.Enemy;
import OBJ.EnemyCharacter.EnemyFactory;
import OBJ.Player;
import OBJ.PlayerCharacter.PlayerFactory;
import OBJ.PlayerCharacter.Warrior;
import OBJ.Statistics.Stat;
import Server.SendAndReceive;
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
    public void start() throws IOException {
        SendAndReceive sendAndReceive = new SendAndReceive(players);
        String gameLog = null;

        sendAndReceive.broadcast("게임이 시작되었습니다.");
        // 게임 시작
        sendAndReceive.broadcast(enemy.getImage());

        while(!sequence.isEmpty()) {
            // player1 -> enemy -> player2 -> enemy 순서대로 반복

            Player player = sequence.poll();

            if(player.isDead()) {
                sendAndReceive.broadcast( player.getStat().getName() + "님이 사망하셨습니다.");
                continue;
            }


            String command = sendAndReceive.turn(player.getSocket());
            // command 입력

            gameLog = player.activate(command, enemy, sequence.stream().toList());

            sequence.add(player);
            menu(players, player, enemy);
            System.out.println(gameLog);
            sendAndReceive.broadcast(gameLog);
            state = "boss HP: " + enemy.getStat().getHp();
            for(Player p : players) {
                state += " " + p.getStat().getName() +" HP: " + p.getStat().getHp() ;
            }



            try{
                Thread.sleep(1000);
            }catch(Exception e) {

            }
            if(enemy.isDead()) {
                sendAndReceive.broadcast("Players Win");
                return;
            }

            gameLog = enemy.activate("1", enemy, sequence.stream().toList());
            System.out.println(gameLog);
            sendAndReceive.broadcast(gameLog);

            state = "boss HP: " + enemy.getStat().getHp();
            for(Player p : players) {
                state += " " + p.getStat().getName() +" HP: " + p.getStat().getHp() ;
            }

            sendAndReceive.broadcast(state);


        }

        sendAndReceive.broadcast("Players defeat");


        return;

    }
    public void menu(List<Player> players, Player turn,Enemy enemy) throws IOException {
        String str = (players.get(0).getStat().getName())+"("+ players.get(0).getPosition()+ ")";
        System.out.println(enemy.getImage());
        System.out.print("+------------------------------+------+--------------------------------+\n");
        System.out.print("|    +--      HP "+enemy.getStat().getHp()+" / "+enemy.getStat().getMax_hp()+"        "+enemy.getStat().getName()+"         MP "+enemy.getStat().getPp()+" / "+enemy.getStat().getMax_pp()+"      --+      |\n");
        System.out.print("+------------------------------+------+--------------------------------+\n");
        System.out.print(">>  " + turn.getStat().getName() + "의 턴입니다.\n") ;
        System.out.print("+----------------------------------------------------------------------+\n");
//        System.out.print(String.format("|%-29s :      : [1] %-12s           |\n",str,players.get(0).getStat().getTech1()));
        System.out.printf("|  %-29s", str);
        System.out.printf(" :      : [1] ");
        System.out.printf("%-23s\n", players.get(0).getStat().getTech1());

        System.out.print("|  HP "+ players.get(0).getStat().getHp() + " / " + players.get(0).getStat().getMax_hp() +  "   MP " +players.get(0).getStat().getPp() + " / " +  players.get(0).getStat().getMax_pp() +"     :      : [2] "+ String.format("%-12s",(players.get(0).getStat().getTech2()))+"             |  \n");
        System.out.print("|    "+String.format("%-26s",(players.get(1).getStat().getName())+"("+ players.get(1).getPosition()+ ")")+ ":      : [3] "+String.format("%-12s",(players.get(0).getStat().getTech3()))+"             |\n");
        System.out.print("|  HP "+ players.get(1).getStat().getHp() + " / " + players.get(1).getStat().getMax_hp() +  "   MP " +players.get(1).getStat().getPp() + " / " +  players.get(1).getStat().getMax_pp() +"     :      : [4] "+ String.format("%-12s",(players.get(0).getStat().getTech4()))+"             |  \n");
        System.out.print("+----------------------------------------------------------------------+\n");


    }
}


