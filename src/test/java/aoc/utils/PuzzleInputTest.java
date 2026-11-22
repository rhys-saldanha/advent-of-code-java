package aoc.utils;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class PuzzleInputTest {

    @Test
    void streamsLinesFromClasspath() {
        StepVerifier.create(PuzzleInput.lines("years/9999/day99/example.txt"))
                .expectNext("7-8")
                .expectNext("9-10")
                .expectNext("11-12")
                .verifyComplete();
    }

    @Test
    void rejectsMissingResource() {
        StepVerifier.create(PuzzleInput.lines("years/9999/day99/input.txt"))
                .expectError(IllegalArgumentException.class)
                .verify();
    }
}
