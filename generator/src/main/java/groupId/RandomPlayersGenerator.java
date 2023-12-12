package groupId;

import groupId.Player;

import java.util.List;

public interface RandomPlayersGenerator {

    List<Player> generateRandomPlayer(int numberPlayers);
}