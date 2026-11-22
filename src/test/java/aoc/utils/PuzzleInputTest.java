package aoc.utils;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class PuzzleInputTest {

    @Test
    void streamsLinesFromClasspath() {
        StepVerifier.create(PuzzleInput.lines("aoc/utils/example.txt"))
                .expectNext("line 1")
                .expectNext("line 2")
                .expectNext("")
                .expectNext("line 4")
                .verifyComplete();
    }

    @Test
    void rejectsMissingResource() {
        StepVerifier.create(PuzzleInput.lines("aoc/utils/missing.txt"))
                .expectError(IllegalArgumentException.class)
                .verify();
    }
}
