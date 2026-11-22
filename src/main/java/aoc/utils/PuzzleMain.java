package aoc.utils;

import reactor.core.publisher.Flux;

/** Runs a single puzzle directly from its main method, without starting Spring Boot. */
public final class PuzzleMain {

    private PuzzleMain() {
    }

    /**
     * Runs both parts when no part is given. Pass "1" or "2" to run a single part.
     */
    public static void run(Puzzle puzzle, String... parts) {
        Flux<String> input = PuzzleInput.lines(puzzle.year(), puzzle.day());
        if (parts.length == 0) {
            runPart(puzzle, input, 0);
            return;
        }
        for (String part : parts) {
            runPart(puzzle, input, Integer.parseInt(part));
        }
    }

    private static void runPart(Puzzle puzzle, Flux<String> input, int part) {
        if (part == 0 || part == 1) {
            puzzle.part1(input).doOnNext(result -> System.out.println("Part 1: " + result)).block();
        }
        if (part == 0 || part == 2) {
            puzzle.part2(input).doOnNext(result -> System.out.println("Part 2: " + result)).block();
        }
    }
}
