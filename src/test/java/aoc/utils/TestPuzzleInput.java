package aoc.utils;

import reactor.core.publisher.Flux;

/** Test-only helper for reading a day's committed example.txt from test resources. */
public final class TestPuzzleInput {

    private TestPuzzleInput() {
    }

    /** Reads y{year}/d{NN}/example.txt from the test classpath. */
    public static Flux<String> example(int year, int day) {
        return PuzzleInput.lines("y%d/d%02d/example.txt".formatted(year, day));
    }
}
