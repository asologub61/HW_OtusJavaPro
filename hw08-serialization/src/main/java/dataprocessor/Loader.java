package dataprocessor;

import model.Measurement;

import java.util.List;

public interface Loader {

    List<Measurement> load();
}
