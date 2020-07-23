package org.noam.output;

import org.noam.Main;
import org.noam.model.OutputModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class OutputToFile {

    private String fileName = "_part.csv";
    private String path = "output/";
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public OutputToFile() {
        this.createOutputDir(this.path);
    }

    public OutputToFile(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
        createOutputDir(path);
    }

    private void createOutputDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    /**
     * Save list of elements in output directory with unique number
     * @param outputModels List of models to write to file
     */
    public void saveToFile(List<OutputModel> outputModels) {
        final File csvOutputFile = new File(this.getPath() + this.getAtomicInteger().getAndIncrement() + this.getFileName());
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            outputModels.forEach(pw::println);
        } catch (FileNotFoundException e) {
            Logger.getLogger(Main.class.getName()).severe("Could not write output file, directory does not exists");
        }
    }
}
