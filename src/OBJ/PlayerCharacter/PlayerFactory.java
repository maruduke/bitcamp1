package OBJ.PlayerCharacter;

import OBJ.Player;
import OBJ.Statistics.Stat;
import Repository.PlayerRepository;

import java.net.Socket;

public class PlayerFactory {
    // player status(DB 값), socket(socket 연결) 을 사용하여 PlayerData 생성 및 반환

    PlayerRepository repository = new PlayerRepository();

    public Player createPlayer(String name, String position, Socket socket) {

        Player player = null;
        if(position.equals("Knight")) {
            Stat stat = repository.createPlayerStat(name, position);
            // 전사 직업 생성
            player = new Warrior(stat,socket,position);
        } else {
            Stat stat = repository.createPlayerStat(name, "novis");
            // 직업이 존재하지 않을 경우 novis 생성
            player = new Warrior(stat, socket, "novis");
        }

        return player;
    }
}
