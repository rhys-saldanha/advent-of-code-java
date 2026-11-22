package aoc.y2026.d01;

import aoc.utils.TestPuzzleRunner;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class Day01Test {

    @Test
    void part1CountsLines() {
        StepVerifier.create(TestPuzzleRunner.part1(Day01.class))
                .expectNext("lines: 3")
                .verifyComplete();
    }

    @Test
    void part2CountsNonBlankLines() {
        StepVerifier.create(TestPuzzleRunner.part2(Day01.class))
                .expectNext("non-blank lines: 3")
                .verifyComplete();
    }
}
