package dataprocessor;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileSerializer implements Serializer {

    private final String name;

    public FileSerializer(String fileName) {
        this.name = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        String gson = new Gson().toJson(data);
        try {
            Files.writeString(Paths.get(name), gson);
        } catch (IOException e) {
            throw new FileProcessException(e);
        }

        //формирует результирующий json и сохраняет его в файл
    }
}
