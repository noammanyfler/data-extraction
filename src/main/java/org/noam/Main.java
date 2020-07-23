package org.noam;

import com.google.common.collect.Lists;
import org.noam.model.OutputModel;
import org.noam.output.OutputToFile;
import org.noam.transform.TransformStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class.getName());
        final String INPUT_FILE_PATH = Paths.get("").toAbsolutePath().toString() + "/raw/";
        final String INPUT_FILE_NAME = "spins_input.json";
        final int CORES = Runtime.getRuntime().availableProcessors();

        final OutputToFile outputToFile = new OutputToFile();

        try (Stream<String> stream = Files.lines(Paths.get(INPUT_FILE_PATH + INPUT_FILE_NAME))) {
            Stream<OutputModel> outputModelsStream = new TransformStream(stream).transform();

            // Partition the stream to a fixed number of sub lists (number of cores)
            List<OutputModel> numberOfOutputStreams = outputModelsStream.collect(Collectors.toUnmodifiableList());
            Lists.partition(numberOfOutputStreams, (numberOfOutputStreams.size()) / CORES + 1)
                    .parallelStream().forEach(outputToFile::saveToFile);
        } catch (IOException e) {
            logger.severe("Input file not found on path: " + INPUT_FILE_PATH + INPUT_FILE_NAME);
        }
    }
}
