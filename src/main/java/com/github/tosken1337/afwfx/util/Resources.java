package com.github.tosken1337.afwfx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sebastian Greif on 10.08.2017.
 * Copyright di support 2017
 */
public final class Resources {
    private Resources() {}

    public static List<String> getResourcesFromClasspath(final String directory, final String fileSuffix) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Resources.class.getClassLoader().getResourceAsStream(directory)))) {
            return reader.lines().filter(file -> file.endsWith(fileSuffix)).collect(Collectors.toList());
        }
    }
}
