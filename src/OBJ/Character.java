package OBJ;

import OBJ.Statistics.Stat;

import java.util.List;

public abstract class Character {

    Stat stat;
    String tech1, tech2, tech3, tech4; // 기술 이름
    
    Character(Stat stat) {
        this.stat = stat;
    }



    void hit(int damage) {
        this.stat.hit(damage);
    }


}
