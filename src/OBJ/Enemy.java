package OBJ;

public abstract class Enemy extends Character{
    String image;

    Enemy(String name, int hp, int pp, int attackPoint, int defencePoint, String image) {
        super(name, hp, pp, attackPoint, defencePoint);
        this.image = image;
    }

    @Override
    void activate() {
        // 행동
    }
}

