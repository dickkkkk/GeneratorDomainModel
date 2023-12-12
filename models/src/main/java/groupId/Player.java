package groupId;

import java.util.List;

public class Player {

    private long id;
    private long playerId;
    private String name;
    private Indicators indicators;
    private List<Achievement> achievements;
    private List<Item> items;
    private List<Currency> currencies;
    private List<Effect> effects;
    private List<Spell> spells;

    public Player(long id, long playerId, String name, Indicators indicators, List<Achievement> achievements, List<Item> items, List<Currency> currencies, List<Effect> effects, List<Spell> spells) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
        this.indicators = indicators;
        this.achievements = achievements;
        this.items = items;
        this.currencies = currencies;
        this.effects = effects;
        this.spells = spells;
    }

    public Player(long id, long playerId, String name) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
    }

    public Player() {
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public Indicators getIndicators() {
        return indicators;
    }

    public void setIndicators(Indicators indicators) {
        this.indicators = indicators;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", name='" + name + '\'' +
                ", indicators=" + indicators +
                ", achievements=" + achievements +
                ", items=" + items +
                ", currencies=" + currencies +
                ", effects=" + effects +
                ", spells=" + spells +
                '}';
    }
}
