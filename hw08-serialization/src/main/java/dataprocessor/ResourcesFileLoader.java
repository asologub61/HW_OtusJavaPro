package dataprocessor;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import model.Measurement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ResourcesFileLoader implements Loader {


    private final String name;

    public ResourcesFileLoader(String fileName) {
        this.name = fileName;
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(name)) {

            var reader = new BufferedReader(new InputStreamReader(inputStream));
            return new Gson().fromJson(reader, new TypeToken<List<Measurement>>() {
            }.getType());
        } catch (IOException e) {
            new RuntimeException(e);
        }
        return null;
    }
}