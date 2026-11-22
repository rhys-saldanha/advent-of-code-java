# Advent of Code (Java)

A [Spring Boot 4.2.0](https://spring.io/projects/spring-boot) + [Project Reactor](https://projectreactor.io/) starter for solving [Advent of Code](https://adventofcode.com/) puzzles in Java 25. There is no web stack: it is a command-line application.

## Layout

```
src/main/java/aoc/
  AdventOfCodeJavaApplication.java     Spring Boot entry point
  utils/                               shared puzzle infrastructure
    Puzzle.java                        contract implemented by every day
    PuzzleInput.java                   classpath input -> Flux<String>
    PuzzleRunner.java                  per-puzzle runner, reads that day's input.txt
  y2022/d01/Day01.java                 package aoc.y2022.d01; empty implementation stub
  y2025/d01/Day01.java                 package aoc.y2025.d01; example solution
  y2026/d01/Day01.java                 package aoc.y2026.d01; example solution
src/test/java/aoc/
  utils/                               tests for shared utilities
    TestPuzzleRunner.java              runs a puzzle against that day's example.txt
    TestPuzzleInput.java               example.txt -> Flux<String>
  y<YEAR>/d<NN>/DayNNTest.java         one test class per day
src/main/resources/y<YEAR>/d<NN>/input.txt      your real puzzle input
src/test/resources/y<YEAR>/d<NN>/example.txt    sample input from the puzzle
```

`main` holds solutions and real input; `test` holds sample-driven tests; `utils` holds
the reusable plumbing shared across all years.

Personal input files (`src/main/resources/**/input.txt`) are gitignored. The
2022 day 01 scaffold contains the puzzle's sample data in the test resource and an
empty `input.txt` ready to paste your personal input into.

## Run

Every day has its own `main`, so in an IDE you can right-click `DayNN` and run
`DayNN.main()`. From the command line, run a day's main with:

```bash
./gradlew runDay -Pyear=2022 -Pday=1
```

Each day's `main` uses `PuzzleRunner.run(DayNN.class)`, which instantiates the puzzle,
derives the year and day from its package and loads the matching `input.txt`. In tests,
`TestPuzzleRunner` does the same with `example.txt`:

```java
StepVerifier.create(TestPuzzleRunner.part1(Day01.class))
        .expectNext("24000")
        .verifyComplete();
```

Run the tests:

```bash
./gradlew test
```

## Add a new day

Run the scaffold task:

```bash
./gradlew newDay -Pyear=2022 -Pday=2
```

It creates an empty `DayNN` stub, an empty `DayNNTest`, and the two text files every day has:

- `src/test/resources/y<YEAR>/d<NN>/example.txt` — the example from the puzzle page, committed and used by tests.
- `src/main/resources/y<YEAR>/d<NN>/input.txt` — empty and gitignored; paste your personal input here.

Packages follow `aoc.y<YEAR>.d<NN>` and resource folders follow `y<YEAR>/d<NN>`.

It refuses to overwrite an existing day. `year()` and `day()` are read from the package name
(`aoc.y<YEAR>.d<NN>`), so the stub doesn't implement them. `part1` and `part2` both default to
counting lines, so override them in the stub as you implement the puzzle.
