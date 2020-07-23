package org.noam.output;

import org.noam.model.OutputModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class OutputToFile {

    private String fileName = "_part.csv";
    private String path = "output/";

    public OutputToFile() {}

    public OutputToFile(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    public void saveToFile(Map.Entry<Integer, List<OutputModel>> entry) {
        final File csvOutputFile = new File(this.path + entry.getKey() + this.fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            entry.getValue().forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
