package OBJ.Statistics;

public class Stat {
    String name;
    int max_hp, max_pp;
    int hp, pp;
    int basic_attack_point, basic_defence_point; // 기초 공격력, 기초 방어력
    int attackPoint, defencePoint; // 버프로 추가 될 수 있음

    public Stat(String name, int max_hp, int max_pp, int basic_attack_point, int basic_defence_point) {
        this.name = name;
        this.max_hp = max_hp;
        this.max_pp = max_pp;
        this.hp = max_hp;
        this.pp = max_pp;
        this.basic_attack_point = basic_attack_point;
        this.basic_defence_point = basic_defence_point;
        this.attackPoint = basic_attack_point;
        this.defencePoint = basic_defence_point;
    }

    public String getName() {
        return name;
    }

    public int getMax_hp() {
        return max_hp;
    }

    public int getMax_pp() {
        return max_pp;
    }

    public int getHp() {
        return hp;
    }

    public int getPp() {
        return pp;
    }

    public int getBasic_attack_point() {
        return basic_attack_point;
    }

    public int getBasic_defence_point() {
        return basic_defence_point;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getDefencePoint() {
        return defencePoint;
    }

    public int hit(int damage) {
        int RealDamage = damage - this.defencePoint < 0 ? damage - this.defencePoint: 1;
        this.hp -= RealDamage;
        return RealDamage;
    }

    public int heal(int heal) {
        this.hp = this.hp + heal >= max_hp ? max_hp : this.hp + heal;
        return heal;
    }

    public int strength(int up) {
        this.attackPoint += up;
        return up;
    }
    public boolean isDead() {
        return this.hp <= 0 ? true : false;
    }


}
