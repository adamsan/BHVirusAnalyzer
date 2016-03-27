package hu.adamsan.bionica.competition.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import hu.adamsan.bionica.competition.Main;

public class FileUtils {

    public static List<String> readFromResource(String file) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(file), "utf-8"))) {
            List<String> items = r.lines()
                    .collect(Collectors.toList());
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error occured while reading from " + file + ".");
    }

}
