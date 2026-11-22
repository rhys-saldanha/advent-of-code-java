package aoc.y2022.d01;

import aoc.utils.TestPuzzleRunner;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class Day01Test {

    @Test
    void part1() {
        StepVerifier.create(TestPuzzleRunner.part1(Day01.class))
                .expectNext("24000")
                .verifyComplete();
    }
}
