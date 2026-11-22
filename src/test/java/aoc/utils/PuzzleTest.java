package aoc.utils;

import aoc.y9999.d99.DummyPuzzle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PuzzleTest {

    @Test
    void yearAndDayComeFromThePackageName() {
        final var puzzle = new DummyPuzzle();

        assertEquals(9999, puzzle.year());
        assertEquals(99, puzzle.day());
    }
}
