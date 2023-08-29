package com.abnb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FeatureMapParser {
    private static final Gson GSON = new GsonBuilder().create();

    public Set<String> getFeatureGroups(String fileName) throws IOException {
        Set<String> groupNames = new HashSet<>();
        Path path = new File(fileName).toPath();
        for (String line : IOUtils.readLines(Files.newInputStream(path), StandardCharsets.UTF_8)) {
            String jsonLine = line.replace("'", "\"");
            Map<String, List<String>> map = GSON.fromJson(jsonLine, new TypeToken<Map<String, List<String>>>() {
            }.getType());
            groupNames.addAll(map.keySet());
        }
        return groupNames;
    }

    public static void main(String[] args) throws IOException {
        FeatureMapParser parser = new FeatureMapParser();
        System.out.println(parser.getFeatureGroups("src/main/resources/listing_management.txt"));
        System.out.println(parser.getFeatureGroups("src/main/resources/basic_pna.txt"));
    }
}
