package groupId.db;

import groupId.*;
import groupId.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CRUDService {
    private final Connection connection = DBUtils.getConnection();

    private static final String[] insertPlayers = new String[]{
            "INSERT INTO players(id, player_id, name) VALUES (?, ?, ?);",
            "INSERT INTO indicators(player_id, hp, mana, power, dexterity, intelligence) VALUES (?, ?, ?, ?, ?, ?);",
            "INSERT INTO achievements(id, player_id, name) VALUES (?, ?, ?);",
            "INSERT INTO items(id, player_id, name, count) VALUES (?, ?, ?, ?);",
            "INSERT INTO currencies(id, player_id, name, count) VALUES (?, ?, ?, ?);",
            "INSERT INTO effects(id, player_id, name, duration) VALUES (?, ?, ?, ?);",
            "INSERT INTO spells(id, player_id, name, level) VALUES (?, ?, ?, ?);",
    };

    public Map<Long, Player> getAllPlayers() {
        Map<Long, Player> players = null;

        try (PreparedStatement allPlayers = connection.prepareStatement("SELECT * FROM players");
             PreparedStatement allIndicators = connection.prepareStatement("SELECT * FROM indicators");
             PreparedStatement allAchievements = connection.prepareStatement("SELECT * FROM achievements");
             PreparedStatement allItems = connection.prepareStatement("SELECT * FROM items");
             PreparedStatement allCurrencies = connection.prepareStatement("SELECT * FROM currencies");
             PreparedStatement allEffects = connection.prepareStatement("SELECT * FROM effects");
             PreparedStatement allSpells = connection.prepareStatement("SELECT * FROM spells")
        ) {
            ResultSet rs = allPlayers.executeQuery();
            ResultSet rs2 = allIndicators.executeQuery();
            ResultSet rs3 = allAchievements.executeQuery();
            ResultSet rs4 = allItems.executeQuery();
            ResultSet rs5 = allCurrencies.executeQuery();
            ResultSet rs6 = allEffects.executeQuery();
            ResultSet rs7 = allSpells.executeQuery();

            players = getPlayersFromRs(rs);
            Map<Long, Indicators> indicators = getIndicatorsFromRs(rs2);
            Map<Long, List<Achievement>> achievements = getAchievementsFromRs(rs3);
            Map<Long, List<Item>> items = getItemsFromRs(rs4);
            Map<Long, List<Currency>> currencies = getCurrenciesFromRs(rs5);
            Map<Long, List<Effect>> effects = getEffectsFromRs(rs6);
            Map<Long, List<Spell>> spells = getSpellsFromRs(rs7);

            for (Map.Entry<Long, Player> playerEntry : players.entrySet()) {
                Long playerId = playerEntry.getKey();
                Player player = playerEntry.getValue();

                player.setIndicators(indicators.get(playerId));
                player.setAchievements(achievements.get(playerId));
                player.setItems(items.get(playerId));
                player.setCurrencies(currencies.get(playerId));
                player.setEffects(effects.get(playerId));
                player.setSpells(spells.get(playerId));
                players.put(playerId, player);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return players;
    }

    public Player getPlayer(Long playerId) {
        Player player = null;
        try (PreparedStatement pr = connection.prepareStatement("SELECT * FROM players WHERE player_id = ?");
             PreparedStatement pr2 = connection.prepareStatement("SELECT * FROM indicators WHERE player_id = ?");
             PreparedStatement pr3 = connection.prepareStatement("SELECT * FROM achievements WHERE player_id = ?");
             PreparedStatement pr4 = connection.prepareStatement("SELECT * FROM items WHERE player_id = ?");
             PreparedStatement pr5 = connection.prepareStatement("SELECT * FROM currencies WHERE player_id = ?");
             PreparedStatement pr6 = connection.prepareStatement("SELECT * FROM effects WHERE player_id = ?");
             PreparedStatement pr7 = connection.prepareStatement("SELECT * FROM spells WHERE player_id = ?")
        ) {
            pr.setLong(1, playerId);
            ResultSet rs = pr.executeQuery();
            pr2.setLong(1, playerId);
            ResultSet rs2 = pr2.executeQuery();
            pr3.setLong(1, playerId);
            ResultSet rs3 = pr3.executeQuery();
            pr4.setLong(1, playerId);
            ResultSet rs4 = pr4.executeQuery();
            pr5.setLong(1, playerId);
            ResultSet rs5 = pr5.executeQuery();
            pr6.setLong(1, playerId);
            ResultSet rs6 = pr6.executeQuery();
            pr7.setLong(1, playerId);
            ResultSet rs7 = pr7.executeQuery();

            player = getPlayerFromRs(rs);
            Indicators indicator = getIndicatorFromRs(rs2);
            List<Achievement> achievements = getAchievementFromRs(rs3);
            List<Item> items = getItemFromRs(rs4);
            List<Currency> currencies = getCurrencyFromRs(rs5);
            List<Effect> effects = getEffectFromRs(rs6);
            List<Spell> spells = getSpellFromRs(rs7);

            player.setIndicators(indicator);
            player.setAchievements(achievements);
            player.setItems(items);
            player.setCurrencies(currencies);
            player.setEffects(effects);
            player.setSpells(spells);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return player;
    }

    private Player getPlayerFromRs(ResultSet rs) {
        Player player = null;
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                player = new Player(id, playerId, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    private Map<Long, Player> getPlayersFromRs(ResultSet rs) {
        Map<Long, Player> players = new HashMap<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                players.put(playerId, new Player(id, playerId, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    private Indicators getIndicatorFromRs(ResultSet rs) {
        Indicators indicators = null;
        try {
            while (rs.next()) {
                long playerId = rs.getLong("player_id");
                int hp = rs.getInt("hp");
                int mana = rs.getInt("mana");
                int power = rs.getInt("power");
                int dexterity = rs.getInt("dexterity");
                int intelligence = rs.getInt("intelligence");
                indicators = new Indicators(playerId, hp, mana, power, dexterity, intelligence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indicators;
    }

    private Map<Long, Indicators> getIndicatorsFromRs(ResultSet rs) {
        Map<Long, Indicators> indicators = new HashMap<>();
        try {
            while (rs.next()) {
                long playerId = rs.getLong("player_id");
                int hp = rs.getInt("hp");
                int mana = rs.getInt("mana");
                int power = rs.getInt("power");
                int dexterity = rs.getInt("dexterity");
                int intelligence = rs.getInt("intelligence");
                indicators.put(playerId, new Indicators(playerId, hp, mana, power, dexterity, intelligence));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indicators;
    }

    private List<Achievement> getAchievementFromRs(ResultSet rs) {
        List<Achievement> achievements = new ArrayList<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                achievements.add(new Achievement(id, playerId, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return achievements;
    }

    private Map<Long, List<Achievement>> getAchievementsFromRs(ResultSet rs) {
        Map<Long, List<Achievement>> achievements = new HashMap<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                List<Achievement> achievementList;
                if (!achievements.containsKey(playerId)) {
                    achievementList = new ArrayList<>();
                } else {
                    achievementList = achievements.get(playerId);
                }
                achievementList.add(new Achievement(id, playerId, name));
                achievements.put(playerId, achievementList);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return achievements;
    }

    private List<Item> getItemFromRs(ResultSet rs) {
        List<Item> items = new ArrayList<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                int count = rs.getInt("count");
                items.add(new Item(id, playerId, name, count));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private Map<Long, List<Item>> getItemsFromRs(ResultSet rs) {
        Map<Long, List<Item>> items = new HashMap<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                int count = rs.getInt("count");
                List<Item> itemsList;
                if (!items.containsKey(playerId)) {
                    itemsList = new ArrayList<>();
                } else {
                    itemsList = items.get(playerId);
                }
                itemsList.add(new Item(id, playerId, name, count));
                items.put(playerId, itemsList);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private List<Currency> getCurrencyFromRs(ResultSet rs) {
        List<Currency> currencies = new ArrayList<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                int count = rs.getInt("count");
                currencies.add(new Currency(id, playerId, name, count));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }

    private Map<Long, List<Currency>> getCurrenciesFromRs(ResultSet rs) {
        Map<Long, List<Currency>> currencies = new HashMap<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                int count = rs.getInt("count");
                List<Currency> currencyList;
                if (!currencies.containsKey(playerId)) {
                    currencyList = new ArrayList<>();
                } else {
                    currencyList = currencies.get(playerId);
                }
                currencyList.add(new Currency(id, playerId, name, count));
                currencies.put(playerId, currencyList);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }

    private List<Effect> getEffectFromRs(ResultSet rs) {
        List<Effect> effects = new ArrayList<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                int duration = rs.getInt("duration");
                effects.add(new Effect(id, playerId, name, duration));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return effects;
    }

    private Map<Long, List<Effect>> getEffectsFromRs(ResultSet rs) {
        Map<Long, List<Effect>> effects = new HashMap<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                int duration = rs.getInt("duration");
                List<Effect> effectList;
                if (!effects.containsKey(playerId)) {
                    effectList = new ArrayList<>();
                } else {
                    effectList = effects.get(playerId);
                }
                effectList.add(new Effect(id, playerId, name, duration));
                effects.put(playerId, effectList);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return effects;
    }

    private List<Spell> getSpellFromRs(ResultSet rs) {
        List<Spell> spells = new ArrayList<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                int level = rs.getInt("level");
                spells.add(new Spell(id, playerId, name, level));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return spells;
    }

    private Map<Long, List<Spell>> getSpellsFromRs(ResultSet rs) {
        Map<Long, List<Spell>> spells = new HashMap<>();
        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                long playerId = rs.getLong("player_id");
                String name = rs.getString("name");
                int level = rs.getInt("level");
                List<Spell> spellList;
                if (!spells.containsKey(playerId)) {
                    spellList = new ArrayList<>();
                } else {
                    spellList = spells.get(playerId);
                }
                spellList.add(new Spell(id, playerId, name, level));
                spells.put(playerId, spellList);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return spells;
    }

    public void savePlayer(Player player) {
        try (
                PreparedStatement statement = connection.prepareStatement(insertPlayers[0]);
                PreparedStatement statement2 = connection.prepareStatement(insertPlayers[1]);
                PreparedStatement statement3 = connection.prepareStatement(insertPlayers[2]);
                PreparedStatement statement4 = connection.prepareStatement(insertPlayers[3]);
                PreparedStatement statement5 = connection.prepareStatement(insertPlayers[4]);
                PreparedStatement statement6 = connection.prepareStatement(insertPlayers[5]);
                PreparedStatement statement7 = connection.prepareStatement(insertPlayers[6]);
        ) {
            insertPlayerInStatement(statement, player);
            insertIndicatorsInStatement(statement2, player.getIndicators());
            insertAchievementsInStatement(statement3, player.getAchievements());
            insertItemsInStatement(statement4, player.getItems());
            insertCurrenciesInStatement(statement5, player.getCurrencies());
            insertEffectsInStatement(statement6, player.getEffects());
            insertSpellsInStatement(statement7, player.getSpells());
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    public void savePlayers(List<Player> players) {
        try (
                PreparedStatement statement = connection.prepareStatement(insertPlayers[0]);
                PreparedStatement statement2 = connection.prepareStatement(insertPlayers[1]);
                PreparedStatement statement3 = connection.prepareStatement(insertPlayers[2]);
                PreparedStatement statement4 = connection.prepareStatement(insertPlayers[3]);
                PreparedStatement statement5 = connection.prepareStatement(insertPlayers[4]);
                PreparedStatement statement6 = connection.prepareStatement(insertPlayers[5]);
                PreparedStatement statement7 = connection.prepareStatement(insertPlayers[6]);
        ) {
            Iterator<Player> iterator = players.listIterator();
            Player player;
            while (iterator.hasNext()) {
                player = iterator.next();
                insertPlayerInStatement(statement, player);
                insertIndicatorsInStatement(statement2, player.getIndicators());
                insertAchievementsInStatement(statement3, player.getAchievements());
                insertItemsInStatement(statement4, player.getItems());
                insertCurrenciesInStatement(statement5, player.getCurrencies());
                insertEffectsInStatement(statement6, player.getEffects());
                insertSpellsInStatement(statement7, player.getSpells());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    private void insertPlayerInStatement(PreparedStatement statement, Player player) {
        try {
            statement.setLong(1, player.getId());
            statement.setLong(2, player.getPlayerId());
            statement.setString(3, player.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertIndicatorsInStatement(PreparedStatement statement, Indicators indicators) {
        try {
            statement.setLong(1, indicators.getPlayerId());
            statement.setInt(2, indicators.getHp());
            statement.setInt(3, indicators.getMana());
            statement.setInt(4, indicators.getPower());
            statement.setInt(5, indicators.getDexterity());
            statement.setInt(6, indicators.getIntelligence());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertAchievementsInStatement(PreparedStatement statement, List<Achievement> achievements) {
        Iterator<Achievement> iterator = achievements.listIterator();
        Achievement achievement;
        try {
            while (iterator.hasNext()) {
                achievement = iterator.next();
                statement.setLong(1, achievement.getId());
                statement.setLong(2, achievement.getPlayerId());
                statement.setString(3, achievement.getName());
                statement.executeUpdate();
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertAchievementInStatement(PreparedStatement statement, Achievement achievement) {
        try {
            statement.setLong(1, achievement.getId());
            statement.setLong(2, achievement.getPlayerId());
            statement.setString(3, achievement.getName());
            statement.executeUpdate();
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertItemsInStatement(PreparedStatement statement, List<Item> items) {
        Iterator<Item> iterator = items.listIterator();
        Item item;
        try {
            while (iterator.hasNext()) {
                item = iterator.next();
                statement.setLong(1, item.getId());
                statement.setLong(2, item.getPlayerId());
                statement.setString(3, item.getName());
                statement.setInt(4, item.getCount());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertItemInStatement(PreparedStatement statement, Item item) {
        try {
            statement.setLong(1, item.getId());
            statement.setLong(2, item.getPlayerId());
            statement.setString(3, item.getName());
            statement.setInt(4, item.getCount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertCurrenciesInStatement(PreparedStatement statement, List<Currency> currencies) {
        Iterator<Currency> iterator = currencies.listIterator();
        Currency currency;
        try {
            while (iterator.hasNext()) {
                currency = iterator.next();
                statement.setLong(1, currency.getId());
                statement.setLong(2, currency.getPlayerId());
                statement.setString(3, currency.getName());
                statement.setInt(4, currency.getCount());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertCurrencyInStatement(PreparedStatement statement, Currency currency) {
        try {
            statement.setLong(1, currency.getId());
            statement.setLong(2, currency.getPlayerId());
            statement.setString(3, currency.getName());
            statement.setInt(4, currency.getCount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertEffectsInStatement(PreparedStatement statement, List<Effect> effects) {
        Iterator<Effect> iterator = effects.listIterator();
        Effect effect;
        try {
            while (iterator.hasNext()) {
                effect = iterator.next();
                statement.setLong(1, effect.getId());
                statement.setLong(2, effect.getPlayerId());
                statement.setString(3, effect.getName());
                statement.setInt(4, effect.getDuration());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertEffectInStatement(PreparedStatement statement, Effect effect) {
        try {
            statement.setLong(1, effect.getId());
            statement.setLong(2, effect.getPlayerId());
            statement.setString(3, effect.getName());
            statement.setInt(4, effect.getDuration());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertSpellsInStatement(PreparedStatement statement, List<Spell> spells) {
        Iterator<Spell> iterator = spells.listIterator();
        Spell spell;
        try {
            while (iterator.hasNext()) {
                spell = iterator.next();
                statement.setLong(1, spell.getId());
                statement.setLong(2, spell.getPlayerId());
                statement.setString(3, spell.getName());
                statement.setInt(4, spell.getLevel());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertSpellInStatement(PreparedStatement statement, Spell spell) {
        try {
            statement.setLong(1, spell.getId());
            statement.setLong(2, spell.getPlayerId());
            statement.setString(3, spell.getName());
            statement.setInt(4, spell.getLevel());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createPlayer(Player player) {
        if (searchIdInPlayerTable(player.getId())) {
            System.out.println("Такой id игрока уже есть");
            return false;
        }
        if (searchPlayerIdInTablePlayers(player.getPlayerId())) {
            System.out.println("Такой playerId игрока уже есть");
            return false;
        }
        try (PreparedStatement statement = connection.prepareStatement(insertPlayers[0])) {
            insertPlayerInStatement(statement, player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Игрок успешно создан");
        return true;
    }

    public boolean createIndicators(Indicators indicators) {
        if (!searchPlayerIdInTablePlayers(indicators.getPlayerId())) {
            return false;
        }
        if (!searchPlayerIdInTableIndicators(indicators.getPlayerId())) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPlayers[1]);
            insertIndicatorsInStatement(preparedStatement, indicators);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean createAchievement(Achievement achievement) {
        if (!searchPlayerIdInTablePlayers(achievement.getPlayerId())) {
            return false;
        }
        if (!searchPlayerIdInTableIndicators(achievement.getPlayerId())) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPlayers[2]);
            insertAchievementInStatement(preparedStatement, achievement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean createItem(Item item) {
        if (!searchPlayerIdInTablePlayers(item.getPlayerId())) {
            return false;
        }
        if (!searchPlayerIdInTableIndicators(item.getPlayerId())) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPlayers[3]);
            insertItemInStatement(preparedStatement, item);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean createCurrency(Currency currency) {
        if (!searchPlayerIdInTablePlayers(currency.getPlayerId())) {
            return false;
        }
        if (!searchPlayerIdInTableIndicators(currency.getPlayerId())) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPlayers[3]);
            insertCurrencyInStatement(preparedStatement, currency);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean createEffect(Effect effect) {
        if (!searchPlayerIdInTablePlayers(effect.getPlayerId())) {
            return false;
        }
        if (!searchPlayerIdInTableIndicators(effect.getPlayerId())) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPlayers[3]);
            insertEffectInStatement(preparedStatement, effect);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean createSpell(Spell spell) {
        if (!searchPlayerIdInTablePlayers(spell.getPlayerId())) {
            return false;
        }
        if (!searchPlayerIdInTableIndicators(spell.getPlayerId())) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPlayers[3]);
            insertSpellInStatement(preparedStatement, spell);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean searchPlayerIdInTablePlayers(Long playerId) {
        boolean flag = false;
        String request = "SELECT player_id FROM players WHERE player_id=?";
        try (PreparedStatement allPlayer = connection.prepareStatement(request)) {
            allPlayer.setLong(1, playerId);
            ResultSet rs = allPlayer.executeQuery();
            while (rs.next()) {
                Long playerIdFromTable = rs.getLong("player_id");
                if (playerIdFromTable != null) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public boolean searchPlayerIdInTableIndicators(Long playerId) {
        boolean flag = false;
        String request = "SELECT player_id FROM indicators WHERE player_id=?";
        try (PreparedStatement allPlayer = connection.prepareStatement(request)) {
            allPlayer.setLong(1, playerId);
            ResultSet rs = allPlayer.executeQuery();
            while (rs.next()) {
                Long playerIdFromTable = rs.getLong("player_id");
                if (playerIdFromTable != null) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public boolean searchIdInPlayerTable(Long id) {
        boolean flag = false;
        String request = "SELECT id FROM players WHERE id=?";
        try (PreparedStatement entities = connection.prepareStatement(request)) {
            entities.setLong(1, id);
            ResultSet rs = entities.executeQuery();
            while (rs.next()) {
                Long idFromTable = rs.getLong("id");
                if (idFromTable != null) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public boolean searchIdInAchievementsTable(Long id) {
        boolean flag = false;
        String request = "SELECT id FROM achievements WHERE id=?";
        try (PreparedStatement entities = connection.prepareStatement(request)) {
            entities.setLong(1, id);
            ResultSet rs = entities.executeQuery();
            while (rs.next()) {
                Long idFromTable = rs.getLong("id");
                if (idFromTable != null) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public boolean searchIdInItemTable(Long id) {
        boolean flag = false;
        String request = "SELECT id FROM items WHERE id=?";
        try (PreparedStatement entities = connection.prepareStatement(request)) {
            entities.setLong(1, id);
            ResultSet rs = entities.executeQuery();
            while (rs.next()) {
                Long idFromTable = rs.getLong("id");
                if (idFromTable != null) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public boolean searchIdInCurrencyTable(Long id) {
        boolean flag = false;
        String request = "SELECT id FROM currencies WHERE id=?";
        try (PreparedStatement entities = connection.prepareStatement(request)) {
            entities.setLong(1, id);
            ResultSet rs = entities.executeQuery();
            while (rs.next()) {
                Long idFromTable = rs.getLong("id");
                if (idFromTable != null) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public boolean searchIdInEffectTable(Long id) {
        boolean flag = false;
        String request = "SELECT id FROM effects WHERE id=?";
        try (PreparedStatement entities = connection.prepareStatement(request)) {
            entities.setLong(1, id);
            ResultSet rs = entities.executeQuery();
            while (rs.next()) {
                Long idFromTable = rs.getLong("id");
                if (idFromTable != null) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public boolean searchIdInSpellTable(Long id) {
        boolean flag = false;
        String request = "SELECT id FROM spells WHERE id=?";
        try (PreparedStatement entities = connection.prepareStatement(request)) {
            entities.setLong(1, id);
            ResultSet rs = entities.executeQuery();
            while (rs.next()) {
                Long idFromTable = rs.getLong("id");
                if (idFromTable != null) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public boolean searchIdInTable(Long id, String table) {
        boolean flag = false;
        String request = "SELECT id FROM ? WHERE id=?";
        try (PreparedStatement entities = connection.prepareStatement(request)) {
            entities.setString(1, table);
            entities.setLong(2, id);
            ResultSet rs = entities.executeQuery();
            while (rs.next()) {
                Long idFromTable = rs.getLong("id");
                if (idFromTable != null) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public boolean updateNamePlayers(Long playerId, String name) {
        if (!searchPlayerIdInTablePlayers(playerId)) {
            System.out.println("Такого playerId не существует");
            return false;
        }
        try {
            String request = "UPDATE players SET name = ? WHERE player_id  = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, playerId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Name игрока успешно изменен");
        return true;
    }

    public boolean updateIndicators(Indicators indicators) {
        if (!searchPlayerIdInTableIndicators(indicators.getPlayerId())) {
            return false;
        }
        String request = "UPDATE indicators SET hp = ?, mana = ?, power = ?, dexterity = ?, intelligence = ? WHERE player_id  = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request);) {
            preparedStatement.setInt(1, indicators.getHp());
            preparedStatement.setInt(2, indicators.getMana());
            preparedStatement.setInt(3, indicators.getPower());
            preparedStatement.setInt(4, indicators.getDexterity());
            preparedStatement.setInt(5, indicators.getIntelligence());
            preparedStatement.setLong(6, indicators.getPlayerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean updateAchievements(Achievement achievement) {
        if (!searchIdInAchievementsTable(achievement.getId())) {
            System.out.println("Ачивки с таким id не существует");
            return false;
        }
        String request = "UPDATE achievements SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request);) {
            preparedStatement.setString(1, achievement.getName());
            preparedStatement.setLong(2, achievement.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Ачивка успешно изменена");
        return true;
    }

    public boolean updateItems(Item item) {
        if (!searchIdInItemTable(item.getId())) {
            return false;
        }
        String request = "UPDATE items SET name = ?, count = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request);) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getCount());
            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean updateCurrencies(Currency currency) {
        if (!searchIdInCurrencyTable(currency.getId())) {
            return false;
        }
        String request = "UPDATE currencies SET name = ?, count = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request);) {
            preparedStatement.setString(1, currency.getName());
            preparedStatement.setInt(2, currency.getCount());
            preparedStatement.setLong(3, currency.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean updateEffects(Effect effect) {
        if (!searchIdInEffectTable(effect.getId())) {
            return false;
        }
        String request = "UPDATE effects SET name = ?, duration = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request);) {
            preparedStatement.setString(1, effect.getName());
            preparedStatement.setInt(2, effect.getDuration());
            preparedStatement.setLong(3, effect.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean updateSpells(Spell spell) {
        if (!searchIdInSpellTable(spell.getId())) {
            return false;
        }
        String request = "UPDATE spells SET name = ?, level = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request);) {
            preparedStatement.setString(1, spell.getName());
            preparedStatement.setInt(2, spell.getLevel());
            preparedStatement.setLong(3, spell.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean deletePlayer(Long playerId) {
        if (!searchPlayerIdInTablePlayers(playerId)) {
            System.out.println("Такой playerId не существует");
            return false;
        }
        String request = "DELETE FROM players WHERE player_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Игрок успешно удален");
        return true;
    }

    public boolean deleteIndicator(Long playerId) {
        if (searchPlayerIdInTableIndicators(playerId)) {
            return false;
        }
        String request = "DELETE FROM indicators WHERE player_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean deleteAchievement(Long id) {
        if (!searchIdInAchievementsTable(id)) {
            return false;
        }
        String request = "DELETE FROM achievements WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean deleteItem(Long id) {
        if (!searchIdInItemTable(id)) {
            return false;
        }
        String request = "DELETE FROM items WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean deleteCurrency(Long id) {
        if (!searchIdInCurrencyTable(id)) {
            return false;
        }
        String request = "DELETE FROM currencies WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean deleteEffect(Long id) {
        if (!searchIdInEffectTable(id)) {
            return false;
        }
        String request = "DELETE FROM effects WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean deleteSpell(Long id) {
        if (!searchIdInSpellTable(id)) {
            return false;
        }
        String request = "DELETE FROM spells WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
