package Repository;

import OBJ.Enemy;
import OBJ.Player;
import OBJ.PlayerCharacter.Healer;
import OBJ.PlayerCharacter.Warrior;
import OBJ.Statistics.Stat;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class PlayerRepository {
    ResultSet playerDB = null;
    Connection conn = null;
    public Connection getDBConnection() {
        try {
            String DB_USER = "bitcamp01";
            String DB_PASSWORD = "qwer1234";
            String DB_URL = "jdbc:mysql://localhost:3306/turnbased?useUnicode=true&characterEncoding=utf8";
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch(Exception e) {e.printStackTrace();}
        return conn;
    }
    public void savePlayerDB(Connection conn, String name) {
        try {
            String sql = ("SELECT * from player WHERE name = name"); // player 테이블의 id와 일치하는 행의 데이터 조회 쿼리
            PreparedStatement pstmt = conn.prepareStatement(sql); // 쿼리 실행
            playerDB = pstmt.executeQuery(); // playerDB에 데이터 저장
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet getPlayerDB() {
        return playerDB;
    }
    public void setPlayerDB() {
        try{
            while(playerDB.next()) {
                String id = playerDB.getString("id");
                String pw = playerDB.getString("pw");
                String image = playerDB.getString("image");
                String name = playerDB.getString("name");
                int max_hp = playerDB.getInt("max_hp");
                int max_pp = playerDB.getInt("max_pp");
                int basic_attack_point = playerDB.getInt("basic_attack_point");
                int basic_defence_point = playerDB.getInt("basic_defence_point");

            }
        } catch(Exception e) {e.printStackTrace();}
    }
    public Stat creatStat(ResultSet playerDB) throws Exception {
        Stat stat = null;
        while (playerDB.next()) {
            String name = playerDB.getString("name");
            int max_hp = playerDB.getInt("max_hp");
            int max_pp = playerDB.getInt("max_pp");
            int basic_attack_point = playerDB.getInt("basic_attack_point");
            int basic_defence_point = playerDB.getInt("basic_defence_point");
            stat = new Stat(name, max_hp, max_pp, basic_attack_point, basic_defence_point);
        }
        return stat;
    }

    public Player createPlayer(Socket socket, String name, String playerName) throws Exception {
        savePlayerDB(getDBConnection(), name);
        Stat stat = creatStat(getPlayerDB());
        stat.setName(playerName);
        if(name == "Warrior") {
            return new Warrior(stat, socket, "");
        }
        else if(name == "Healer") {
            return new Healer(stat, socket, "");
        }
        return new Warrior(stat, socket, "");
    }


}
