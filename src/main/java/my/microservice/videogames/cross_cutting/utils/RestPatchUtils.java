package my.microservice.videogames.cross_cutting.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import my.artifacts.abstractions.ICommand;
import my.microservice.videogames.cross_cutting.exceptions.PatchException;

public class RestPatchUtils {

    public static <T extends ICommand> T apply(JsonPatch patch, T command) {
        ObjectMapper mapper = ApplicationContextProvider.getContext().getBean(ObjectMapper.class);
        try {
            JsonNode patched = patch.apply(mapper.convertValue(command, JsonNode.class));
            return (T) mapper.treeToValue(patched, command.getClass());
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new PatchException(command.getClass(), patch.toString());
        }
    }
}
