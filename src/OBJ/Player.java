package OBJ;

public abstract class Player extends Character {

    Position position;

    Player(String name, int hp, int pp, int attackPoint, int defencePoint, Position position) {
        super(name, hp, pp, attackPoint, defencePoint);
        this.position = position;
    }

    @Override
    void activate() {

    }


}
