package aoc.utils;

import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

/** Loads puzzle input files from the classpath as a reactive stream of lines. */
public final class PuzzleInput {

    private PuzzleInput() {
    }

    /** Reads the classpath resource at {@code resource}, e.g. {@code years/2026/day01/example.txt}. */
    public static Flux<String> lines(String resource) {
        return Flux.using(
                () -> {
                    InputStream stream = PuzzleInput.class.getClassLoader().getResourceAsStream(resource);
                    if (stream == null) {
                        throw new IllegalArgumentException("Puzzle input not found on classpath: " + resource);
                    }
                    return new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
                },
                reader -> Flux.fromStream(reader.lines()),
                reader -> {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
        );
    }

    /** Reads {@code years/{year}/day{NN}/input.txt} from the main resources. */
    public static Flux<String> lines(int year, int day) {
        return lines("years/%d/day%02d/input.txt".formatted(year, day));
    }
}
