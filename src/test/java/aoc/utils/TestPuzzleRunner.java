package aoc.utils;

import reactor.core.publisher.Mono;

/** Runs a single puzzle against its committed example.txt instead of the personal input. */
public final class TestPuzzleRunner {

    private TestPuzzleRunner() {
    }

    public static Mono<String> part1(Class<? extends Puzzle> puzzleType) {
        final var puzzle = PuzzleRunner.newPuzzle(puzzleType);
        return PuzzleRunner.part1(puzzle, TestPuzzleInput.example(puzzle.year(), puzzle.day()));
    }

    public static Mono<String> part2(Class<? extends Puzzle> puzzleType) {
        final var puzzle = PuzzleRunner.newPuzzle(puzzleType);
        return PuzzleRunner.part2(puzzle, TestPuzzleInput.example(puzzle.year(), puzzle.day()));
    }
}
