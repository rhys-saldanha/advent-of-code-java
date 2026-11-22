package aoc.utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Runs a single puzzle against its personal input.txt. */
public final class PuzzleRunner {

    private PuzzleRunner() {
    }

    public static Mono<String> part1(Class<? extends Puzzle> puzzleType) {
        final var puzzle = newPuzzle(puzzleType);
        return part1(puzzle, PuzzleInput.lines(puzzle.year(), puzzle.day()));
    }

    public static Mono<String> part2(Class<? extends Puzzle> puzzleType) {
        final var puzzle = newPuzzle(puzzleType);
        return part2(puzzle, PuzzleInput.lines(puzzle.year(), puzzle.day()));
    }

    /** Runs both parts, printing each result. */
    public static void run(Class<? extends Puzzle> puzzleType) {
        final var puzzle = newPuzzle(puzzleType);
        final var input = PuzzleInput.lines(puzzle.year(), puzzle.day());
        print("Part 1", part1(puzzle, input));
        print("Part 2", part2(puzzle, input));
    }

    static Puzzle newPuzzle(Class<? extends Puzzle> puzzleType) {
        try {
            return puzzleType.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Cannot instantiate " + puzzleType.getName(), e);
        }
    }

    static Mono<String> part1(Puzzle puzzle, Flux<String> input) {
        return puzzle.part1(input);
    }

    static Mono<String> part2(Puzzle puzzle, Flux<String> input) {
        return puzzle.part2(input);
    }

    private static void print(String label, Mono<String> result) {
        result.doOnNext(value -> System.out.println(label + ": " + value)).block();
    }
}
