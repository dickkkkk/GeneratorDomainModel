package groupId;

import groupId.db.CRUDService;
import groupId.deserilizers.Deserialization;
import groupId.deserilizers.JsonDeserialization;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Deserialization jsonDeserialization = new JsonDeserialization();
        List<Player> players = jsonDeserialization.readPlayersJson("C:\\tasks/GeneratorDomainModel/players.json");

        CRUDService crudService = new CRUDService();
        //crudService.savePlayers(players);
        Map<Long, Player> playersMap = crudService.getAllPlayers();

    }
}