package groupId;


import groupId.enums.SpellName;

public class Spell {

    private long id;
    private long playerId;
    private String name;
    private int level;

    public Spell(long id, long playerId, SpellName name, int level) {
        this.id = id;
        this.playerId = playerId;
        this.name = name.toString();
        this.level = level;
    }

    public Spell(long id, long playerId, String name, int level) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
        this.level = level;
    }

    public Spell(long id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public Spell() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
