package Repository;

import OBJ.Enemy;
import OBJ.EnemyCharacter.Devil;
import OBJ.Statistics.Stat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EnemyRepository {
    ResultSet enemyDB = null;
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
    public void saveEnemyDB(Connection conn,String name) { // sql DB의 enemy 데이터를 enemyDB에 저장
        try {
            String sql = ("SELECT * from enemy WHERE name = name");
            PreparedStatement pstmt = conn.prepareStatement(sql); //조회
            enemyDB = pstmt.executeQuery(); // 저장
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet getEnemyDB() { // 저장된 enemyDB를 반환
        return enemyDB;
    }
    public Enemy setEnemyDB() { // enmeyDB에 db
        Enemy enemy = null;
        try{
            while(enemyDB.next()) {
                String id = enemyDB.getString("id");
                String image = enemyDB.getString("image");
                String name = enemyDB.getString("name");
                int max_hp = enemyDB.getInt("max_hp");
                int max_pp = enemyDB.getInt("max_pp");
                int basic_attack_point = enemyDB.getInt("basic_attack_point");
                int basic_defence_point = enemyDB.getInt("basic_defence_point");
                enemy = new Devil(creatStat(enemyDB), image);
            }
        } catch(Exception e) {e.printStackTrace();}
        return enemy;
    }
    public static Stat creatStat(ResultSet enemyDB) throws Exception {
        Stat stat = null;
        while (enemyDB.next()) {
            String name = enemyDB.getString("name");
            int max_hp = enemyDB.getInt("max_hp");
            int max_pp = enemyDB.getInt("max_pp");
            int basic_attack_point = enemyDB.getInt("basic_attack_point");
            int basic_defence_point = enemyDB.getInt("basic_defence_point");
            stat = new Stat(name, max_hp, max_pp, basic_attack_point, basic_defence_point);
        }
        return stat;
    }

    public Enemy createEnemy(String enemyName) {
        // 원하는 Enemy 생성
        saveEnemyDB(getDBConnection(), enemyName);
        return setEnemyDB();
    }
}
