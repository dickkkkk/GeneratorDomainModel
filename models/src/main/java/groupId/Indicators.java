package groupId;

public class Indicators {

    private long playerId;
    private int hp;
    private int mana;
    private int power;
    private int dexterity;
    private int intelligence;

    public Indicators(long playerId, int hp, int mana, int power, int dexterity, int intelligence) {
        this.playerId = playerId;
        this.hp = hp;
        this.power = power;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.mana = mana;
    }

    public Indicators() {
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String toString() {
        return "Indicators{" +
                "playerId=" + playerId +
                ", hp=" + hp +
                ", power=" + power +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                ", mana=" + mana +
                '}';
    }
}
