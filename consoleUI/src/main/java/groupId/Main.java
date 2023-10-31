package groupId;

import groupId.cui.ConsoleApplication;
import groupId.db.CRUDService;
import groupId.deserilizers.Deserialization;
import groupId.deserilizers.JsonDeserialization;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Deserialization jsonDeserialization = new JsonDeserialization();
        List<Player> players = jsonDeserialization.readPlayersJson("C:\\tasks/GeneratorDomainModel/players.json");

        CRUDService crudService = new CRUDService();
        //crudUtils.savePlayers(players);
        ConsoleApplication consoleApplication = new ConsoleApplication();
        consoleApplication.start();

    }
}