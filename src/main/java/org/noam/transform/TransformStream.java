package org.noam.transform;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.noam.model.OutputModel;

import java.util.stream.Stream;

public class TransformStream {
    private Stream<String> stringStream;

    public Stream<String> getStringStream() {
        return stringStream;
    }

    public void setStringStream(Stream<String> stringStream) {
        this.stringStream = stringStream;
    }

    public TransformStream(Stream<String> stringStream) {
        this.stringStream = stringStream;
    }

    public Stream<OutputModel> transform() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return stringStream.parallel().map(row -> {
            try {
                return mapper.readValue(row, OutputModel.class);
            } catch (JsonProcessingException e) {
                // Logging - Deserialization failed
                return null;
            }
        });
    }
}
