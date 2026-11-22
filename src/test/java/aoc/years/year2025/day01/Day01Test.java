package aoc.years.year2025.day01;

import aoc.utils.PuzzleInput;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class Day01Test {

    private final Day01 puzzle = new Day01();

    @Test
    void part1CountsLines() {
        StepVerifier.create(puzzle.part1(PuzzleInput.lines("years/2025/day01/example.txt")))
                .expectNext("lines: 3")
                .verifyComplete();
    }

    @Test
    void part2CountsNonBlankLines() {
        StepVerifier.create(puzzle.part2(PuzzleInput.lines("years/2025/day01/example.txt")))
                .expectNext("non-blank lines: 3")
                .verifyComplete();
    }
}
