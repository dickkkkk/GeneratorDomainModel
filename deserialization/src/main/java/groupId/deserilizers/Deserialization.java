package groupId.deserilizers;

import groupId.Player;

import java.util.List;

public interface Deserialization {
    List<Player> readPlayersJson(String pathToFile);
}
