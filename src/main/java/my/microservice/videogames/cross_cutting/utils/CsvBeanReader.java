package my.microservice.videogames.cross_cutting.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CsvBeanReader {
    public static <T> List<T> readCsv(Path path, Class<T> aClass) {
        ObjectMapper mapper = new ObjectMapper();
        try (Reader reader = Files.newBufferedReader(path)) {
            CSVReaderHeaderAware cb = new CSVReaderHeaderAware(reader);
            List<T> list = new ArrayList<>();
            Map<String, String> map;
            while((map = cb.readMap()) != null) {
                T t = mapper.convertValue(map, aClass);
                list.add(t);
            }
            return list;
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
