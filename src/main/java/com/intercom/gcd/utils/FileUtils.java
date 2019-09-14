package com.intercom.gcd.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {


    public void wrtieObjects(String path, List<Object> lines) throws IOException {
        if(StringUtils.isEmpty(path))
            throw new IllegalArgumentException("File name is empty");
        ObjectMapper mapper = new ObjectMapper();
        FileWriter writer = new FileWriter(path);
        for(Object obj: lines) {
            writer.write(mapper.writeValueAsString(obj) + System.lineSeparator());
        }
        writer.close();
    }

    public List<String> readLines(String path) throws IOException {
        if(StringUtils.isEmpty(path))
            throw new IllegalArgumentException("File name is empty");
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            return lines.collect(Collectors.toList());
        }
    }
}
