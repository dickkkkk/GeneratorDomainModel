package groupId;


import groupId.enums.AchievementName;

public class Achievement {

    private long id;
    private long playerId;
    private String name;

    public Achievement(long id, long playerId, AchievementName name) {
        this.id = id;
        this.playerId = playerId;
        this.name = name.toString();
    }

    public Achievement(long id, long playerId, String name) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
    }

    public Achievement(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Achievement() {
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

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
