package OBJ;

public abstract class Character {
    String name;
    int max_hp, max_pp;
    int hp, pp;
    int basic_attack_point, basic_defence_point; // 기초 공격력, 기초 방어력
    int attackPoint, defencePoint; // 버프로 추가 될 수 있음
    String tech1, tech2, tech3, tech4; // 기술 이름
    
    Character(String name, int hp, int pp, int attackPoint, int defencePoint) {
        this.name = name;
        this.max_hp = hp;
        this.hp = hp;
        this.max_pp = pp;
        this.pp = pp;
        this.basic_attack_point = attackPoint;
        this.basic_defence_point = defencePoint;
        this.attackPoint = attackPoint;
        this.defencePoint = defencePoint;
    }



    void hit(int damage) {
        this.hp -= (damage-defencePoint);
    }


    int tech1() {
        // 평타
        return attackPoint;
    };

    abstract void activate();
    public abstract void tech2();
    public abstract void tech3();
    public abstract void tech4();

}
