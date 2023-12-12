package groupId.serilizers;

import groupId.Player;

import java.util.List;

public interface Serialization {
    void savePlayersFile(List<Player> players);
}
