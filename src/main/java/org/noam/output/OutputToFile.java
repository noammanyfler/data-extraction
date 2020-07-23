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
    private AtomicInteger atomicInteger = new AtomicInteger();

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

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void saveToFile(List<OutputModel> outputModels) {
        final File csvOutputFile = new File(this.path + this.getAtomicInteger().getAndIncrement() + this.fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            outputModels.forEach(pw::println);
        } catch (FileNotFoundException e) {
            Logger.getLogger(Main.class.getName()).severe("Could not write output file, directory does not exists");
        }
    }
}
