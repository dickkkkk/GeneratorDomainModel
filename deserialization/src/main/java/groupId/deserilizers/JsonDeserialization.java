package groupId.deserilizers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import groupId.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonDeserialization implements Deserialization {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Player> readPlayersJson(String pathToFile) {
        File file = new File(pathToFile);
        try {
            return objectMapper.readValue(file, new TypeReference<List<Player>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}