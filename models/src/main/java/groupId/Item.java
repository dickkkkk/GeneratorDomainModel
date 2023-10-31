package groupId;


import groupId.enums.ItemName;

public class Item {
    private long id;
    private long playerId;
    private String name;
    private int count;

    public Item(long id, long playerId, ItemName name, int count) {
        this.id = id;
        this.playerId = playerId;
        this.name = name.toString();
        this.count = count;
    }

    public Item(long id, long playerId, String name, int count) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
        this.count = count;
    }

    public Item(long id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Item() {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
