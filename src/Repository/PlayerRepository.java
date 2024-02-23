package Repository;

import OBJ.Enemy;
import OBJ.Player;
import OBJ.PlayerCharacter.Healer;
import OBJ.PlayerCharacter.Warrior;
import OBJ.Statistics.Stat;

import java.net.Socket;
import java.sql.*;


public class PlayerRepository {

    public Connection getDBConnection() {
        Connection conn = null;
        try {
            String DB_USER = "bitcamp01";
            String DB_PASSWORD = "qwer1234";
            String DB_URL = "jdbc:mysql://localhost:3306/turnbased?useUnicode=true&characterEncoding=utf8";
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch(Exception e) {e.printStackTrace();}
        return conn;
    }
    public ResultSet getPlayer(Connection conn, String position) {
        try {
            String sql = String.format("SELECT * from player WHERE position = %s", position); // player 테이블의 id와 일치하는 행의 데이터 조회 쿼리
            PreparedStatement pstmt = conn.prepareStatement(sql); // 쿼리 실행
            ResultSet playerDB = pstmt.executeQuery(); // playerDB에 데이터 저장
            return playerDB;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Stat createPlayerStat(ResultSet playerDB, String name, Socket socket) {
        try{
            while(playerDB.next()) {
                int id = playerDB.getInt("id");
                String position = playerDB.getString("position");
                int max_hp = playerDB.getInt("max_hp");
                int max_pp = playerDB.getInt("max_pp");
                int basic_attack_point = playerDB.getInt("basic_attack_point");
                int basic_defence_point = playerDB.getInt("basic_defence_point");
                String image = playerDB.getString("image");

                return new Stat(name,max_hp,max_pp,basic_attack_point,basic_defence_point, image);
            }
        } catch(Exception e) {e.printStackTrace();}

        return null;
    }



}
