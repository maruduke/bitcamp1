package Repository;

import OBJ.Enemy;
import OBJ.EnemyCharacter.Devil;
import OBJ.Statistics.Stat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.sql.Statement;

public class EnemyRepository {

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
    public ResultSet getEnemyResult(Connection conn,int id) { // sql DB의 enemy 데이터를 enemyDB에 저장
        try {
            String sql = String.format("SELECT * from enemy WHERE id = %s", id);
            PreparedStatement pstmt = conn.prepareStatement(sql); //조회
            ResultSet enemyDB = pstmt.executeQuery(); // 저장
            return enemyDB;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public Enemy setEnemyDB(ResultSet enemyDB) { // enmeyDB에 db
        Enemy enemy = null;
        try{
            while(enemyDB.next()) {
                int id = enemyDB.getInt("id");
                String image = enemyDB.getString("image");
                String name = enemyDB.getString("name");
                int max_hp = enemyDB.getInt("max_hp");
                int max_pp = enemyDB.getInt("max_pp");
                int basic_attack_point = enemyDB.getInt("basic_attack_point");
                int basic_defence_point = enemyDB.getInt("basic_defence_point");
                enemy = new Devil(new Stat(name, max_hp, max_pp, basic_attack_point, basic_defence_point)
                    , image);
            }
        } catch(Exception e) {e.printStackTrace();}

        if(enemy == null)
            System.out.println("enemy is null");

        return enemy;
    }

    public Enemy createEnemy(int id) {
        // 원하는 Enemy 생성
        ResultSet rs = getEnemyResult(getDBConnection(), id);
        return setEnemyDB(rs);
    }
}
