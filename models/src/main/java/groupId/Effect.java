package groupId;


import groupId.enums.EffectName;

public class Effect {

    private long id;
    private long playerId;
    private String name;
    private int duration;

    public Effect(long id, long playerId, EffectName name, int duration) {
        this.id = id;
        this.playerId = playerId;
        this.name = name.toString();
        this.duration = duration;
    }

    public Effect(long id, long playerId, String name, int duration) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
        this.duration = duration;
    }

    public Effect(long id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public Effect() {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Effect{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
