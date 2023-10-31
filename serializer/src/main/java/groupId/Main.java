package groupId;

import groupId.serilizers.JsonSerialization;
import groupId.serilizers.Serialization;

public class Main {
    public static void main(String[] args) {
        RandomPlayersGenerator playerGenerator = new PlayerGenerator();

        Serialization jsonSerialization = new JsonSerialization();
        jsonSerialization.savePlayersFile(playerGenerator.generateRandomPlayer(1000));
    }
}