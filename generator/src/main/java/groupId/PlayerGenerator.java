package groupId;

import groupId.enums.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PlayerGenerator implements RandomPlayersGenerator {
    private final SecureRandom secureRandom = new SecureRandom();
    private long pastIdItems = 0;
    private long pastIdCurrencies = 0;
    private long pastIdAchievements = 0;
    private long pastIdEffects = 0;
    private long pastIdSpells = 0;
    private long playerId = 0;


    @Override
    public List<Player> generateRandomPlayer(int numberPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numberPlayers; i++) {
            playerId = i+10;
            players.add(new Player(i,
                    playerId,
                    generateNickname(i),
                    generateIndicators(),
                    generateAchievements(),
                    generateItems(),
                    generateCurrencies(),
                    generateEffects(),
                    generateSpells())
            );
        }
        return players;
    }

    private String generateNickname(int id){
        return "RandomPlayer#" + id;
    }

    private Indicators generateIndicators(){
        return new Indicators(playerId,
                secureRandom.nextInt(100),
                secureRandom.nextInt(100),
                secureRandom.nextInt(10),
                secureRandom.nextInt(10),
                secureRandom.nextInt(10)
        );
    }

    private List<Achievement> generateAchievements(){
        List<Achievement> achievements = new ArrayList<>();
        for (int i = 0; i < secureRandom.nextInt(4); i++) {
            pastIdAchievements++;
            achievements.add(new Achievement(pastIdAchievements, playerId, randomNameAchievement()));
        }
        return achievements;
    }

    private AchievementName randomNameAchievement(){
        AchievementName[] achievementNames = AchievementName.values();
        return achievementNames[secureRandom.nextInt(achievementNames.length)];
    }

    private List<Item> generateItems(){
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < secureRandom.nextInt(7); i++) {
            pastIdItems++;
            items.add(new Item(pastIdItems, playerId, randomNameItems(), secureRandom.nextInt(100)));
        }
        return items;
    }

    private ItemName randomNameItems(){
        ItemName[] itemNames = ItemName.values();
        return itemNames[secureRandom.nextInt(itemNames.length)];
    }

    private List<Currency> generateCurrencies(){
        List<Currency> currencies = new ArrayList<>();
        for (int i = 0; i < secureRandom.nextInt(7); i++) {
            pastIdCurrencies++;
            currencies.add(new Currency(pastIdCurrencies, playerId, randomNameCurrencies(), secureRandom.nextInt(100)));
        }
        return currencies;
    }

    private CurrencyName randomNameCurrencies(){
        CurrencyName[] currencyNames = CurrencyName.values();
        return currencyNames[secureRandom.nextInt(currencyNames.length)];
    }

    private List<Effect> generateEffects(){
        List<Effect> effects = new ArrayList<>();
        for (int i = 0; i < secureRandom.nextInt(4); i++) {
            pastIdEffects++;
            effects.add(new Effect(pastIdEffects, playerId, randomNameEffects(), secureRandom.nextInt(30)));
        }
        return effects;
    }

    private EffectName randomNameEffects(){
        EffectName[] effectNames = EffectName.values();
        return effectNames[secureRandom.nextInt(effectNames.length)];
    }

    private List<Spell> generateSpells(){
        List<Spell> spells = new ArrayList<>();
        for (int i = 0; i < secureRandom.nextInt(4); i++) {
            pastIdSpells++;
            spells.add(new Spell(pastIdSpells, playerId, randomNameSpells(), secureRandom.nextInt(10)));
        }
        return spells;
    }

    private SpellName randomNameSpells(){
        SpellName[] spellNames = SpellName.values();
        return spellNames[secureRandom.nextInt(spellNames.length)];
    }
}