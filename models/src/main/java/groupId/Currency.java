package groupId;

import groupId.enums.CurrencyName;

public class Currency {
    private long id;
    private long playerId;
    private String name;
    private int count;

    public Currency(long id, long playerId, CurrencyName name, int count) {
        this.id = id;
        this.playerId = playerId;
        this.name = name.toString();
        this.count = count;
    }

    public Currency(long id, long playerId, String name, int count) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
        this.count = count;
    }

    public Currency(long id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Currency() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
        return "Currencies{" +
                "id=" + id +
                ", count=" + count +
                ", name='" + name + '\'' +
                '}';
    }
}
