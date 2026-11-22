package aoc.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.DefaultApplicationArguments;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PuzzleRunnerTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    private final RecordingPuzzle puzzle = new RecordingPuzzle(9999, 99);
    private final RecordingPuzzle otherPuzzle = new RecordingPuzzle(2022, 1);

    private PuzzleRunner runner;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        runner = new PuzzleRunner(List.of(puzzle, otherPuzzle));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void runsBothPartsByDefault() {
        runner.run(new DefaultApplicationArguments("9999", "99"));

        assertEquals(1, puzzle.part1Calls);
        assertEquals(1, puzzle.part2Calls);
        assertEquals(0, otherPuzzle.part1Calls);
        assertEquals(0, otherPuzzle.part2Calls);
        assertEquals("Part 1: part 1 from 2 lines%nPart 2: part 2 from 2 lines%n".formatted(), printed());
    }

    @Test
    void runsOnlyPart1WhenRequested() {
        runner.run(new DefaultApplicationArguments("9999", "99", "1"));

        assertEquals(1, puzzle.part1Calls);
        assertEquals(0, puzzle.part2Calls);
        assertEquals("Part 1: part 1 from 2 lines%n".formatted(), printed());
    }

    @Test
    void runsOnlyPart2WhenRequested() {
        runner.run(new DefaultApplicationArguments("9999", "99", "2"));

        assertEquals(0, puzzle.part1Calls);
        assertEquals(1, puzzle.part2Calls);
        assertEquals("Part 2: part 2 from 2 lines%n".formatted(), printed());
    }

    @Test
    void listsAvailablePuzzlesInOrderWithNoArguments() {
        runner.run(new DefaultApplicationArguments());

        assertEquals("Available: 2022 day 01%nAvailable: 9999 day 99%n".formatted(), printed());
    }

    @Test
    void rejectsUnknownDay() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> runner.run(new DefaultApplicationArguments("9999", "98")));

        assertTrue(error.getMessage().contains("9999 day 98"));
    }

    @Test
    void rejectsMissingDayArgument() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> runner.run(new DefaultApplicationArguments("9999")));

        assertTrue(error.getMessage().contains("Usage"));
    }

    private String printed() {
        return output.toString(StandardCharsets.UTF_8);
    }

    private static final class RecordingPuzzle implements Puzzle {

        private final int year;
        private final int day;
        private int part1Calls;
        private int part2Calls;

        private RecordingPuzzle(int year, int day) {
            this.year = year;
            this.day = day;
        }

        @Override
        public int year() {
            return year;
        }

        @Override
        public int day() {
            return day;
        }

        @Override
        public Mono<String> part1(Flux<String> input) {
            part1Calls++;
            return input.count().map(count -> "part 1 from " + count + " lines");
        }

        @Override
        public Mono<String> part2(Flux<String> input) {
            part2Calls++;
            return input.count().map(count -> "part 2 from " + count + " lines");
        }
    }
}
