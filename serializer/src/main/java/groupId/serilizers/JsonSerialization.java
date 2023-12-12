package groupId.serilizers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import groupId.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonSerialization implements Serialization{
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonSerialization() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void savePlayersFile(List<Player> players) {
        try {
            objectMapper.writeValue(new File("players.json"), players);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
